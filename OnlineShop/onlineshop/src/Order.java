
import java.util.List;

public class Order {
    private Customer customer;
    private List<Product> products;
    private double totalAmount;
    private boolean approved;

    public Order(Customer customer, List<Product> products, double totalAmount) {
        this.customer = customer;
        this.products = products;
        this.totalAmount = totalAmount;
        this.approved = false;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
