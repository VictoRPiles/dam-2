package data;

import data.entity.Article;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

class DataAPITest {

    @BeforeEach
    void setUp() {
        DataAPI.init();
    }

    @AfterEach
    void tearDown() {
        DataAPI.close();
    }

    @Test
    @Disabled
    void insertArticle() {
        DataAPI.insertArticle(new Article());
        DataAPI.insertArticle(new Article("Shoes", 50f, List.of("Sport", "Clothing")));
    }

    @Test
    @Disabled
    void insertUser() {
    }

    @Test
    @Disabled
    void findArticle() {
    }

    @Test
    @Disabled
    void findArticleByCategory() {
    }

    @Test
    @Disabled
    void findArticleByName() {
    }

    @Test
    @Disabled
    void findArticleInPriceRange() {
    }

    @Test
    @Disabled
    void findUserByCountry() {
    }

    @Test
    @Disabled
    void orderByPrice() {
    }

    @Test
    @Disabled
    void updateAddress() {
    }

    @Test
    @Disabled
    void updateEmail() {
    }

    @Test
    @Disabled
    void addComment() {
    }

    @Test
    @Disabled
    void deleteArticle() {
    }

    @Test
    @Disabled
    void deleteUser() {
    }
}