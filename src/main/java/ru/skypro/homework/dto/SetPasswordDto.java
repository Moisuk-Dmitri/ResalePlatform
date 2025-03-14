package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class SetPasswordDto {

    private String currentPassword;
    private String newPassword;

}
