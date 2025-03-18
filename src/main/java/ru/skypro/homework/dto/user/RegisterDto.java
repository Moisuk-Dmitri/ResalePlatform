package ru.skypro.homework.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.homework.dto.Role;
/**
 * Represents an object with data for registration - username, password, firstname, lastname, phone, role
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterDto {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;

}


