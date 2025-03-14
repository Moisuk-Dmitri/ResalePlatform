package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    @Override
    public boolean getComment(int id) {
        return true;
    }

    @Override
    public boolean postComment(int id, String text) {
        return true;
    }

    @Override
    public boolean deleteComment(int adId, int commentId) {
        return true;
    }

    @Override
    public boolean patchComment(int adId, int commentId) {
        return true;
    }
}
