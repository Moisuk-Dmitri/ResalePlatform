package ru.skypro.homework.service;

import java.nio.file.Path;

public interface ImageService {

    Path getImagePath();

    String saveImageToDir(String image);

    void deleteImageFromDir(String image);

}
