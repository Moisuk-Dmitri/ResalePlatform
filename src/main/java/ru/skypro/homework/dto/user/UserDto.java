package ru.skypro.homework.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {

    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String image;
}


