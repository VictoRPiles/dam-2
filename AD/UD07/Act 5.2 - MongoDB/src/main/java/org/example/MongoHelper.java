package org.example;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author victor
 */
@SuppressWarnings("unused")
public class MongoHelper {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

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
            System.out.println(ANSI_GREEN + document.toJson() + ANSI_RESET);
        }
    }

    /**
     * Drop all documents in a collection.
     *
     * @param collection The collection.
     */
    public static void dropAllInCollection(@NotNull MongoCollection<Document> collection) {
        /* https://stackoverflow.com/questions/31058439/how-to-delete-all-documents-in-mongodb-collection-in-java */
        BasicDBObject document = new BasicDBObject();
        collection.deleteMany(document);
        System.out.printf(ANSI_RED + "[%s] Drop all documents in %s.%n" + ANSI_RESET, MongoHelper.class.getSimpleName(), collection.getNamespace());
    }
}
