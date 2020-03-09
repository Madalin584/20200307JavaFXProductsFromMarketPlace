package sample.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
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
    private Logger logger= Logger.getLogger(this.getClass().getName());

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
    private TableColumn<Product, String> description;
    @FXML
    private TableColumn<Product, String> name;
    @FXML
    private TableColumn<Product, Integer> price;
    @FXML
    private TableColumn<Product, Integer> quantity;

    public void loginIsPressed(ActionEvent event) throws IOException {
        UserService userService = new UserService();
        User user = userService.findByUsernameAndPassword(username.getText(), password.getText());
        if (user == null) {
            System.out.println("Login fail");
        } else {
            Parent root = FXMLLoader.load(getClass().getResource("/scene/welcome.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }

    }

    public void pressme(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/scene/goodby.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();


    }

    ObservableList<Product> observableListOfProducts = FXCollections.observableArrayList();

    //TODO: this method will initialize our table, i'm not quite sure...
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //here we need all products from DB
        logger.info("Inside initialize() method");
        ProductService productService = new ProductService();
        List<Product> products = productService.getAll();
        observableListOfProducts.addAll(products);
        logger.warning("ObservableListOfProducts was added");

        logger.warning("Above setting columns...");

        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        tableView.setItems(observableListOfProducts);

        logger.info("At the end of the method");
    }
}
