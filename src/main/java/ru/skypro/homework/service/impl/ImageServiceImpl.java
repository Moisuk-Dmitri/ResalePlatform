package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.skypro.homework.exception.ImageNotFoundException;
import ru.skypro.homework.service.ImageService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {

    private final Path imagePath;

    public ImageServiceImpl(@Value("${path.images}") Path imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public Path getImagePath() {
        return imagePath;
    }

    @Override
    public String saveImageToDir(String image) {
        try {
            Path path = Paths.get(imagePath + "\\" + image);
            File file = new File(String.valueOf(path.toAbsolutePath()));
            if (!file.exists()) {
                throw new ImageNotFoundException("image not found to read");
            }
            BufferedImage bi = ImageIO.read(file);

            String generatedName = UUID.randomUUID() + ".png";
            File outputfile = new File(imagePath.toString() + "\\" + generatedName);
            ImageIO.write(bi, "png", outputfile);

            return generatedName;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteImageFromDir(String image) {
        Path path = Paths.get(imagePath + "\\" + image);
        File file = new File(String.valueOf(path.toAbsolutePath()));
        if (!file.exists()) {
            throw new ImageNotFoundException("image not found to delete");
        }

        file.delete();
    }

}
