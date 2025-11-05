package service;
import dao.CartDAO;
import dao.ProductDAO;
import model.Cart;
import model.Product;
import java.util.*;

public class CartService {
    private CartDAO cartDAO = new CartDAO();
    private ProductDAO productDAO = new ProductDAO();

    public boolean addToCart(int userId, int productId, int quantity) {
        return cartDAO.addToCart(userId, productId, quantity);
    }

    public List<Cart> viewCart(int userId) {
        return cartDAO.getCartByUser(userId);
    }

    public double calculateTotal(int userId) {
        List<Cart> cartItems = viewCart(userId);
        List<Product> products = productDAO.getAllProducts();
        double total = 0;

        for (Cart c : cartItems) {
            for (Product p : products) {
                if (p.getId() == c.getProductId()) {
                    total += p.getPrice() * c.getQuantity();
                }
            }
        }
        return total;
    }

    public boolean removeFromCart(int cartId) {
        return cartDAO.removeFromCart(cartId);
    }

    public boolean clearCart(int userId) {
        return cartDAO.clearCart(userId);
    }

	
}