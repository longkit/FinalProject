import java.util.List;
import java.util.Scanner;
public class Admin extends User {

    public Admin(String username, String password, String address, String phoneNumber) {
        super(username, password, address, phoneNumber);
    }

    @Override
    public void displayProfile() {
        System.out.println("Customer Profile:");
        System.out.println("Username: " + getUsername());
        System.out.println("Address: " + getAddress());
        System.out.println("Phone: " + getPhoneNumber());
    }

    public void approveOrder(Order order, Shop shop) {
        order.setApproved(true);
        shop.processOrder(order);
    }

    public void viewUserProfile(User user) {
        if (user != null) {
            user.displayProfile();
        } else {
            System.out.println("User not found.");
        }
    }

    public void approveFundRequest(FundRequest request) {
        request.getCustomer().getWallet().addFunds(request.getAmount());
        System.out.println("Fund request approved. Added " + request.getAmount() + " to " + request.getCustomer().getUsername() + "'s wallet.");
    }

    public void addAdmin(Shop shop, Admin admin) {
        shop.addUser(admin);
    }

    public void addSeller(Shop shop, Seller seller) {
        shop.addUser(seller);
    }

    public void viewAllOrders(Shop shop) {
        List<Order> orders = shop.getOrders();
        orders.forEach(order -> {
            System.out.println("Order by: " + order.getCustomer().getUsername());
            System.out.println("Products: " + order.getProducts());
            System.out.println("Total amount: " + order.getTotalAmount());
            System.out.println("Approved: " + order.isApproved());
            System.out.println();
        });
    }

    public void viewAllSellers(Shop shop) {
        List<User> users = shop.getUsers();
        users.stream()
                .filter(user -> user instanceof Seller)
                .forEach(user -> user.displayProfile());
    }

    public void viewAllCustomerOrders(Shop shop, Customer customer) {
        List<Order> orders = shop.getOrders();
        orders.stream()
                .filter(order -> order.getCustomer().equals(customer))
                .forEach(order -> {
                    System.out.println("Order by: " + order.getCustomer().getUsername());
                    System.out.println("Products: " + order.getProducts());
                    System.out.println("Total amount: " + order.getTotalAmount());
                    System.out.println("Approved: " + order.isApproved());
                    System.out.println();
                });
    }
    public void viewAllUsers(Shop shop) {
        List<User> users = shop.getUsers();
        for (int i = 0; i < users.size(); i++) {
            System.out.println((i + 1) + ". " + users.get(i).getUsername());
        }
        Scanner scanner=new Scanner(System.in);
        System.out.print("Choose a user to view details (enter index): ");
        int userIndex = scanner.nextInt();
        scanner.nextLine();

        if (userIndex > 0 && userIndex <= users.size()) {
            User selectedUser = users.get(userIndex - 1);
            selectedUser.displayProfile();
        } else {
            System.out.println("Invalid user index.");
        }
    }

    public void viewTotalRevenue(Shop shop) {
        double totalRevenue = shop.getOrders().stream()
                .filter(Order::isApproved)
                .mapToDouble(Order::getTotalAmount)
                .sum();
        System.out.println("Total Revenue: " + totalRevenue*0.1);
    }
}
