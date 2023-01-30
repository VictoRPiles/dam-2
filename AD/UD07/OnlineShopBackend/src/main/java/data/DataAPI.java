package data;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import data.entity.Address;
import data.entity.Article;
import data.entity.Comment;
import data.entity.User;
import data.util.Colors;
import data.util.MongoHelper;
import org.bson.types.ObjectId;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.TestOnly;
import org.jetbrains.annotations.VisibleForTesting;

import java.util.regex.Pattern;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Sorts.descending;
import static com.mongodb.client.model.Updates.push;
import static com.mongodb.client.model.Updates.set;

/**
 * @author victor
 */
@SuppressWarnings("unused")
public abstract class DataAPI {
    public static final String DATABASE_NAME = "act5_3";
    public static final String USERS_COLLECTION = "users";
    public static final String ARTICLES_COLLECTION = "articles";

    private static MongoClient client;
    private static MongoDatabase db;

    /**
     * Initialize the {@link MongoClient mongo client} and the {@link MongoDatabase database}
     */
    public static void init() {
        Colors.printInfoMessage("Connecting to Mongo database (mongodb://localhost:27017/)...");
        client = new MongoClient(
                new MongoClientURI(
                        "mongodb://localhost:27017/"
                )
        );
        db = client.getDatabase(DATABASE_NAME)
                .withCodecRegistry(MongoHelper.getPojoCodecRegistry());
    }

    /**
     * Close the {@link MongoClient mongo client}.
     *
     * @see MongoClient#close()
     */
    public static void close() {
        Colors.printInfoMessage("Closing database connection...");
        client.close();
    }

    /**
     * Insert a new {@link Article article} in the "articles" collection.
     *
     * @param article The new article.
     * @see MongoCollection#insertOne(Object)
     */
    public static void insertArticle(@NotNull Article article) {
        Colors.printQueryMessage("Inserting " + article + "...");
        MongoCollection<Article> collection = db.getCollection(ARTICLES_COLLECTION, Article.class);
        collection.insertOne(article);
    }

    /**
     * Insert a new {@link User user} in the "users" collection.
     *
     * @param user The new user..
     * @see MongoCollection#insertOne(Object)
     */
    public static void insertUser(@NotNull User user) {
        Colors.printQueryMessage("Inserting " + user + "...");
        MongoCollection<User> collection = db.getCollection(USERS_COLLECTION, User.class);
        collection.insertOne(user);
    }

    /**
     * Search an article by {@link Article#getId() id}.
     *
     * @param id The id of the article.
     * @return The article object.
     */
    public static @Nullable Article findArticle(@NotNull ObjectId id) {
        Colors.printQueryMessage("Searching for article with id: " + id + "...");
        MongoCollection<Article> collection = db.getCollection(ARTICLES_COLLECTION, Article.class);
        return collection.find(eq("_id", id)).first();
    }

    /**
     * Search for articles by {@link Article#getCategories() category}.
     *
     * @param category The category.
     * @return All the articles that are of the category.
     */
    public static @NotNull FindIterable<Article> findArticleByCategory(@NotNull String category) {
        Colors.printQueryMessage("Searching for articles with category: " + category + "...");
        MongoCollection<Article> collection = db.getCollection(ARTICLES_COLLECTION, Article.class);
        return collection.find(eq("categories", category));
    }

    /**
     * Search for the articles that contains the string in its {@link Article#getName() name}.
     *
     * @param string The string.
     * @return All the articles that contains the string in its name.
     */
    public static @NotNull FindIterable<Article> findArticleByName(@NotNull String string) {
        Colors.printQueryMessage("Searching for articles with '" + string + "' in its name ...");
        MongoCollection<Article> collection = db.getCollection(ARTICLES_COLLECTION, Article.class);
        return collection.find(regex("name", Pattern.compile(string)));
    }

    /**
     * Search for the articles whose {@link Article#getPrice() price} is in the range [low, high], both inclusive.
     *
     * @param low  The min value.
     * @param high The max value.
     * @return All the articles whose price is in the range [low, high], both inclusive.
     */
    public static @NotNull FindIterable<Article> findArticleInPriceRange(double low, double high) {
        Colors.printQueryMessage(String.format("Searching for articles whose price is in the range [%s, %s], both inclusive...", low, high));

        if (low > high) throw new IllegalArgumentException("Low value must be lower than high value");

        MongoCollection<Article> collection = db.getCollection(ARTICLES_COLLECTION, Article.class);
        return collection.find(and(gte("price", low), lte("price", high)));
    }

    /**
     * @param country The country.
     * @return All the users who live in the country specified as argument.
     */
    public static @NotNull FindIterable<User> findUserByCountry(@NotNull String country) {
        Colors.printQueryMessage("Searching for users who live in: " + country + "...");
        MongoCollection<User> collection = db.getCollection(USERS_COLLECTION, User.class);
        return collection.find(eq("address.country", country));
    }

    /**
     * Receives a {@link FindIterable<Article> FindIterable<Article>} object and returns it ordered by price ascending
     * or descending as specified as argument.
     *
     * @param articles  The articles.
     * @param ascendent The order.
     * @return The {@link FindIterable<Article> FindIterable<Article>} object ordered by price.
     */
    public static @NotNull FindIterable<Article> orderByPrice(@NotNull FindIterable<Article> articles, boolean ascendent) {
        String oderDirection = ascendent ? "ascendent" : "descendent";
        Colors.printQueryMessage("Sorting articles in " + oderDirection + " order...");
        return ascendent ? articles.sort(ascending("price")) : articles.sort(descending("price"));
    }

    /**
     * Update the {@link User#getAddress() address of an user}.
     *
     * @param user    The user.
     * @param address The new address.
     */
    public static void updateAddress(@NotNull User user, Address address) {
        Colors.printQueryMessage("Updating address for user: " + user + "...");
        MongoCollection<User> collection = db.getCollection(USERS_COLLECTION, User.class);
        collection.updateOne(eq("_id", user.getId()), set("address", address));
    }

    /**
     * Update the {@link User#getEmail() email of an user}.
     *
     * @param user  The user.
     * @param email The new email.
     */
    public static void updateEmail(@NotNull User user, String email) {
        Colors.printQueryMessage("Updating email for user: " + user + "...");
        MongoCollection<User> collection = db.getCollection(USERS_COLLECTION, User.class);
        collection.updateOne(eq("_id", user.getId()), set("email", email));
    }

    /**
     * Check that {@link Comment#getUserId() newComment.userId} exists as the {@link User#getId() id of a user} in the
     * Users collection.
     * <p>
     * In case of inexistence, do not add the comment but inform that the user does not exist.
     *
     * @param article    The article.
     * @param newComment The new comment.
     */
    public static void addComment(@NotNull Article article, @NotNull Comment newComment) {
        Colors.printQueryMessage("Adding new comment: '" + newComment + "' in article: " + article + "...");
        MongoCollection<Article> articlesCollection = db.getCollection(ARTICLES_COLLECTION, Article.class);
        MongoCollection<User> usersCollection = db.getCollection(USERS_COLLECTION, User.class);

        User userWithId = usersCollection.find(eq("_id", newComment.getUserId())).first();
        if (userWithId == null) {
            Colors.printInfoMessage("User with id " + newComment.getUserId() + " not found.");
            throw new MongoException("User with id " + newComment.getUserId() + " not found.");
        }

        articlesCollection.updateOne(eq("_id", article.getId()), push("comment", newComment));
    }

    /**
     * Delete an article.
     *
     * @param article The article to delete.
     */
    public static void deleteArticle(@NotNull Article article) {
        Colors.printQueryMessage(String.format("Deleting article: " + article + " ..."));
        MongoCollection<Article> collection = db.getCollection(ARTICLES_COLLECTION, Article.class);
        long deletedCount = collection.deleteOne(eq("_id", article.getId())).getDeletedCount();
        if (deletedCount == 0) {
            Colors.printInfoMessage(String.format("Article: " + article + " doesn't exist."));
        }
    }

    /**
     * Delete a {@link User user} and also all the {@link Comment comments} whose {@link Comment#getUserId() author} is
     * the user.
     *
     * @param user The user to delete.
     */
    public static void deleteUser(@NotNull User user) {
        Colors.printQueryMessage(String.format("Deleting user: " + user + " ..."));
        MongoCollection<User> usersCollection = db.getCollection(USERS_COLLECTION, User.class);
        long deletedCount = usersCollection.deleteOne(eq("_id", user.getId())).getDeletedCount();
        if (deletedCount == 0) {
            Colors.printInfoMessage(String.format("User: " + user + " doesn't exist."));
            return;
        }

        Colors.printQueryMessage("Deleting comments ...");
        MongoCollection<Article> articlesCollection = db.getCollection(ARTICLES_COLLECTION, Article.class);
        articlesCollection.deleteMany(eq("comments.userId", user.getId()));
    }

    @TestOnly
    @VisibleForTesting
    protected static @NotNull MongoCollection<Object> getCollectionByName(@NotNull String collectionName) {
        return db.getCollection(collectionName, Object.class);
    }
}
