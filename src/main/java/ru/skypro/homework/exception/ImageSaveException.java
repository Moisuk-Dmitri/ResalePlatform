package ru.skypro.homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ImageSaveException extends RuntimeException{
    public ImageSaveException(String message) {
        super(message);
    }

    public ImageSaveException() {super("image save exception");}
}
