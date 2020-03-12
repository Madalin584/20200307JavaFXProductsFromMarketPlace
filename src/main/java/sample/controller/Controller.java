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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.dto.ProductDTO;
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
    private TableView<ProductDTO> tableView;
    @FXML
    private TableColumn<ProductDTO, String> description;
    @FXML
    private TableColumn<ProductDTO, String> name;
    @FXML
    private TableColumn<ProductDTO, Integer> price;
    @FXML
    private TableColumn<ProductDTO, Integer> quantity;

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


    ProductService productService = new ProductService();

    public ObservableList<ProductDTO> getAll() {
        ObservableList<ProductDTO> observableListOfProducts = FXCollections.observableArrayList();
        observableListOfProducts.addAll(new ProductDTO("Telefon2", 22, 4, "Description1"));
        observableListOfProducts.addAll(new ProductDTO("Telefon2", 23, 2, "Description2"));
        return observableListOfProducts;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        tableView.setItems(getAll());

        logger.info("At the end of the method");
    }
}
