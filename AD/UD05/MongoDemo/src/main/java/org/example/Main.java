package org.example;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import org.bson.Document;
/**
 ____
/    \                        __
\    /                       |   \
 ----------------------------    _\
 ____________________________     /
/    \                       |__ /
\    /
 ----
 */
public class Main {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("mflix");

        MongoIterable<String> databaseNames = client.listDatabaseNames();
        StringBuilder result = new StringBuilder();
        for (String name : databaseNames) {
            result.append(name).append("; ");
        }

        System.out.println("Available databases: " + result);
        System.out.println("Database name: " + db.getName());

        MongoCollection<Document> movies;
        String collectionName = "movies";
        movies = db.getCollection(collectionName);
        System.out.println("Collection: " + collectionName);
        System.out.println("Nº of documents: " + movies.countDocuments());

        client.close();
    }
}