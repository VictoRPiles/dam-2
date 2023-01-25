package org.example;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

/**
 * @author victor
 */
@SuppressWarnings("unused")
public class MongoHelper {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";

    /**
     * Suppress MongoDB Driver logs.
     */
    public static void suppressLogs() {
        Logger logger = Logger.getLogger("org.mongodb.driver");
        logger.setLevel(Level.SEVERE);
    }

    /**
     * Close the database connection.
     *
     * @param client The MongoDB client.
     */
    public static void close(@NotNull MongoClient client) {
        System.out.printf(ANSI_RED + "[%s] Closing database connection...%n" + ANSI_RESET, MongoHelper.class.getSimpleName());
        client.close();
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
     * Print all {@link Object objects} in an {@link FindIterable iterable}.
     *
     * @param iterable The iterable.
     */
    @SuppressWarnings("rawtypes")
    public static void printPojoFindIterable(@NotNull FindIterable iterable) {
        for (Object pojo : iterable) {
            System.out.println(ANSI_GREEN + pojo + ANSI_RESET);
        }
    }

    /**
     * Drop all documents in a {@link MongoCollection<Document> collection}.
     *
     * @param collection The collection.
     */
    public static void dropAllInCollection(@NotNull MongoCollection<Document> collection) {
        /* https://stackoverflow.com/questions/31058439/how-to-delete-all-documents-in-mongodb-collection-in-java */
        BasicDBObject document = new BasicDBObject();
        collection.deleteMany(document);
        System.out.printf(ANSI_RED + "[%s] Drop all documents in %s.%n" + ANSI_RESET, MongoHelper.class.getSimpleName(), collection.getNamespace());
    }

    /**
     * Drop all documents matching a {@link Bson filter} in a {@link MongoCollection collection}.
     *
     * @param collection The collection.
     */
    @SuppressWarnings("rawtypes")
    public static void dropInCollection(@NotNull MongoCollection collection, Bson filter) {
        System.out.printf(ANSI_RED + "[%s] Drop %s documents in %s.%n" + ANSI_RESET, MongoHelper.class.getSimpleName(), filter, collection.getNamespace());
        collection.deleteMany(filter);
    }

    /**
     * Create a {@link CodecRegistry codec registry} for POJOs.
     *
     * @return A CodecRegistry for POJOs.
     * @see PojoCodecProvider
     */
    @NotNull
    public static CodecRegistry getPojoCodecRegistry() {
        System.out.printf(ANSI_RED + "[%s] Creating PojoCodecRegistry....%n" + ANSI_RESET, MongoHelper.class.getSimpleName());
        return fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(
                        PojoCodecProvider.builder().automatic(true).build()
                )
        );
    }
}
