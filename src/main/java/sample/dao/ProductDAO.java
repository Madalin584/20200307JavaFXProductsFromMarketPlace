package sample.dao;

import sample.model.Product;

import java.util.List;

public interface ProductDAO {
    List<Product> getAllProducts();

    void add(Product product);

    void remove(Integer id);

    void buy(Product product);
}
