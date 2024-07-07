import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;
public class Seller extends User {

    private Wallet wallet;
    private List<Product> products;
    private boolean isApproved;

    public Seller(String username, String password, String address, String phoneNumber) {
        super(username, password, address, phoneNumber);
        this.wallet = new Wallet();
        this.products = new ArrayList<>();
    }
Scanner scanner = new Scanner(System.in);
    public Wallet getWallet() {
        return wallet;
    }

    public void addProduct(Product product) {
        products.add(product);

    }
    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public void displayProfile() {
        System.out.println("Seller Profile:");
        System.out.println("Username: " + getUsername());
        System.out.println("Address: " + getAddress());
        System.out.println("Phone: " + getPhoneNumber());
        System.out.println("Wallet Balance: " + wallet.getBalance());
        System.out.println("Products: " + products.toString());
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }


    public void addNewProduct(Shop shop) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product name: ");
        String productName = scanner.nextLine();
        System.out.print("Enter product price: ");
        double productPrice = scanner.nextDouble();
        System.out.print("Enter product quantity: ");
        int productQuantity = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter product additional data: ");
        String productAdditionalData = scanner.nextLine();


        Category selectedCategory = selectCategory(shop);
        if (selectedCategory != null) {
            System.out.println("Selected category: " + selectedCategory.getName());
            Category selectedSubcategory = selectSubcategory(selectedCategory);
            if (selectedSubcategory != null) {
                System.out.println("Selected subcategory: " + selectedSubcategory.getName());
                Product product = new Product(productName, productPrice, productQuantity, productAdditionalData, selectedSubcategory);
                addProduct(product);
                selectedSubcategory.addProduct(product);

                System.out.println("Product added successfully.");
            } else {
                System.out.println("Invalid subcategory.");
            }
        } else {
            System.out.println("Invalid category.");
        }


    }

    private Category selectCategory(Shop shop) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a category:");
        List<Category> categories = shop.getCategories();
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i).getName());
        }
        System.out.print("Enter category number: ");
        int categoryNumber = scanner.nextInt();
        scanner.nextLine();
        if (categoryNumber > 0 && categoryNumber <= categories.size()) {
            return categories.get(categoryNumber - 1);
        } else {
            return null;
        }
    }

    private Category selectSubcategory(Category category) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a subcategory:");
        List<Category> subcategories = category.getSubcategories();
        for (int i = 0; i < subcategories.size(); i++) {
            System.out.println((i + 1) + ". " + subcategories.get(i).getName());
        }
        System.out.print("Enter subcategory number: ");
        int subcategoryNumber = scanner.nextInt();
        scanner.nextLine();
        if (subcategoryNumber > 0 && subcategoryNumber <= subcategories.size()) {
            return subcategories.get(subcategoryNumber - 1);
        } else {
            return null;
        }
    }
}
