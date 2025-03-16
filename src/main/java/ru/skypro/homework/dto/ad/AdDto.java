package ru.skypro.homework.dto.ad;

import lombok.Data;

/**
 * Represents an advertisement object with an ID, title, author, image and price.
 * This class is used to pass ad data between application layers,
 * and is designed to simplify the processing and presentation of ad information in the user interface.
 */
@Data
public class AdDto {
    private Integer author;
    private String image;
    private Integer pk;
    private Integer price;
    private String title;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }

    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "AdDto{" +
                "author=" + author +
                ", image='" + image + '\'' +
                ", pk=" + pk +
                ", price=" + price +
                ", title='" + title + '\'' +
                '}';
    }
}
