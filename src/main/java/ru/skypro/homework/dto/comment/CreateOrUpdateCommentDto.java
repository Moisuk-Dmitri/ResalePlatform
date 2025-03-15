package ru.skypro.homework.dto.comment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CreateOrUpdateCommentDto {

    @Schema(minLength = 8, maxLength = 64, description = "текст комментария")
    private String text;

}
