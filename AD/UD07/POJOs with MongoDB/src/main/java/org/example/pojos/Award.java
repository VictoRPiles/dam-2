package org.example.pojos;

import org.bson.types.ObjectId;

/**
 * @author victor
 */
public class Award {
    private final ObjectId id;
    private int wins;
    private int nominations;
    private String text;

    public Award() {
        this.id = new ObjectId();
    }

    public Award(int wins, int nominations, String text) {
        this.id = new ObjectId();
        this.wins = wins;
        this.nominations = nominations;
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Award award = (Award) o;

        return id.equals(award.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Award{" +
                "id=" + id +
                ", wins=" + wins +
                ", nominations=" + nominations +
                ", text='" + text + '\'' +
                '}';
    }

    public ObjectId getId() {
        return id;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getNominations() {
        return nominations;
    }

    public void setNominations(int nominations) {
        this.nominations = nominations;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
