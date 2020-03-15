package sample.dao;

import sample.model.User;

public interface UserDAO {
    User findByUsernameAndPassword(String username, String password);

    void buyWithDiscount();
}
