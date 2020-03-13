package sample.service;

import lombok.extern.java.Log;
import org.hibernate.Session;
import sample.dao.ProductDAOImpl;
import sample.model.Product;

import java.util.List;

@Log
public class ProductService {
    private Session session;
    private ProductDAOImpl productDAO;

    public ProductService() {
        this.productDAO = new ProductDAOImpl();
        this.session = HibernateUtil.getSession();
    }

    //this method will call getAll() method from productsDaoImpl
    public List<Product> getAll() {
        List<Product> products = null;
        try {
            products = productDAO.get();
        } catch (Exception e) {
            log.warning(e.getMessage());
        }
        session.close();
        return products;
    }
}
