package entity;

import org.bson.types.ObjectId;

import java.util.List;

/**
 * @author victor
 */
public class Article {
    private final ObjectId id;
    private String name;
    private float price;
    private List<String> categories;
    private List<Comment> comments;

    public Article() {
        id = new ObjectId();
    }

    public Article(String name, float price, List<String> categories) {
        id = new ObjectId();
        this.name = name;
        this.price = price;
        this.categories = categories;
        this.comments = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Article article = (Article) o;

        return id.equals(article.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", categories=" + categories +
                ", comments=" + comments +
                '}';
    }

    public ObjectId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
