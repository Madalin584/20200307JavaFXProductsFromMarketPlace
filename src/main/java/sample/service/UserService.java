package sample.service;

import org.hibernate.Session;
import sample.dao.UserDAOImpl;
import sample.model.User;

import java.util.logging.Logger;

public class UserService {
    private Logger logger;
    private Session session;
    private UserDAOImpl userDAO;

    public UserService() {
        this.logger = Logger.getLogger(this.getClass().getName());
        this.userDAO = new UserDAOImpl();
        this.session = HibernateUtil.getSession();

    }

    public User findByUsernameAndPassword(String username, String password) {
        User user = null;
        try {
           user = userDAO.findByUsernameAndPassword(username, password);
        } catch (Exception e) {
            logger.info(e.getMessage());
            return null;
        }
        session.close();
        return user;
    }
}
