package ru.skypro.homework.repository;

import liquibase.pro.packaged.C;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.Comment;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    Optional<List<Comment>> findByAd(int ad);

    Optional<Comment> findByAdAndPk(int ad, int pk);

    Optional<Comment> deleteByAdAndPk(int ad, int pk);
}
