package data;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import data.entity.Address;
import data.entity.Article;
import data.entity.Comment;
import data.entity.User;
import data.util.Colors;
import data.util.MongoHelper;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.types.ObjectId;
import org.jetbrains.annotations.NotNull;

/**
 * @author victor
 */
@SuppressWarnings("unused")
public abstract class DataAPI {
    private final static String DATABASE_NAME = "act5_3";
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
        CodecRegistry pojoCodecRegistry = MongoHelper.getPojoCodecRegistry();
        db = client
                .getDatabase(DATABASE_NAME)
                .withCodecRegistry(pojoCodecRegistry);
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
        Colors.printInfoMessage("Inserting " + article + "...");
        MongoCollection<Article> collection = db.getCollection("articles", Article.class);
        collection.insertOne(article);
    }

    public static void insertUser(User user) {
        // TODO: 25/1/2023
    }

    public static Article findArticle(ObjectId id) {
        // TODO: 25/1/2023
        return null;
    }

    /**
     * @param category The category.
     * @return All the articles that are of the category.
     */
    public static FindIterable<Article> findArticleByCategory(String category) {
        // TODO: 25/1/2023
        return null;
    }

    /**
     * @param string The string.
     * @return All the articles that contains the string in its name.
     */
    public static FindIterable<Article> findArticleByName(String string) {
        // TODO: 25/1/2023
        return null;
    }

    /**
     * @param low  The min value.
     * @param high The max value.
     * @return All the articles whose price is in the range [low, high], both inclusive.
     */
    public static FindIterable<Article> findArticleInPriceRange(double low, double high) {
        // TODO: 25/1/2023
        return null;
    }

    /**
     * @param country The country.
     * @return All the users who live in the country specified as argument.
     */
    public static FindIterable<User> findUserByCountry(String country) {
        // TODO: 25/1/2023
        return null;
    }

    /**
     * Receives a {@link FindIterable<Article> FindIterable<Article>} object and returns it ordered by price ascending
     * or descending as specified as argument.
     *
     * @param articles  The articles.
     * @param ascendent The order.
     * @return The {@link FindIterable<Article> FindIterable<Article>} object ordered by price.
     */
    public static FindIterable<Article> orderByPrice(FindIterable<Article> articles, boolean ascendent) {
        // TODO: 25/1/2023
        return null;
    }

    public static void updateAddress(User user, Address address) {
        // TODO: 25/1/2023
    }

    public static void updateEmail(User user, String email) {
        // TODO: 25/1/2023
    }

    /**
     * Check that {@link Comment#getUserId() newComment.userId} exists as the {@link User#getId() id of a user} in the
     * Users collection. In case of inexistence, do not add the comment but inform that the user does not exist.
     *
     * @param article    The article.
     * @param newComment The new comment.
     */
    public static void addComment(Article article, Comment newComment) {
        // TODO: 25/1/2023
    }

    /**
     * Delete an article.
     *
     * @param article The article to delete.
     */
    public static void deleteArticle(Article article) {
        // TODO: 25/1/2023
    }

    /**
     * Delete a {@link User user} and also all the {@link Comment comments} whose {@link Comment#getUserId() author} is
     * the user.
     *
     * @param user The user to delete.
     */
    public static void deleteUser(User user) {
        // TODO: 25/1/2023
    }
}
