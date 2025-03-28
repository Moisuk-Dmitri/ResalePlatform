package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface ImageService {

    Path getImagePath();

    String saveImageToDir(MultipartFile image);

    void deleteImageFromDir(String image);

}
