package ru.skypro.homework.dto.ad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

/**
 * Represents the properties for creating or updating an advertisement.
 * This class encapsulates the title, price, and description of an advertisement,
 * with validation constraints applied to ensure data integrity.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateOrUpdateAdDto {

    /**
     * The title of the advertisement.
     * Must be between 4 and 32 characters in length.
     */
    @Size(min = 4, max = 32)
    private String title;

    /**
     * The price of the advertisement.
     * Must be between 0 and 10000000.
     */
    @Size(min = 0, max = 10000000)
    private Integer price;

    /**
     * The description of the advertisement.
     * Must be between 8 and 64 characters in length.
     */
    @Size(min = 8, max = 64)
    private String description;

}
