import java.util.List;
import java.util.Scanner;

public class Main {

    private static void displaySubcategories(Category category) {
        System.out.println("Subcategories in " + category.getName() + ":");
        List<Category> subcategories = category.getSubcategories();
        for (int i = 0; i < subcategories.size(); i++) {
            System.out.println((i + 1) + ". " + subcategories.get(i).getName());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Shop shop = new Shop("My Shop", "www.myshop.org");


        Admin defaultAdmin = new Admin("admin", "admin123", "address", "0000000000");
        shop.addUser(defaultAdmin);


        Category electronics = new Electronics();
        Category clothing = new Clothing();
        Category books = new Books();
        Category home = new Home();
        Category beauty = new Beauty();

        shop.addCategory(electronics);
        shop.addCategory(clothing);
        shop.addCategory(books);
        shop.addCategory(home);
        shop.addCategory(beauty);

        while (true) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            if (option == 1) {
                System.out.println("1. Customer");
                System.out.println("2. Seller");
                System.out.print("Choose a role: ");
                int role = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                System.out.print("Enter address: ");
                String address = scanner.nextLine();
                System.out.print("Enter phone number: ");
                String phoneNumber = scanner.nextLine();

                if (role == 1) {
                    Customer customer = new Customer(username, password, address, phoneNumber);
                    shop.addUser(customer);

                } else if (role == 2) {
                    Seller seller = new Seller(username, password, address, phoneNumber);
                    shop.addUser(seller);
                }
                System.out.println("Registration successful.");

            } else if (option == 2) {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                User user = shop.getUsers().stream()
                        .filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password))
                        .findFirst()
                        .orElse(null);

                if (user != null) {
                    if (user instanceof Admin) {
                        Admin admin = (Admin) user;
                        while (true) {
                            System.out.println("1. View User Profile");
                            System.out.println("2. Approve Order");
                            System.out.println("3. Approve Fund Request");
                            System.out.println("4. Add Admin");
                            System.out.println("5. Add Seller");
                            System.out.println("6. View All Orders");
                            System.out.println("7. View All Sellers");
                            System.out.println("8. View Total Revenue");
                            System.out.println("9. View All Users");
                            System.out.println("10. Logout");
                            System.out.println("11.Update Profile");
                            System.out.println("12.Approve Seller");
                            System.out.println("Choose an option: ");
                            int adminOption = scanner.nextInt();
                            scanner.nextLine();

                            if (adminOption == 1) {
                                System.out.println("Users:");
                                for (User u : shop.getUsers()) {
                                    System.out.println(u.getUsername());
                                }
                                System.out.print("Enter username to view: ");
                                String viewUsername = scanner.nextLine();
                                User viewUser = shop.getUsers().stream()
                                        .filter(u -> u.getUsername().equals(viewUsername))
                                        .findFirst()
                                        .orElse(null);
                                admin.viewUserProfile(viewUser);

                            } else if (adminOption == 2) {
                                System.out.println("Orders:");
                                int i =0;
                                for (Order o : shop.getOrders()) {
                                    System.out.println(i + "." +o.getCustomer().getUsername() + ": " + o.getTotalAmount());
                               i++; }
                                System.out.print("Enter order index to approve: ");
                                int orderIndex = scanner.nextInt();
                                scanner.nextLine();
                                if (orderIndex >= 0 && orderIndex < shop.getOrders().size()) {
                                    Order order = shop.getOrders().get(orderIndex);
                                    admin.approveOrder(order, shop);
                                } else {
                                    System.out.println("Invalid order index.");
                                }

                            } else if (adminOption == 3) {
                                System.out.println(shop.getFundRequests());
                                System.out.print("Enter fund request index to approve: ");
                                int requestIndex = scanner.nextInt();
                                scanner.nextLine();
                                if (requestIndex >= 0 && requestIndex < shop.getFundRequests().size()) {
                                    FundRequest request = shop.getFundRequests().get(requestIndex);
                                    admin.approveFundRequest(request);
                                } else {
                                    System.out.println("Invalid fund request index.");
                                }

                            } else if (adminOption == 4) {
                                System.out.print("Enter new admin username: ");
                                String newAdminUsername = scanner.nextLine();
                                System.out.print("Enter new admin password: ");
                                String newAdminPassword = scanner.nextLine();
                                System.out.print("Enter new admin address: ");
                                String newAdminAddress = scanner.nextLine();
                                System.out.print("Enter new admin phone number: ");
                                String newAdminPhoneNumber = scanner.nextLine();
                                Admin newAdmin = new Admin(newAdminUsername, newAdminPassword, newAdminAddress, newAdminPhoneNumber);
                                shop.addUser(newAdmin);

                            } else if (adminOption == 5) {
                                System.out.print("Enter new seller username: ");
                                String newSellerUsername = scanner.nextLine();
                                System.out.print("Enter new seller password: ");
                                String newSellerPassword = scanner.nextLine();
                                System.out.print("Enter new seller address: ");
                                String newSellerAddress = scanner.nextLine();
                                System.out.print("Enter new seller phone number: ");
                                String newSellerPhoneNumber = scanner.nextLine();
                                Seller newSeller = new Seller(newSellerUsername, newSellerPassword, newSellerAddress, newSellerPhoneNumber);
                                shop.addUser(newSeller);

                            } else if (adminOption == 6) {
                                admin.viewAllOrders(shop);

                            } else if (adminOption == 7) {
                                admin.viewAllSellers(shop);

                            } else if (adminOption == 8) {
                                admin.viewTotalRevenue(shop);

                            } else if (adminOption == 9) {
                                admin.viewAllUsers(shop);

                            } else if (adminOption == 10) {
                                break;
                            }
                            else if (adminOption == 11) {
                                System.out.print("Enter new Username: ");
                                String newUsername = scanner.nextLine();
                                System.out.print("Enter new password: ");
                                String newPassword = scanner.nextLine();
                                System.out.print("Enter new address: ");
                                String newAddress = scanner.nextLine();
                                System.out.print("Enter new phone number: ");
                                String newPhoneNumber = scanner.nextLine();
                                admin.updateProfile(newUsername,newPassword, newAddress, newPhoneNumber);
                        }else if (adminOption == 12) {
                                System.out.println("Sellers:");
                                for (int i = 0; i < shop.getUsers().size(); i++) {
                                    User u = shop.getUsers().get(i);
                                    if (u instanceof Seller) {
                                        Seller s = (Seller) u;
                                        System.out.println(i + ". " + s.getUsername() + " - Approved: " + s.isApproved());
                                    }
                                }
                                System.out.print("Enter the seller number to approve: ");
                                int sellerNumber = scanner.nextInt();
                                scanner.nextLine();  
                                if (sellerNumber >= 0 && sellerNumber < shop.getUsers().size()) {
                                    User u = shop.getUsers().get(sellerNumber);
                                    if (u instanceof Seller) {
                                        Seller s = (Seller) u;
                                        shop.approveSeller(s);
                                        System.out.println("Seller approved.");
                                    } else {
                                        System.out.println("Invalid seller number.");
                                    }
                                } else {
                                    System.out.println("Invalid seller number.");
                                }
                            }

                        }

                    } else if (user instanceof Customer) {
                        Customer customer = (Customer) user;
                        while (true) {
                            System.out.println("1. View Profile");
                            System.out.println("2. Add Product to Cart");
                            System.out.println("3. Remove Product from Cart");
                            System.out.println("4. View Cart");
                            System.out.println("5. Checkout");
                            System.out.println("6. Request Add Funds");
                            System.out.println("7. Logout");
                            System.out.println("8. Update Profile");
                            System.out.println("9. Search By Keyword");
                            System.out.print("Choose an option: ");
                            int customerOption = scanner.nextInt();
                            scanner.nextLine();

                            if (customerOption == 1) {
                                customer.displayProfile();

                            }
                            else if (customerOption == 9) {
                                System.out.print("Enter keyword to search products: ");
                                String keyword = scanner.nextLine();
                                customer.searchProducts(shop, keyword);
                            }

                            else if (customerOption == 2) {
                                System.out.println("Choose a category:");
                                for (int i = 0; i < shop.getCategories().size(); i++) {
                                    System.out.println((i + 1) + ". " + shop.getCategories().get(i).getName());
                                }
                                int categoryOption = scanner.nextInt();
                                scanner.nextLine();
                                if (categoryOption > 0 && categoryOption <= shop.getCategories().size()) {
                                    Category selectedCategory = shop.getCategories().get(categoryOption - 1);
                                    displaySubcategories(selectedCategory);


                                    System.out.print("Choose a subcategory or enter 0 to go back: ");
                                    int subcategoryChoice = scanner.nextInt();
                                    scanner.nextLine();

                                    if (subcategoryChoice > 0 && subcategoryChoice <= selectedCategory.getSubcategories().size()) {
                                        Category selectedSubcategory = selectedCategory.getSubcategories().get(subcategoryChoice - 1);


                                        System.out.println("Products in " + selectedSubcategory.getName() + ":");
                                        List<Product> productsInSubcategory = selectedSubcategory.getProducts();
                                        for (int i = 0; i < productsInSubcategory.size(); i++) {
                                            System.out.println((i + 1) + ". " + productsInSubcategory.get(i).getName());
                                            customer.viewProductDetails(productsInSubcategory.get(i));

                                        }


                                        System.out.print("Enter product index to add to cart (0 to cancel): ");
                                        int productIndex = scanner.nextInt();
                                        scanner.nextLine();

                                        if (productIndex > 0 && productIndex <= productsInSubcategory.size()) {
                                            Product selectedProduct = productsInSubcategory.get(productIndex - 1);
                                            customer.addToCart(selectedProduct);
                                            System.out.println(selectedProduct.getName() + " added to cart.");
                                        } else {
                                            System.out.println("Invalid product index. No product added to cart.");
                                        }
                                    } else if (subcategoryChoice == 0) {

                                        continue;
                                    } else {
                                        System.out.println("Invalid subcategory choice.");
                                    }
                                } else {
                                    System.out.println("Invalid category.");
                                }
                            }


                               else if (customerOption == 3) {
                                int i = 0;
                                System.out.println("Products in Cart:");
                                for (Product o : customer.getCart()) {
                                    System.out.println(i+". "+o.getName());
                                }
                                System.out.print("Enter product index to remove: ");
                                int productIndex = scanner.nextInt();
                                scanner.nextLine();
                                if (productIndex >= 0 && productIndex < customer.getCart().size()) {
                                    Product product = customer.getCart().get(productIndex);
                                    customer.removeFromCart(product);
                                    System.out.println("Product removed from cart.");
                                } else {
                                    System.out.println("Invalid product index.");
                                }

                            } else if (customerOption == 4) {
                                System.out.println(customer.getCart());

                            } else if (customerOption == 5) {
                                customer.checkout(shop);

                            } else if (customerOption == 6) {
                                System.out.print("Enter amount to add: ");
                                double amount = scanner.nextDouble();
                                scanner.nextLine();
                                customer.requestAddFunds(amount,shop);

                            } else if (customerOption == 7) {
                                break;
                            }
                            else if (customerOption == 8) {
                                System.out.print("Enter new Username: ");
                                String newUsername = scanner.nextLine();
                                System.out.print("Enter new password: ");
                                String newPassword = scanner.nextLine();
                                System.out.print("Enter new address: ");
                                String newAddress = scanner.nextLine();
                                System.out.print("Enter new phone number: ");
                                String newPhoneNumber = scanner.nextLine();
                                customer.updateProfile(newUsername,newPassword, newAddress, newPhoneNumber);
                            }

                        }


                    } else if (user instanceof Seller) {
                        Seller seller = (Seller) user;
                        if (!seller.isApproved()) {
                            System.out.println("Your account is not approved yet. Please wait for admin approval.");
                            continue;
                        }
                        while (true) {
                            System.out.println("1. View Profile");
                            System.out.println("2. Add Product");
                            System.out.println("3. Remove Product");
                            System.out.println("4. View Products");
                            System.out.println("5. Logout");
                            System.out.println("6. Update Profile");
                            System.out.print("Choose an option: ");
                            int sellerOption = scanner.nextInt();
                            scanner.nextLine();

                            if (sellerOption == 1) {
                                seller.displayProfile();

                            } else if (sellerOption == 2) {
                                seller.addNewProduct(shop);

                            }else if (sellerOption == 3) {
                                int i = 0;
                                System.out.println("Products :");
                                for (Product o : seller.getProducts()) {
                                    System.out.println(i+". "+o.getName());
                                }
                                System.out.print("Enter product index to remove: ");
                                int productIndex = scanner.nextInt();
                                scanner.nextLine();
                                if (productIndex >= 0 && productIndex < seller.getProducts().size()) {
                                    Product product = seller.getProducts().get(productIndex);
                                    seller.removeProduct(product);
                                    product.getCategory().getProducts().remove(product);
                                    System.out.println("Product removed.");
                                } else {
                                    System.out.println("Invalid product index.");
                                }

                            } else if (sellerOption == 4) {
                                System.out.println(seller.getProducts());

                            } else if (sellerOption == 5) {
                                break;
                            }
                            else if (sellerOption == 6) {
                                System.out.print("Enter new Username: ");
                                String newUsername = scanner.nextLine();
                                System.out.print("Enter new password: ");
                                String newPassword = scanner.nextLine();
                                System.out.print("Enter new address: ");
                                String newAddress = scanner.nextLine();
                                System.out.print("Enter new phone number: ");
                                String newPhoneNumber = scanner.nextLine();
                                seller.updateProfile(newUsername,newPassword, newAddress, newPhoneNumber);
                            }

                        }
                    }
                } else {
                    System.out.println("Invalid username or password.");
                }

            } else if (option == 3) {
                break;
            }

        }

        scanner.close();

    }

}
