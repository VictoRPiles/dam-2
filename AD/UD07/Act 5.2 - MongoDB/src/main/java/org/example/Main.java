package org.example;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

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

        UserController users = new UserController(database);
        users.run();

        CompanyController company = new CompanyController(database);
        company.run();
    }
}