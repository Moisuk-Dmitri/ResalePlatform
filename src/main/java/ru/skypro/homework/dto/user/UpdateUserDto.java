package ru.skypro.homework.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Represents a data object for updating user's info
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateUserDto {

    private String firstName;
    private String lastName;
    private String phone;

}
