package ru.skypro.homework.service;

public interface CommentService {

    public boolean getComment(int id);

    public boolean postComment(int id, String text);

    public boolean deleteComment(int adId, int commentId);

    public boolean patchComment(int adId, int commentId);

}
