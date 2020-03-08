package sample.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.model.User;
import sample.service.UserService;

import java.io.IOException;


public class Controller {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    public void loginIsPressed(ActionEvent event) throws IOException {
        UserService userService = new UserService();
        User user = userService.findByUsernameAndPassword(username.getText(), password.getText());
        if (user == null) {
            System.out.println("Login fail");
        }else {
            Parent root = FXMLLoader.load(getClass().getResource("/scene/goodby.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();        }

    }
    public void pressme(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/scene/goodby.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();


    }


}
