package ru.skypro.homework.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Represents an object with data for authorization - username and password
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginDto {

    private String username;
    private String password;

}
