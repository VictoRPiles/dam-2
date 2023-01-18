package org.example;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        MongoHelper.suppressLogs();
        /*
         * Requires the MongoDB Java Driver.
         * https://mongodb.github.io/mongo-java-driver
         */
        @SuppressWarnings("resource")
        MongoClient mongoClient = new MongoClient(
                new MongoClientURI(
                        "mongodb://localhost:27017/"
                )
        );
        /* Create database social-network */
        MongoDatabase database = mongoClient.getDatabase("social-network");

        /* Insert users */
        MongoCollection<Document> collection = database.getCollection("users");
        List<Document> users = new ArrayList<>();
        users.add(new Document()
                .append("_id", 5L)
                .append("name", "Juan")
                .append("Surname", "García Castellano")
                .append("age", 23)
                .append("gender", "M")
                .append("registration", new Date())
        );
        users.add(new Document()
                .append("_id", 6L)
                .append("name", "Beatriz")
                .append("Surname", "Perez Solaz")
                .append("age", 27)
                .append("gender", "F")
                .append("registration", new Date())
        );
        collection.insertMany(users);

        /* Retrieve all users */
        Bson filter = new Document();
        FindIterable<Document> result = collection.find(filter);
        MongoHelper.printFindIterable(result);

        Document jorge = new Document()
                .append("_id", 7L)
                .append("name", "Jorge")
                .append("Surname", "Lopez Sevilla")
                .append("gender", "M")
                .append("registration", new Date())
                .append("groups", new String[]{"basketball", "kitchen", "historical novel"});
        collection.insertOne(jorge);
    }
}