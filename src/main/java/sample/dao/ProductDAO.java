package sample.dao;

import sample.model.Product;

import java.util.List;

public interface ProductDAO {
    List<Product> get();

    void add();

    void remove();

    void buy();
}
