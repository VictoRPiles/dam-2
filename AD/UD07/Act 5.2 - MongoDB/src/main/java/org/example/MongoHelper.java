package org.example;

import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author victor
 */
@SuppressWarnings("unused")
public class MongoHelper {
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
}
