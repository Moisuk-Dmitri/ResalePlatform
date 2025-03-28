package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ad.CreateOrUpdateAdDto;
import ru.skypro.homework.exception.ImageNotFoundException;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.ImageService;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.Principal;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Value("${ad.image.path}")
    private String imagePath;

    @Value("${avatar.dir.path}")
    private String avatarPath;

    /**
     * Save
     *
     * @param image file of image.
     * @param properties CreateOrUpdateAd.
     * @return Image
     */
    @Override
    public Image uploadAdImage(CreateOrUpdateAdDto properties, MultipartFile image) throws IOException {
        createDirectory();
        Path filePath = Path.of(imagePath, properties.getTitle() + properties.getPrice() + "." + image.getOriginalFilename());
        image.transferTo(filePath);

        Image image1 = new Image();
        image1.setImagePath(filePath.toString());
        imageRepository.save(image1);
        image1.setId(findImageIdByImagePath(filePath.toString()));
        return image1;
    }

    /**
     * Get id by path
     *
     * @param imagePath file of image
     * @return Integer
     * @throws ImageNotFoundException if it is not found
     */
    @Override
    public Integer findImageIdByImagePath(String imagePath) {
        return imageRepository.findIdByImagePath(imagePath);
    }

    /**
     * Change
     *
     * @param imageId id
     * @param image file
     * @return String path
     * @throws ImageNotFoundException if it is not found
     */
    @Override
    public String updateAdImage(Integer imageId, MultipartFile image) throws IOException {
        if (!imageRepository.existsById(imageId)) {

            throw new ImageNotFoundException(imageId);
        }
        Image image1 = imageRepository.findById(imageId).orElseThrow(() -> new ImageNotFoundException(imageId));
        Path filePath = Path.of(image1.getImagePath());
        Files.deleteIfExists(filePath);
        image.transferTo(filePath);

        return filePath.toString();
    }

    /**
     * Delete
     *
     * @param imageId идентификатор изображения
     */
    @Override
    public void deleteImage(Integer imageId) {
        if (imageRepository.existsById(imageId)) {
            imageRepository.deleteById(imageId);
        }
    }

    /**
     * Save
     *
     * @param image file of image=
     * @return Image
     */
    public Image saveImage(MultipartFile image, Principal principal) throws IOException {
        createDirectoryAvatar();
        Path filePath = Path.of(avatarPath, principal.getName()
                .substring(0, principal.getName()
                        .lastIndexOf("@")) + "." + getExtension(Objects.requireNonNull(image.getOriginalFilename())));

        image.transferTo(filePath);

        Image image1 = new Image();
        image1.setImagePath(filePath.toString());
        imageRepository.save(image1);
        return image1;
    }

    private void createDirectoryAvatar() throws IOException {
        Path path = Path.of(avatarPath);
        if (Files.notExists(path)) {
            Files.createDirectory(path);
        }
    }

    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    private void createDirectory() throws IOException {
        Path path = Path.of(imagePath);
        if (Files.notExists(path)) {
            Files.createDirectory(path);
        }
    }

    private String getExtension(String originalPath) {
        return originalPath.substring(originalPath.lastIndexOf(".") + 1);
    }

    private byte[] getImageBytes(Integer id) throws IOException {
        Path imagePath = Path.of(imageRepository.findById(id).orElseThrow(() -> new ImageNotFoundException(id)).getImagePath());
        return Files.readAllBytes(imagePath);
    }
}