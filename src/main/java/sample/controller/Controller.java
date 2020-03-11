package sample.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import sample.exceptions.UserException;
import sample.model.Product;
import sample.model.User;
import sample.service.ProductService;
import sample.service.UserService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class Controller implements Initializable {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    //onAction va apela o metoda(addQuantity()) din acest controller
    //aceasta metoda va interactiona cu baza de date si
    //va incrementa valoarea de la coloana "Quantity"
    //daca operatiunea se executa cu succes va afisa un mesaj de confirmare
    //altfel va afisa un mesaj de eroare
    //dupa fiecare actualizare de produs tabelul ar trebui afisat
    //din nou cu noile valori

    //login fxml
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    //welcome fxml
    //binding these variables with fx:id input from SceneBuilder
    @FXML
    private TableView<Product> tableView;
    @FXML
    private TableColumn<Product, Integer> id;
    @FXML
    private TableColumn<Product, String> description;
    @FXML
    private TableColumn<Product, String> name;
    @FXML
    private TableColumn<Product, Integer> price;
    @FXML
    private TableColumn<Product, Integer> quantity;

    public void loginButtonIsPressed(ActionEvent event) throws UserException, IOException {
        UserService userService = new UserService();
        User user = userService.findByUsernameAndPassword(username.getText(), password.getText());
        if (user != null) {
            Parent root = FXMLLoader.load(getClass().getResource("/scene/welcome.fxml"));
            Scene scene = new Scene(root, 600, 400);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Products Page");
            stage.show();
        } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Login fail");
            errorAlert.setContentText("Please enter valid username and password");
            errorAlert.showAndWait();
            throw new UserException("User object is null");
        }
    }

    ObservableList<Product> observableListOfProducts = FXCollections.observableArrayList();
    ProductService productService = new ProductService();
    List<Product> products = productService.getAll();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        observableListOfProducts.addAll(products);

//        id.setCellValueFactory(new PropertyValueFactory<>("id"));
//        name.setCellValueFactory(new PropertyValueFactory<>("name"));
//        description.setCellValueFactory(new PropertyValueFactory<>("description"));
//        price.setCellValueFactory(new PropertyValueFactory<>("price"));
//        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
//        tableView.setItems(observableListOfProducts);

        logger.info("At the end of the method");
    }
}
