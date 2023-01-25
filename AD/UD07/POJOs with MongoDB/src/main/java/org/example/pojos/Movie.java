package org.example.pojos;

import org.bson.types.ObjectId;

import java.util.List;

/**
 * @author victor
 */
public class Movie {
    private final ObjectId id;
    private String title;
    private int year;
    private List<String> directors;
    private List<String> genres;
    private Award award;

    public Movie() {
        this.id = new ObjectId();
    }

    public Movie(String title, int year, List<String> directors, List<String> genres, Award award) {
        this.id = new ObjectId();
        this.title = title;
        this.year = year;
        this.directors = directors;
        this.genres = genres;
        this.award = award;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        return id.equals(movie.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", directors=" + directors +
                ", genres=" + genres +
                ", award=" + award +
                '}';
    }

    public ObjectId getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<String> getDirectors() {
        return directors;
    }

    public void setDirectors(List<String> directors) {
        this.directors = directors;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public Award getAward() {
        return award;
    }

    public void setAward(Award award) {
        this.award = award;
    }
}
