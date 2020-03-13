package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.java.Log;
import sample.model.Product;
import sample.service.ProductService;

import java.net.URL;
import java.util.ResourceBundle;

@Log
public class WelcomeController implements Initializable {
    //onAction va apela o metoda(addQuantity()) din acest controller
    //aceasta metoda va interactiona cu baza de date si
    //va incrementa valoarea de la coloana "Quantity"
    //daca operatiunea se executa cu succes va afisa un mesaj de confirmare
    //altfel va afisa un mesaj de eroare
    //dupa fiecare actualizare de produs tabelul ar trebui afisat
    //din nou cu noile valori

    //welcome fxml
    //binding these variables with fx:id input from SceneBuilder
    @FXML
    private TableView<Product> tableView;
    @FXML
    private TableColumn<Product, String> description;
    @FXML
    private TableColumn<Product, String> name;
    @FXML
    private TableColumn<Product, Integer> price;
    @FXML
    private TableColumn<Product, Integer> quantity;

    public ObservableList<Product> getAll() {
        ProductService productService = new ProductService();
        ObservableList<Product> observableListOfProducts = FXCollections.observableArrayList();
        observableListOfProducts.addAll(productService.getAll());
        return observableListOfProducts;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        tableView.setItems(getAll());

        log.info("At the end of the method");
    }
}
