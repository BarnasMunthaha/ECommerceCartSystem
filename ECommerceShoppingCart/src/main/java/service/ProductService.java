package service;
import dao.ProductDAO;
import model.Product;
import java.util.List;

public class ProductService {
    private ProductDAO productDAO = new ProductDAO();

    public List<Product> listProducts() {
        return productDAO.getAllProducts();
    }

    public boolean addProduct(String name, double price) {
        return productDAO.addProduct(new Product(0, name, price));
    }
}