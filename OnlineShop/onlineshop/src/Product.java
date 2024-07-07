
import java.util.ArrayList;
import java.util.List;

public class Product {

    private String name;
    private double price;
    private int quantity;
    private String additionalData;
    private Category category;

    public Product(String name, double price, int quantity, String additionalData, Category category) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.additionalData = additionalData;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getAdditionalData() {
        return additionalData;
    }

    public Category getCategory() {
        return category;
    }


    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", additionalData='" + additionalData + '\'' +
                ", category=" + category.getName() +
                '}';
    }
}
