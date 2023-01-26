package data;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import data.entity.Address;
import data.entity.Article;
import data.entity.User;
import data.util.Colors;
import data.util.MongoHelper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class DataAPITest {

    @BeforeAll
    static void setUp() {
        DataAPI.init();

        MongoCollection<Object> usersCollection = DataAPI.getCollectionByName(DataAPI.USERS_COLLECTION);
        MongoCollection<Object> articlesCollection = DataAPI.getCollectionByName(DataAPI.ARTICLES_COLLECTION);

        /* Drop all records in these collections for proper testing */
        MongoHelper.dropAllInCollection(usersCollection);
        MongoHelper.dropAllInCollection(articlesCollection);
    }

    @AfterAll
    static void tearDown() {
        DataAPI.close();
    }

    @Test
    void insertArticle() {
        System.out.println("\n########## Test: insertArticle ##########");

        DataAPI.insertArticle(new Article());
        DataAPI.insertArticle(new Article("Shoes", 50f, List.of("Sport", "Clothing")));
    }

    @Test
    void insertUser() {
        System.out.println("\n########## Test: insertUser ##########");

        DataAPI.insertUser(new User());
        DataAPI.insertUser(new User("Test User 1", "test1@test.com", new Address()));
        DataAPI.insertUser(new User("Test User 2", "test2@test.com",
                new Address("Test Street", 1, "Test City", "Test Country")
        ));
    }

    @Test
    void findArticle() {
        System.out.println("\n########## Test: findArticle ##########");

        Article emptyArticle = new Article();
        DataAPI.insertArticle(emptyArticle);
        Article found = DataAPI.findArticle(emptyArticle.getId());
        Colors.printSelectMessage(found.toString());

        Article filledArticle = new Article("Test Article", 100f, List.of("Test Category 1", "Test Category 2"));
        DataAPI.insertArticle(filledArticle);
        Article found2 = DataAPI.findArticle(filledArticle.getId());
        Colors.printSelectMessage(found2.toString());
    }

    @Test
    @SuppressWarnings("DataFlowIssue")
    void findArticleByCategory() {
        System.out.println("\n########## Test: findArticleByCategory ##########");

        Article filledArticle1 = new Article("Test Article Cat 3", 100f, List.of("Test Category 3"));
        Article filledArticle2 = new Article("Test Article Cat 4", 100f, List.of("Test Category 4"));
        Article filledArticle3 = new Article("Test Article Cat 3&4", 100f, List.of("Test Category 3", "Test Category 4"));
        DataAPI.insertArticle(filledArticle1);
        DataAPI.insertArticle(filledArticle2);
        DataAPI.insertArticle(filledArticle3);

        FindIterable<Article> found1 = DataAPI.findArticleByCategory("Test Category 3");
        FindIterable<Article> found2 = DataAPI.findArticleByCategory("Test Category 4");
        FindIterable<Article> found3 = DataAPI.findArticleByCategory("");

        assertThrows(IllegalArgumentException.class, () -> DataAPI.findArticleByCategory(null));

        MongoHelper.printPojoFindIterable(found1);
        MongoHelper.printPojoFindIterable(found2);
        MongoHelper.printPojoFindIterable(found3);
    }

    @Test
    void findArticleByName() {
        System.out.println("\n########## Test: findArticleByName ##########");

        Article articleByName1 = new Article("Name 1", 100f, List.of("Test Category 3"));
        Article articleByName2 = new Article("Name 2", 100f, List.of("Test Category 3"));
        Article articleByName3 = new Article("", 100f, List.of("Test Category 3"));
        Article articleByName4 = new Article(null, 100f, List.of("Test Category 3"));

        DataAPI.insertArticle(articleByName1);
        DataAPI.insertArticle(articleByName2);
        DataAPI.insertArticle(articleByName3);
        DataAPI.insertArticle(articleByName4);

        FindIterable<Article> found1 = DataAPI.findArticleByName("Name");
        FindIterable<Article> found2 = DataAPI.findArticleByName("name");
        FindIterable<Article> found3 = DataAPI.findArticleByName("1");
        FindIterable<Article> found4 = DataAPI.findArticleByName("2");
        FindIterable<Article> found5 = DataAPI.findArticleByName(articleByName2.getName());

        assertThrows(IllegalArgumentException.class, () -> DataAPI.findArticleByName(articleByName4.getName()));

        MongoHelper.printPojoFindIterable(found1);
        MongoHelper.printPojoFindIterable(found2);
        MongoHelper.printPojoFindIterable(found3);
        MongoHelper.printPojoFindIterable(found4);
        MongoHelper.printPojoFindIterable(found5);
    }

    @Test
    void findArticleInPriceRange() {
        System.out.println("\n########## Test: findArticleInPriceRange ##########");
        Article articleByPrice1 = new Article("Article 1", 1000f, List.of("Test Category 3"));
        Article articleByPrice2 = new Article("Article 2", 2000f, List.of("Test Category 3"));
        Article articleByPrice3 = new Article("Article 2", 10000f, List.of("Test Category 3"));
        Article articleByPrice4 = new Article("Article 2", 0f, List.of("Test Category 3"));
        Article articleByPrice5 = new Article("Article 2", null, List.of("Test Category 3"));

        DataAPI.insertArticle(articleByPrice1);
        DataAPI.insertArticle(articleByPrice2);
        DataAPI.insertArticle(articleByPrice3);
        DataAPI.insertArticle(articleByPrice4);
        DataAPI.insertArticle(articleByPrice5);

        FindIterable<Article> found1 = DataAPI.findArticleInPriceRange(999, 1001);
        FindIterable<Article> found2 = DataAPI.findArticleInPriceRange(1000, 1000);
        FindIterable<Article> found3 = DataAPI.findArticleInPriceRange(5000, 10000);

        assertThrows(IllegalArgumentException.class, () -> DataAPI.findArticleInPriceRange(1001, 1000));

        MongoHelper.printPojoFindIterable(found1);
        MongoHelper.printPojoFindIterable(found2);
        MongoHelper.printPojoFindIterable(found3);
    }

    @Test
    @SuppressWarnings("DataFlowIssue")
    void findUserByCountry() {
        System.out.println("\n########## Test: findUserByCountry ##########");

        User userByCountry1 = new User("User 1", "user1@test.com", new Address("Test St.", 1, "Test City", "Test Country"));
        User userByCountry2 = new User("User 2", "user2@test.com", new Address("Test St.", 2, "Test City", "Java Country"));
        User userByCountry3 = new User("User 2", "user2@test.com", null);
        User userByCountry4 = new User("User 2", "user2@test.com", new Address("Test St.", 2, "Java City", null));

        DataAPI.insertUser(userByCountry1);
        DataAPI.insertUser(userByCountry2);
        DataAPI.insertUser(userByCountry3);
        DataAPI.insertUser(userByCountry4);

        FindIterable<User> testCountry = DataAPI.findUserByCountry("Test Country");
        FindIterable<User> javaCountry = DataAPI.findUserByCountry("Java Country");
        FindIterable<User> blankCountry = DataAPI.findUserByCountry("");
        FindIterable<User> unknownCountry = DataAPI.findUserByCountry("Unknown Country");

        assertThrows(IllegalArgumentException.class, () ->  DataAPI.findUserByCountry(null));

        MongoHelper.printPojoFindIterable(testCountry);
        MongoHelper.printPojoFindIterable(javaCountry);
        MongoHelper.printPojoFindIterable(blankCountry);
        MongoHelper.printPojoFindIterable(unknownCountry);
    }

    @Test
    void orderByPrice() {
        System.out.println("\n########## Test: orderByPrice ##########");
        // TODO: 26/1/2023 Code  
    }

    @Test
    void updateAddress() {
        System.out.println("\n########## Test: updateAddress ##########");
        // TODO: 26/1/2023 Code  
    }

    @Test
    void updateEmail() {
        System.out.println("\n########## Test: updateEmail ##########");
        // TODO: 26/1/2023 Code  
    }

    @Test
    void addComment() {
        System.out.println("\n########## Test: addComment ##########");
        // TODO: 26/1/2023 Code  
    }

    @Test
    void deleteArticle() {
        System.out.println("\n########## Test: deleteArticle ##########");
        // TODO: 26/1/2023 Code  
    }

    @Test
    void deleteUser() {
        System.out.println("\n########## Test: deleteUser ##########");
        // TODO: 26/1/2023 Code  
    }
}