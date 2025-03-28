package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ad.CreateOrUpdateAdDto;
import ru.skypro.homework.model.Image;

import java.io.IOException;
import java.security.Principal;

public interface ImageService {
    public Image uploadAdImage(CreateOrUpdateAdDto properties, MultipartFile image) throws IOException;
    public Integer findImageIdByImagePath(String imagePath);
    public String updateAdImage(Integer imageId, MultipartFile image) throws IOException;
    void deleteImage(Integer imageId);
    Image saveImage(MultipartFile imageFile, Principal principal) throws IOException;
}