package ru.skypro.homework.service.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.comment.CommentDto;
import ru.skypro.homework.dto.comment.CommentsDto;
import ru.skypro.homework.model.Comment;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper( CommentMapper.class );

    CommentDto commentToCommentDto(Comment comment);
    Comment commentDtoToComment(CommentsDto commentsDto);




}
