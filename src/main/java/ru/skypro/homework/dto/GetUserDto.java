package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class GetUserDto {

    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private Role USER;
    private String image;
}
