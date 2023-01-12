package org.example;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        @SuppressWarnings("SpellCheckingInspection")
        MongoDatabase newMflixDatabase = MongoHelper.getDatabase("new_mflix");
        MongoCollection<Document> collection = MongoHelper.getCollection(newMflixDatabase, "movies");

        Document document1 = new Document()
                .append("title", "Lord of Rings")
                .append("genres", List.of("Adventure", "Fantasy"))
                .append("type", "Movie")
                .append("rated", "+13")
                .append("year", 2001)
                .append("director", "Peter Jackson")
                .append("cast", List.of("Elijah Wood", "Ian McKellen", "Liv Tyler", "Viggo Mortensen"));
        Document document2 = new Document()
                .append("title", "Matrix")
                .append("genres", List.of("Sci-fi,", "Cyberpunk"))
                .append("type", "Movie")
                .append("rated", "+18")
                .append("year", 1999)
                .append("director", "The Wachowskis")
                .append("cast", List.of("Keanu Reeves", "Laurence Fishburne", "Carrie-Anne Moss"));
        Document document3 = new Document()
                .append("title", "Game of thrones")
                .append("genres", List.of("Fantasy", "Intrigue"))
                .append("type", "Series")
                .append("rated", "+18")
                .append("year", 2011)
                .append("cast", List.of("Peter Dinklage", "Lena Headey,", "Kit Harington", "Emilia Clarke"));
        Document document4 = new Document()
                .append("title", "Data Access: the Movie")
                .append("genres", List.of("Drama", "Horror"))
                .append("type", "Movie")
                .append("rated", "+18")
                .append("year", 2021)
                .append("director", "Xavier Ibáñez");

        System.out.println("[OK] Inserting Lord of Rings...");
        System.out.println("[OK] Inserting Matrix...");
        System.out.println("[OK] Inserting Game of thrones...");
        System.out.println("[OK] Inserting Data Access: the Movie...");
        collection.insertMany(List.of(document1, document2, document3, document4));

        MongoHelper.closeConnection();
    }
}