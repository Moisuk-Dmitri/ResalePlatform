package ru.skypro.homework.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pk;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false)
    private UserData author;

    private String text;
    private long createdAt;

    public Comment() {
    }

    public Comment(int pk, UserData author, String text, long createdAt) {
        this.pk = pk;
        this.author = author;
        this.text = text;
        this.createdAt = createdAt;
    }

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public UserData getAuthor() {
        return author;
    }

    public void setAuthor(UserData author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Comment comment = (Comment) object;
        return pk == comment.pk && createdAt == comment.createdAt && Objects.equals(author, comment.author) && Objects.equals(text, comment.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pk, author, text, createdAt);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "pk=" + pk +
                ", author=" + author +
                ", text='" + text + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
