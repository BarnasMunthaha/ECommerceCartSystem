package service;
import dao.OrderDAO;
import service.CartService;

public class OrderService {
    private OrderDAO orderDAO = new OrderDAO();
    private CartService cartService = new CartService();

    public boolean checkout(int userId) {
        double total = cartService.calculateTotal(userId);
        if (total > 0) {
            boolean created = orderDAO.createOrder(userId, total);
            if (created) {
                cartService.clearCart(userId);
                return true;
            }
        }
        return false;
    }
}