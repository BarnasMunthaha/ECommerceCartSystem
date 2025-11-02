package dao;
import java.sql.*;
import java.util.*;
import model.Order;

public class OrderDAO {

    public boolean createOrder(int userId, double total) {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO orders(user_id, total) VALUES(?, ?)"
            );
            ps.setInt(1, userId);
            ps.setDouble(2, total);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public List<Order> getOrdersByUser(int userId) {
        List<Order> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM orders WHERE user_id=?");
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Order(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getDouble("total"),
                        rs.getTimestamp("order_date")
                ));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
}