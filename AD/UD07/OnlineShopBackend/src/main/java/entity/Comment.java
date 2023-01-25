package entity;

import org.bson.types.ObjectId;

/**
 * @author victor
 */
public class Comment {
    private int score;
    private ObjectId userId;
    private String text;

    public Comment() {
    }

    public Comment(ObjectId userId, String text) {
        this.userId = userId;
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        return id.equals(comment.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", score=" + score +
                ", userId=" + userId +
                ", text='" + text + '\'' +
                '}';
    }

    public ObjectId getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        // TODO: 24/1/2023 Integer between 1 and 5
        this.score = score;
    }

    public ObjectId getUserId() {
        return userId;
    }

    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
