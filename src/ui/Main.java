package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    static Stage primaryStage;
    static FXMLLoader loader;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Main.primaryStage = primaryStage;
        loader = new FXMLLoader(getClass().getResource("authentication/login_page.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Lokalisasi Bali - Login");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}