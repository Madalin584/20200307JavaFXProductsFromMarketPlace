package sample.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import sample.model.User;
import sample.service.HibernateUtil;

public class UserDAOImpl implements UserDAO {
    private Session session = HibernateUtil.getSession();

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        String hql = "select u from User u where username = :username and password = :password";
        Query<User> query = session.createQuery(hql, User.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        return query.getSingleResult();

    }
}
