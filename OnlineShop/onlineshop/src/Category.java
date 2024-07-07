

import java.util.ArrayList;
import java.util.List;

public class Category {

    private String name;
    private List<Product> products;
    private List<Category> subcategories;

    public Category(String name) {
        this.name = name;
        this.products = new ArrayList<>();
        this.subcategories = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Category> getSubcategories() {
        return subcategories;
    }

    public void addProduct(Product product) {
        products.add(product);


    }

    public void removeProduct(Product product) {
        products.remove(product);

    }

    public void addSubcategory(Category category) {
        subcategories.add(category);
    }

    public void removeSubcategory(Category category) {
        subcategories.remove(category);
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", products=" + products +
                ", subcategories=" + subcategories +
                '}';
    }
}
