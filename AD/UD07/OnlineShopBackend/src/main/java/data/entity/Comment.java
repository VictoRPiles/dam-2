package data.entity;

import org.bson.types.ObjectId;
import org.jetbrains.annotations.NotNull;

/**
 * @author victor
 */
public class Comment {
    private Integer score;
    private ObjectId userId;
    private String text;

    public Comment() {
    }

    public Comment(Integer score, ObjectId userId, String text) {
        setScore(score);
        this.userId = userId;
        this.text = text;
    }

    public Integer getScore() {
        return score;
    }

    /**
     * Assert score value is between 1 and 5.
     *
     * @param score The score value.
     */
    public void setScore(Integer score) {
        if (!((score >= 1) && (score <= 5))) throw new IllegalArgumentException("Score value must be between 1 and 5");
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

    @Override
    public @NotNull String toString() {
        return "Comment{" +
                "score=" + score +
                ", userId=" + userId +
                ", text='" + text + '\'' +
                '}';
    }
}
