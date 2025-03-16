package ru.skypro.homework.dto.ad;

import lombok.Data;

import javax.validation.constraints.Size;

/**
 * Represents the properties for creating or updating an advertisement.
 * This class encapsulates the title, price, and description of an advertisement,
 * with validation constraints applied to ensure data integrity.
 */
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

    public @Size(min = 4, max = 32) String getTitle() {
        return title;
    }

    public void setTitle(@Size(min = 4, max = 32) String title) {
        this.title = title;
    }

    public @Size(min = 0, max = 10000000) Integer getPrice() {
        return price;
    }

    public void setPrice(@Size(min = 0, max = 10000000) Integer price) {
        this.price = price;
    }

    public @Size(min = 8, max = 64) String getDescription() {
        return description;
    }

    public void setDescription(@Size(min = 8, max = 64) String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "CreateOrUpdateAd{" +
                "title='" + title + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
