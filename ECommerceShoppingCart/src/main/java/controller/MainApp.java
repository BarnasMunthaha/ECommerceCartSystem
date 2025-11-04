//mainapp
package controller;
import java.util.*;
import model.*;
import service.*;

public class MainApp {
    private static Scanner sc = new Scanner(System.in);
    private static UserService userService = new UserService();
    private static ProductService productService = new ProductService();
    private static CartService cartService = new CartService();
    private static OrderService orderService = new OrderService();
    private static User loggedUser = null;

    public static void main(String[] args) {
        while (true) {
        	System.out.println("======================================");
            System.out.println("Welcome to E-Commerce System");
            System.out.println("======================================");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.println("Enter Your Choice:");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 :
                	register();
                	break;
                case 2 :
                	login();
                	break;
                case 3 :
                	System.out.println("Thank you for visiting!");
                	System.exit(0);
                	break;
                default:
                	System.out.println("Invalid Choice!Try again.");
            }
        }
    }

    static void register() {
        System.out.print("Enter username: ");
        String u = sc.next();
        System.out.print("Enter password: ");
        String p = sc.next();
        if (userService.registerUser(u, p)) {
            System.out.println(" Registration Complete!");
        System.out.println("Hi "+u+", glad to have you with us.");
        }
        else {
            System.out.println(" Registration failed!");
        }
    }

    static void login() {
        System.out.print("Username: ");
        String u = sc.next();
        System.out.print("Password: ");
        String p = sc.next();
        loggedUser = userService.login(u, p);
        if (loggedUser != null) {
            System.out.println("Hii, " + loggedUser.getUsername() + "!");
            System.out.println("Welcome to your shopping world --let's make today amazing.");
            userMenu();
        } else {
            System.out.println("Invalid credentials!");
        }
    }

    static void userMenu() {
        while (true) {
        	System.out.println("========================");
        	System.out.println("E-Commerce Shopping Menu");
        	System.out.println("========================");
            System.out.println("\n1. View Products");
            System.out.println("2. Add to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Remove from Cart");
            System.out.println("5. Clear Cart");
            System.out.println("6. Checkout");
            System.out.println("7. Logout");
            System.out.println("Enter Your Choice:");

            int choice = sc.nextInt();

            switch (choice) {
                case 1 :
                	viewProducts();
                	break;
                case 2 :
                	addToCart();
                	break;
                case 3 :
                	viewCart();
                	break;
                case 4 :
                	removeFromCart();
                	break;
                case 5 :
                	clearCart();
                	break;
                	
                case 6 :
                	checkout();
                	break;
                case 7 :
                { loggedUser = null; 
                System.out.println("Logged Out Successfully!");
                return;}
               
                default:
                	System.out.println("Invalid Choice! Please try again.");
            }
        }
    }

    static void viewProducts() {
        List<Product> list = productService.listProducts();
        System.out.println("\nAvailable Products:");
        for (Product p : list)
            System.out.println(p.getId() + " - " + p.getName() + " - ₹" + p.getPrice());
    }

    static void addToCart() {
        System.out.print("Enter product id: ");
        int pid = sc.nextInt();
        System.out.print("Enter quantity: ");
        int qty = sc.nextInt();

        if (cartService.addToCart(loggedUser.getId(), pid, qty))
            System.out.println(" Added to cart!");
        else
            System.out.println(" Failed to add!");
    }

    static void viewCart() {
        List<Cart> list = cartService.viewCart(loggedUser.getId());
        double total = cartService.calculateTotal(loggedUser.getId());
        if (list.isEmpty()) {
            System.out.println(" Cart is empty!");
        } else {
            System.out.println("Your Cart:");
            for (Cart c : list)
                System.out.println("CartID: " + c.getId() + " | ProductID: " + c.getProductId() + " | Qty: " + c.getQuantity());
            System.out.println("Total Amount: ₹" + total);
        }
    }
    static void removeFromCart() {
        System.out.print("Enter Cart ID to remove: ");
        int cartId = sc.nextInt();

        if (cartService.removeFromCart(cartId))
            System.out.println(" Product removed from cart!");
        else
            System.out.println(" Product not found in cart!");
    }

    static void clearCart() {
        if (cartService.clearCart(loggedUser.getId()))
            System.out.println(" Cart cleared successfully!");
        else
            System.out.println(" Cart is already empty!");
    }

    static void checkout() {
        if (orderService.checkout(loggedUser.getId()))
            System.out.println("Order placed successfully!");
        else
            System.out.println("Checkout failed!");
    }
}
