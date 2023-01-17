package org.example;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import org.bson.Document;

@SuppressWarnings("SameParameterValue")
public class Main {
    public static void main(String[] args) {
        MongoHelper.suppressLogs();
        /*
         * Requires the MongoDB Java Driver.
         * https://mongodb.github.io/mongo-java-driver
         */
        MongoClient mongoClient = new MongoClient(
                new MongoClientURI(
                        "mongodb://localhost:27017/"
                )
        );

        queryExercises(mongoClient);
    }

    private static void queryExercises(MongoClient mongoClient) {
        int limitValue = 100;
        System.out.println("==================================================");
        /* Get the data for the movie “Jurassic World” */
        FindIterable<Document> movieByTitle = MongoHelper.getMovieByTitle(mongoClient, "Jurassic World").limit(limitValue);
        MongoHelper.printFindIterable(movieByTitle);
        System.out.println("==================================================");
        /* Get the movies of the year 2016 */
        FindIterable<Document> movieByYear = MongoHelper.getMovieByYear(mongoClient, 2016).limit(limitValue);
        MongoHelper.printFindIterable(movieByYear);
        System.out.println("==================================================");
        /* Get the movies released between 2015 and 2016, both inclusive */
        FindIterable<Document> movieByYearRange = MongoHelper.getMovieByYearRange(mongoClient, 2015, 2016).limit(limitValue);
        MongoHelper.printFindIterable(movieByYearRange);
        System.out.println("==================================================");
        /* Get the movies released between 2015 and 2016, both inclusive */
        FindIterable<Document> movieByYearType = MongoHelper.getMovieByYearType(mongoClient, "string").limit(limitValue);
        MongoHelper.printFindIterable(movieByYearType);
        System.out.println("==================================================");
        /* Get the movies that have not defined a plot ($exists) */
        FindIterable<Document> movieByPlotExistence = MongoHelper.getMovieByPlotExistence(mongoClient, false).limit(limitValue);
        MongoHelper.printFindIterable(movieByPlotExistence);
        System.out.println("==================================================");
        /* Get the movies filmed in Spain */
        FindIterable<Document> movieByCountry = MongoHelper.getMovieByCountry(mongoClient, "Spain").limit(limitValue);
        MongoHelper.printFindIterable(movieByCountry);
        System.out.println("==================================================");
        /* Get the movies with only one person in the cast and of the genre “Biography” */
        FindIterable<Document> movieByCastSizeAndGenre = MongoHelper.getMovieByCastSizeAndGenre(mongoClient, 1, "Biography").limit(limitValue);
        MongoHelper.printFindIterable(movieByCastSizeAndGenre);
        System.out.println("==================================================");
        /* Get the movies that have Spanish and English languages ($all) */
        FindIterable<Document> movieByLanguages = MongoHelper.getMovieByLanguages(mongoClient, "English", "Spanish").limit(limitValue);
        MongoHelper.printFindIterable(movieByLanguages);
        System.out.println("==================================================");
        /* Get the movies directed by Spielberg ($regex) */
        FindIterable<Document> movieByDirectorRegex = MongoHelper.getMovieByDirectorRegex(mongoClient, "/spielberg/i").limit(limitValue);
        MongoHelper.printFindIterable(movieByDirectorRegex);
        System.out.println("==================================================");
        /* Get the movies directed by Spielberg OR Kubrick */
        FindIterable<Document> movieByDirectorOrDirectorRegex = MongoHelper.getMovieByDirectorOrDirectorRegex(mongoClient, "spielberg(?i)", "kubrick(?i)").limit(limitValue);
        MongoHelper.printFindIterable(movieByDirectorOrDirectorRegex);
        System.out.println("==================================================");
        /* Get the movies that won more than 7 awards and have at least 9 points in IMDB */
        FindIterable<Document> movieByAwardsAndPoints = MongoHelper.getMovieByAwardsAndPoints(mongoClient, 7, 9).limit(limitValue);
        MongoHelper.printFindIterable(movieByAwardsAndPoints);
        System.out.println("==================================================");
    }
}