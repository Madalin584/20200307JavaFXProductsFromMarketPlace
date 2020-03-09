package sample.service;

import org.hibernate.Session;
import sample.dao.ProductDAOImpl;
import sample.model.Product;

import java.util.List;
import java.util.logging.Logger;

public class ProductService {
    private Logger logger;
    private Session session;
    private ProductDAOImpl productDAO;

    public ProductService() {
        this.logger = Logger.getLogger(ProductService.class.getName());
        this.productDAO = new ProductDAOImpl();
        this.session = HibernateUtil.getSession();
    }

    //this method will call getAll() method from productsDaoImpl
    public List<Product> getAll() {
        List<Product> products = null;
        try {
            products = productDAO.get();
        } catch (Exception e) {
            logger.warning(e.getMessage());
        }
        session.close();
        return products;
    }
}
