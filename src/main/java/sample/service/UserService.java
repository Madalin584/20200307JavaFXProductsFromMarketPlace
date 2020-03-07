package sample.service;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import sample.dao.UserDAOImpl;
import sample.model.User;

import java.util.logging.Logger;

public class UserService {
    private Logger logger;
    private Session session;
    private Transaction transaction;
    private UserDAOImpl userDAO;

    public UserService() {
        this.logger = Logger.getLogger(this.getClass().getName());
        this.userDAO = new UserDAOImpl();
        this.session = HibernateUtil.getSession();
    }

    public User findByUsernameAndPassword(String username, String password) {
        User user = null;
        try {
            transaction = session.getTransaction();
            user = userDAO.findByUsernameAndPassword(username, password);
        } catch (HibernateException e) {
            logger.info(e.getMessage());
            return null;
        } catch (Exception e) {
            logger.info(e.getMessage());
            return null;
        }
        transaction.commit();
        session.close();
        return user;
    }
}
