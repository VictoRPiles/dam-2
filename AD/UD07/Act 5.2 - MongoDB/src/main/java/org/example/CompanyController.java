package org.example;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.*;

/**
 * @author victor
 */
public class CompanyController implements Runnable {
    private final MongoDatabase database;

    public CompanyController(MongoDatabase database) {
        this.database = database;
    }

    @Override
    public void run() {
        /* In our social network you can also register companies, which we keep in the collection "company" ... */
        MongoCollection<Document> collection = database.getCollection("company");

        MongoHelper.dropAllInCollection(collection);

        /* ... insert the company "Gardening Gardenia" with identifier 10 */
        collection.insertOne(new Document("_id", 10L).append("name", "Gardening Gardenia"));

        /*
        Now you must update the data of the company "Gardening Gardenia" adding the following fields:
        - Address. It must be an embedded document with street Palmeras, number 8 and town Torrente.
        - Sector: services.
        - Web: http: //www.gardeninggardenia.com
        */
        collection.updateOne(
                eq("name", "Gardening Gardenia"),
                set("address", new Document().append("street", "Palmeras").append("number", 8).append("town", "Torrente"))
        );
        collection.updateOne(
                eq("name", "Gardening Gardenia"),
                set("sector", "services")
        );
        collection.updateOne(
                eq("name", "Gardening Gardenia"),
                set("web", "http: //www.gardeninggardenia.com")
        );

        /* We are going to count the followers of the companies of the social network, using a field "followers" ... */
        /* ... Five users have marked FOLLOW the company "Gardening Gardenia" ... */
        collection.updateOne(
                eq("name", "Gardening Gardenia"),
                set("followers", 5)
        );

        /* ... Then two people have followed the company. Type the command to increment it ... */
        collection.updateOne(
                eq("name", "Gardening Gardenia"),
                inc("followers", 2)
        );

        /* ... Finally, one has unfollowed. Also type the command to decrease it ... */
        collection.updateOne(
                eq("name", "Gardening Gardenia"),
                inc("followers", -1)
        );

        /* Update the address of the company "Gardening Gardenia", add the postal code 46009 */
        collection.updateOne(
                eq("name", "Gardening Gardenia"),
                set("address.postal", 46009)
        );

        /* Eliminate the field "sector" of the company "Gardening Gardenia", leaving intact the rest of the fields */
        collection.updateOne(
                eq("name", "Gardening Gardenia"),
                unset("sector")
        );
    }
}
