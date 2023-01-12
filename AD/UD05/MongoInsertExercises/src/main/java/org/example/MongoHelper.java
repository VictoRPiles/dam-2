package org.example;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.jetbrains.annotations.NotNull;

/**
 * @author victor
 */
public class MongoHelper {
    private static MongoClient client = new MongoClient();

    public static MongoDatabase getDatabase(String databaseName) {
        return client.getDatabase(databaseName);
    }

    public static @NotNull MongoCollection<Document> getCollection(@NotNull MongoDatabase database, String collectionName) {
        return database.getCollection(collectionName);
    }

    public static void closeConnection(){
        client.close();
    }
}
