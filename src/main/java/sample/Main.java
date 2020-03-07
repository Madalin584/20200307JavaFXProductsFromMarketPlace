package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import sample.model.User;
import sample.service.HibernateUtil;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        primaryStage.setTitle("Login Menu");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
        createUser();
    }

    public static void createUser() {
        User user = new User("ionel", "ionel1", "Ionel", "Popescu", "ionel@yahoo.com", "employee");
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSession();
            session.save(user);
            transaction = session.beginTransaction();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}
