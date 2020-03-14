package sample.dao;

import sample.model.Product;

import java.util.List;

public interface ProductDAO {
    List<Product> getAllProducts();

    void add(Product product);

    //delete by ID or by Name ???
    void remove();

    void buy(Product product);
}
