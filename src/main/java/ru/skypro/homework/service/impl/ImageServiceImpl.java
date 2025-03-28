package ru.skypro.homework.service.impl;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Component
@Data
public class ImageServiceImpl {


    public ResponseEntity<byte[]> getImageAsBytes;

    @Value("${image.path}")
    private String path;


    public String updateImage(MultipartFile image) throws IOException {

        Path imagePath = Paths.get(path);
        Files.createDirectories(Paths.get(path)); //create directory for images
        String imageName = UUID.randomUUID()+"-" + image.getOriginalFilename(); //create name for new image

        Path filePath = imagePath.resolve(imageName); // create final path with created image name
        Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING); // copy image to created directory with replacing old image
        return imageName;
    }

    public void deleteUserImage(User user)  {
        Path imagePath = Paths.get(path, user.getImage());
        try {
            Files.deleteIfExists(imagePath);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAdImage(Ad ad)  {
        Path imagePath = Paths.get(path, ad.getImage());
        try {
            Files.deleteIfExists(imagePath);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}