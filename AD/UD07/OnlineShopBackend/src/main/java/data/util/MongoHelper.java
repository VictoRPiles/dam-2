package data.util;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.jetbrains.annotations.NotNull;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

/**
 * @author victor
 */
public interface MongoHelper {
    /**
     * Create a {@link CodecRegistry codec registry} for POJOs.
     *
     * @return A CodecRegistry for POJOs.
     * @see PojoCodecProvider
     */
    @NotNull
    static CodecRegistry getPojoCodecRegistry() {
        Colors.printInfoMessage("Creating PojoCodecRegistry....");
        return fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(
                        PojoCodecProvider.builder().automatic(true).build()
                )
        );
    }

    /**
     * Print all {@link Object objects} in an {@link FindIterable iterable}.
     *
     * @param iterable The iterable.
     * @see Colors#printSelectMessage(String)
     */
    @SuppressWarnings("rawtypes")
    static void printPojoFindIterable(@NotNull FindIterable iterable) {
        for (Object pojo : iterable) {
            Colors.printSelectMessage(pojo.toString());
        }
    }

    /**
     * Drop all documents in a {@link MongoCollection<Object> collection}.
     *
     * @param collection The collection.
     */
    static void dropAllInCollection(@NotNull MongoCollection<Object> collection) {
        /* https://stackoverflow.com/questions/31058439/how-to-delete-all-documents-in-mongodb-collection-in-java */
        BasicDBObject document = new BasicDBObject();
        collection.deleteMany(document);
        Colors.printInfoMessage(String.format("[%s] Drop all documents in %s.", MongoHelper.class.getSimpleName(), collection.getNamespace()));
    }
}
