package org.example;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

/**
 * @author victor
 */
public class UserController implements Runnable {
    private final MongoDatabase database;

    public UserController(MongoDatabase database) {
        this.database = database;
    }

    @Override
    public void run() {
        /* Insert users */
        MongoCollection<Document> collection = database.getCollection("users");

        MongoHelper.dropAllInCollection(collection);

        List<Document> users = new ArrayList<>();
        users.add(new Document()
                .append("_id", 5L)
                .append("name", "Juan")
                .append("surname", "García Castellano")
                .append("age", 23)
                .append("gender", "M")
                .append("registration", new Date())
        );
        users.add(new Document()
                .append("_id", 6L)
                .append("name", "Beatriz")
                .append("surname", "Perez Solaz")
                .append("age", 27)
                .append("gender", "F")
                .append("registration", new Date())
        );
        collection.insertMany(users);

        /* Retrieve all users */
        Bson filter = new Document();
        System.out.println("[+] Retrieve all users.");
        FindIterable<Document> result = collection.find(filter);
        MongoHelper.printFindIterable(result);

        /* Insert the new user Jorge Lopez Sevilla */
        Document jorge = new Document()
                .append("_id", 7L)
                .append("name", "Jorge")
                .append("surname", "Lopez Sevilla")
                .append("gender", "M")
                .append("registration", new Date())
                .append("groups", List.of("basketball", "kitchen", "historical novel"));
        collection.insertOne(jorge);

        /* Juan García, with identifier 5 is unsubscribed. Write the sentence to delete it */
        collection.deleteOne(eq("_id", 5L));

        /* The user Beatriz, with identifier 6, signs up for two groups: "historical novel" and "dance" */
        collection.updateOne(
                eq("_id", 6),
                set("groups", List.of("historical novel", "dance"))
        );

        /*
        The user Beatriz, with identifier 6, signs up for the group "theater". Write the command to add that group to its array.
        Docs: https: //www.mongodb.com/docs/manual/reference/operator/update/addToSet/
        */
        collection.updateOne(
                eq("_id", 6),
                addToSet("groups", "theater")
        );

        /*
        The user with identifier 6 is deleted from the "dance" group. Write the statement to remove it from its group array.
        */
        collection.updateOne(
                eq("_id", 6),
                pull("groups", "dance")
        );

        /* As in any social network, users can enter comments. */
        filter = new Document().append("name", "Jorge").append("surname", "Lopez Sevilla");
        Bson set = set(
                "comments",
                List.of(new Document()
                        .append("title", "New historical novel comment title")
                        .append("text", "New historical novel comment text")
                        .append("group", "historical novel")
                        .append("date", new Date()))
        );
        collection.updateOne(filter, set);
        collection.updateOne(filter, inc("total_coments", 1));

        filter = new Document().append("name", "Jorge").append("surname", "Lopez Sevilla");
        Bson push = push(
                "comments",
                List.of(new Document()
                        .append("title", "New basketball comment title")
                        .append("text", "New basketball comment text")
                        .append("group", "basketball")
                        .append("date", new Date()))
        );
        collection.updateOne(filter, push);
        collection.updateOne(filter, inc("total_coments", 1));

        /*
        Write the commands to retrieve the following information:
        */
        FindIterable<Document> documents;

        /* Name, surname, age and _id of those users that belong to “historical novel” group and are older than 25 years old. */
        System.out.println("[+] Name, surname, age and _id of those users that belong to “historical novel” group and are older than 25 years old.");
        filter = and(eq("groups", "historical novel"), gt("age", 25L));
        Bson project = and(eq("name", 1L), eq("surname", 1L), eq("age", 1L), eq("_id", 1L));
        documents = collection.find(filter).projection(project);
        MongoHelper.printFindIterable(documents);

        /* Name, surname and groups (but not the _id) of those users belonging to at least 2 groups. */
        System.out.println("[+] Name, surname and groups (but not the _id) of those users belonging to at least 2 groups.");
        filter = where("this.groups.length >= 2");
        project = and(eq("_id", 0L), eq("name", 1L), eq("surname", 1L), eq("groups", 1L));
        documents = collection.find(filter).projection(project);
        MongoHelper.printFindIterable(documents);

        /* Name, surname and groups (but not the _id) of those users belonging to “historical novel” group and “theater”. */
        System.out.println("[+] Name, surname and groups (but not the _id) of those users belonging to “historical novel” group and “theater”. ");
        filter = and(eq("groups", "historical novel"), eq("groups", "theater"));
        project = and(eq("_id", 0L), eq("name", 1L), eq("surname", 1L), eq("groups", 1L));
        documents = collection.find(filter).projection(project);
        MongoHelper.printFindIterable(documents);

        /* Name, surname of those users that have a field named comments. */
        System.out.println("[+] Name, surname of those users that have a field named comments.");
        filter = exists("comments", true);
        project = and(eq("name", 1L), eq("surname", 1L));
        documents = collection.find(filter).projection(project);
        MongoHelper.printFindIterable(documents);

        /* Name of the companies that are in Torrente and have zero followers. */
        System.out.println("[+] Name of the companies that are in Torrente and have zero followers.");
        filter = and(eq("address.town", "Torrente"), eq("followers", 0L));
        project = eq("name", 1L);
        documents = collection.find(filter).projection(project);
        MongoHelper.printFindIterable(documents);

        /* Name of the companies that are in Torrente and have more than 5 followers. */
        System.out.println("[+] Name of the companies that are in Torrente and have more than 5 followers.");
        filter = and(eq("address.town", "Torrente"), gt("followers", 5L));
        project = eq("name", 1L);
        documents = collection.find(filter).projection(project);
        MongoHelper.printFindIterable(documents);
    }
}
