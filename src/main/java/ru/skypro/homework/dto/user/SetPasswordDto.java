package ru.skypro.homework.dto.user;

import lombok.Data;

@Data
public class SetPasswordDto {

    private String currentPassword;
    private String newPassword;

}
