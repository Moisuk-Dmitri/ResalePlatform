package ru.skypro.homework.dto.comment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentDto {

        @Schema(description = "id автора комментария")
        private Integer author;
        @Schema(description = "ссылка на аватар автора комментария")
        private String authorImage;
        @Schema(description = "имя создателя комментария")
        private String authorFirstName;
        @Schema(description = "дата и время создания комментария в миллисекундах с 00:00:00 01.01.1970")
        private long createdAt;
        @Schema(description = "id комментария")
        private int pk;
        @Schema(description = "текст комментария")
        private String text;

}
