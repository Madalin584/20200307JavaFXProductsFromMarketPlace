package sample.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import sample.model.Product;
import sample.service.HibernateUtil;

import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    private Session session = HibernateUtil.getSession();

    @Override
    public List<Product> get() {
        String hql = "from Product";
        Query<Product> query = session.createQuery(hql, Product.class);
        return query.getResultList();
    }

    @Override
    public void add() {

    }

    @Override
    public void remove() {

    }

    @Override
    public void buy() {

    }
}
