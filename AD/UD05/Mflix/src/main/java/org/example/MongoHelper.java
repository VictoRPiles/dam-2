package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import static com.mongodb.client.model.Filters.*;

/**
 * @author victor
 */
@SuppressWarnings("unused")
public class MongoHelper {
    /**
     * Print a JSON string using {@link GsonBuilder#setPrettyPrinting() Google's GSON library}.
     *
     * @param json Unformatted JSON.
     * @return Formatted JSON.
     */
    public static String formatJson(String json) {
        /* https://stackoverflow.com/questions/4105795/pretty-print-json-in-java */
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement je = JsonParser.parseString(json);

        return gson.toJson(je);
    }

    /**
     * Suppress MongoDB Driver logs.
     */
    public static void suppressLogs() {
        Logger logger = Logger.getLogger("org.mongodb.driver");
        logger.setLevel(Level.SEVERE);
    }

    /**
     * Print all {@link Document documents} in an {@link FindIterable iterable}.
     *
     * @param iterable The iterable.
     */
    public static void printFindIterable(@NotNull FindIterable<Document> iterable) {
        for (Document document : iterable) {
            System.out.println(document.toJson());
        }
    }

    @SuppressWarnings("SameParameterValue")
    static @NotNull FindIterable<Document> getMovieByTitle(@NotNull MongoClient mongoClient, String title) {
        Bson filter = eq("title", title);
        MongoDatabase database = mongoClient.getDatabase("mflix");
        MongoCollection<Document> collection = database.getCollection("movies");

        System.out.printf("Query: {title: '%s'}%n", title);
        return collection.find(filter);
    }

    @SuppressWarnings("SameParameterValue")
    static @NotNull FindIterable<Document> getMovieByYear(@NotNull MongoClient mongoClient, int year) {
        Bson filter = eq("year", year);

        MongoDatabase database = mongoClient.getDatabase("mflix");
        MongoCollection<Document> collection = database.getCollection("movies");

        System.out.printf("Query: {year: '%s'}%n", year);
        return collection.find(filter);
    }

    @SuppressWarnings("SameParameterValue")
    static @NotNull FindIterable<Document> getMovieByYearRange(@NotNull MongoClient mongoClient, int startYear, int finalYear) {
        Bson filter = and(Arrays.asList(gte("year", startYear), lte("year", finalYear)));

        MongoDatabase database = mongoClient.getDatabase("mflix");
        MongoCollection<Document> collection = database.getCollection("movies");

        System.out.printf("Query: {$and: [{year: {$gte: %s}}, {year: {$lte: %s}}]}%n", startYear, finalYear);
        return collection.find(filter);
    }

    @SuppressWarnings("SameParameterValue")
    static @NotNull FindIterable<Document> getMovieByYearType(@NotNull MongoClient mongoClient, String type) {
        Bson filter = type("year", type);

        MongoDatabase database = mongoClient.getDatabase("mflix");
        MongoCollection<Document> collection = database.getCollection("movies");

        System.out.printf("Query: {year: {$type: \"%s\"}}%n", type);
        return collection.find(filter);
    }

    @SuppressWarnings("SameParameterValue")
    static @NotNull FindIterable<Document> getMovieByPlotExistence(@NotNull MongoClient mongoClient, boolean exists) {
        Bson filter = exists("plot", exists);

        MongoDatabase database = mongoClient.getDatabase("mflix");
        MongoCollection<Document> collection = database.getCollection("movies");

        System.out.printf("Query: {plot: {$exists: %s}}%n", exists);
        return collection.find(filter);
    }

    @SuppressWarnings("SameParameterValue")
    static @NotNull FindIterable<Document> getMovieByCountry(@NotNull MongoClient mongoClient, String country) {
        Bson filter = eq("countries", country);

        MongoDatabase database = mongoClient.getDatabase("mflix");
        MongoCollection<Document> collection = database.getCollection("movies");

        System.out.printf("Query: {countries: \"%s\"}%n", country);
        return collection.find(filter);
    }

    @SuppressWarnings("SameParameterValue")
    static @NotNull FindIterable<Document> getMovieByCastSizeAndGenre(@NotNull MongoClient mongoClient, int size, String genre) {
        Bson filter = and(size("cast", size), eq("genres", genre));

        MongoDatabase database = mongoClient.getDatabase("mflix");
        MongoCollection<Document> collection = database.getCollection("movies");

        System.out.printf("Query: {cast: {$size: %s}, genres: '%s'}%n", size, genre);
        return collection.find(filter);
    }

    static @NotNull FindIterable<Document> getMovieByLanguages(@NotNull MongoClient mongoClient, String... languages) {
        List<String> languagesList = Arrays.asList(languages);
        Bson filter = all("languages", languagesList);

        MongoDatabase database = mongoClient.getDatabase("mflix");
        MongoCollection<Document> collection = database.getCollection("movies");

        System.out.printf("Query: {languages: {$all:%s}}%n", languagesList);
        return collection.find(filter);
    }

    @SuppressWarnings("SameParameterValue")
    static @NotNull FindIterable<Document> getMovieByDirectorRegex(@NotNull MongoClient mongoClient, String directorRegex) {
        Bson filter = regex("directors", Pattern.compile(directorRegex));

        MongoDatabase database = mongoClient.getDatabase("mflix");
        MongoCollection<Document> collection = database.getCollection("movies");

        System.out.printf("Query: {directors: {$regex:%s}}%n", directorRegex);
        return collection.find(filter);
    }

    @SuppressWarnings("SameParameterValue")
    static @NotNull FindIterable<Document> getMovieByDirectorOrDirectorRegex(@NotNull MongoClient mongoClient, String directorRegex1, String directorRegex2) {
        Bson filter = or(Arrays.asList(
                regex("directors", Pattern.compile(directorRegex1)),
                regex("directors", Pattern.compile(directorRegex2)))
        );

        MongoDatabase database = mongoClient.getDatabase("mflix");
        MongoCollection<Document> collection = database.getCollection("movies");

        System.out.printf("Query: {$or: [{directors: {$regex:%s}}, {directors: {$regex:%s}}]}%n", directorRegex1, directorRegex2);
        return collection.find(filter);
    }

    @SuppressWarnings("SameParameterValue")
    static @NotNull FindIterable<Document> getMovieByAwardsAndPoints(@NotNull MongoClient mongoClient, int awards, int points) {
        Bson filter = and(Arrays.asList(gt("awards.wins", awards), gte("imdb.rating", points)));

        MongoDatabase database = mongoClient.getDatabase("mflix");
        MongoCollection<Document> collection = database.getCollection("movies");

        System.out.printf("Query: {$and:[{\"awards.wins\": {$gt:%s}}, {\"imdb.rating\":{$gte:%s}}]}%n", awards, points);
        return collection.find(filter);
    }
}
