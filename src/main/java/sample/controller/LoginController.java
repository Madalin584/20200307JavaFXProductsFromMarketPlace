package sample.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.extern.java.Log;
import sample.exceptions.UserException;
import sample.model.User;
import sample.service.UserService;

import java.io.IOException;

@Log
public class LoginController {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    public void loginButtonIsPressed(ActionEvent event) throws UserException, IOException {
        UserService userService = new UserService();
        User user = userService.findByUsernameAndPassword(username.getText(), password.getText());
        if (user != null) {
            Parent root = FXMLLoader.load(getClass().getResource("/scene/welcome.fxml"));
            Scene scene = new Scene(root, 600, 400);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Welcome Page");
            stage.show();
        } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Login fail");
            errorAlert.setContentText("Please enter valid username and password");
            errorAlert.showAndWait();
            throw new UserException("User object is null");
        }
    }
}
