import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Customer extends User {

    private Wallet wallet;
    private List<Product> cart;

    public Customer(String username, String password, String address, String phoneNumber) {
        super(username, password, address, phoneNumber);
        this.wallet = new Wallet();
        this.cart = new ArrayList<>();
    }
    Scanner scanner=new Scanner(System.in);
    public Wallet getWallet() {
        return wallet;
    }

    public void addToCart(Product product) {
        cart.add(product);
    }

    public void removeFromCart(Product product) {
        cart.remove(product);
    }

    public List<Product> getCart() {
        return cart;
    }

    public void checkout(Shop shop) {
        double totalAmount = cart.stream().mapToDouble(Product::getPrice).sum();
        Order order = new Order(this, cart, totalAmount);
        shop.addOrder(order);
        cart.clear();
        System.out.println("Order placed. Awaiting approval.");
    }

    public void requestAddFunds(double amount, Shop shop) {
        shop.addFundRequest(this, amount);
        System.out.println("Fund request sent.");
    }
    public void searchProducts(Shop shop, String keyword) {
        List<Product> foundProducts = new ArrayList<>();
        for (Category category : shop.getCategories()) {
            searchProductsInCategory(category, keyword, foundProducts);
        }

        if (foundProducts.isEmpty()) {
            System.out.println("No products found matching the keyword: " + keyword);
        } else {
            System.out.println("Products found matching the keyword: " + keyword);
            for (int i = 0; i < foundProducts.size(); i++) {
                System.out.println((i + 1) + ". " + foundProducts.get(i).getName());
                viewProductDetails(foundProducts.get(i));

            }

            System.out.print("Enter the number of the product to add to cart (0 to cancel): ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice > 0 && choice <= foundProducts.size()) {
                Product selectedProduct = foundProducts.get(choice - 1);
                addToCart(selectedProduct);
                System.out.println(selectedProduct.getName() + " added to cart.");
            } else {
                System.out.println("Invalid choice. No product added to cart.");
            }

        }
    }

    private void searchProductsInCategory(Category category, String keyword, List<Product> foundProducts) {

        for (Product product : category.getProducts()) {
            if (product.getName().toLowerCase().contains(keyword.toLowerCase())) {
                foundProducts.add(product);
            }
        }

        for (Category subcategory : category.getSubcategories()) {
            searchProductsInCategory(subcategory, keyword, foundProducts);
        }
    }

    public void viewProductDetails(Product product) {
        System.out.println("Product Details:");
        System.out.println("Name: " + product.getName());
        System.out.println("Price: " + product.getPrice());
        System.out.println("Quantity available: " + product.getQuantity());
        System.out.println("Additional data: " + product.getAdditionalData());
        System.out.println("Category: " + product.getCategory().getName());
    }


    @Override
    public void displayProfile() {
        System.out.println("Customer Profile:");
        System.out.println("Username: " + getUsername());
        System.out.println("Address: " + getAddress());
        System.out.println("Phone: " + getPhoneNumber());
        System.out.println("Wallet Balance: " + wallet.getBalance());
    }
}
