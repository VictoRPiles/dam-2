package data.util;

import com.mongodb.MongoClientSettings;
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
}
