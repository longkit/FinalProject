import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Shop{
    private String name;
    private String url;
    private List<Product> products;
    private List<User> users;
    private List<Order> orders;
    private List<FundRequest> fundRequests;
    private List<Category> categories;

    private List<Category> subcategories;




    public Shop(String name, String url) {
        this.name = name;
        this.url = url;
        this.users = new ArrayList<>();
        this.products = new ArrayList<>();
        this.orders = new ArrayList<>();
        this.fundRequests = new ArrayList<>();
        this.categories = new ArrayList<>();
    }


    public void addCategory(Category category) {
        categories.add(category);
    }

    public List<Category> getCategories() {
        return categories;
    }

    public List<Category> getSubcategories() {
        return subcategories;
    }

    public void addUser(User user) {
        users.add(user);

    }

    public void addOrder(Order order) {
        orders.add(order);

    }

    public void addFundRequest(Customer customer, double amount) {
        fundRequests.add(new FundRequest(customer, amount));

    }

    public void processOrder(Order order) {
        Customer customer = order.getCustomer();
        if (customer.getWallet().getBalance() >= order.getTotalAmount()) {
            customer.getWallet().deduct(order.getTotalAmount());
            User seller = findSellerForOrder(order);
            if (seller instanceof Seller) {
                Seller sellerObj = (Seller) seller;
                sellerObj.getWallet().addFunds(order.getTotalAmount() * 0.90);
                order.getProducts().forEach(product -> product.setQuantity(product.getQuantity() - 1));
            }

            System.out.println("Order processed and approved.");
        } else {
            System.out.println("Insufficient funds. Order canceled.");
            orders.remove(order);
        }
    }
    public void approveSeller(Seller seller) {
        seller.setApproved(true);

    }
    private User findSellerForOrder(Order order) {

        return users.stream().filter(user -> user instanceof Seller).findFirst().orElse(null);
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public List<FundRequest> getFundRequests() {
        return fundRequests;
    }


}
