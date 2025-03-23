package ru.skypro.homework.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import ru.skypro.homework.dto.comment.CommentDto;
import ru.skypro.homework.dto.comment.CommentsDto;
import ru.skypro.homework.dto.comment.CreateOrUpdateCommentDto;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.mappers.CommentMapper;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final AdRepository adRepository;
    private CommentMapper commentMapper;

    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository, AdRepository adRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.adRepository = adRepository;
    }

    @Override
    public CommentsDto getComments(int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            List<CommentDto> comments = commentRepository.findByAd(id).get().stream()
                    .map(commentMapper::commentToCommentDto)
                    .toList();

            return new CommentsDto(
                    comments.size(),
                    comments
            );
        } else {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
        }
    }


    @Override
    public CreateOrUpdateCommentDto postComment(int id, String text) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            Comment comment = new Comment(
                    userRepository.findByUsername(authentication.getName()).get(),
                    text,
                    new Date().getTime(),
                    adRepository.findById(id).get()
            );
            return commentMapper.commentToCreateOrUpdateCommentDto(commentRepository.save(comment));
        } else {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public Void deleteComment(int adId, int commentId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            commentMapper.commentToCommentDto(commentRepository.deleteByAdAndPk(adId, commentId).get());
            return null;
        } else {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public CreateOrUpdateCommentDto patchComment(int adId, int commentId, String text) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            Comment comment = commentRepository.findByAdAndPk(adId, commentId).get();
            comment.setText(text);
            return commentMapper.commentToCreateOrUpdateCommentDto(commentRepository.save(comment));
        } else {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
        }
    }
}
