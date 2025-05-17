package ru.skypro.homework.dto.ad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents extended information about an advertisement.
 * This class encapsulates detailed information about an advertisement, including its ID,
 * author details, description, contact information, image URL, price, and title.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExtendedAd {
    private Integer pk;
    private String authorFirstName;
    private String authorLastName;
    private String description;
    private String email;
    private String image;
    private String phone;
    private Integer price;
    private String title;

}
