package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class Controller {
    @FXML
    private TextField username;
    @FXML
    private PasswordField pass;
    public void loginIsPressed(ActionEvent event){


    }
    public void pressme(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/goodby.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }


}
