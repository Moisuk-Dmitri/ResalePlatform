package ru.skypro.homework.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Represents a data object for updating password
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SetPasswordDto {

    private String currentPassword;
    private String newPassword;

}
