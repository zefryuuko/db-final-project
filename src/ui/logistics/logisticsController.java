package ui.logistics;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class logisticsController {
    public Button logout;
    public Button refresh;
    public Button staff;
    public Button sales;
    public Button inventory;
    public Button history;
    public Button finances;
    public Button logistics;
    public Button addLogistics;
    public Button edit;
    public Button delete;

    public void clickLogout() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../logout_modal.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void clickRefresh() throws IOException {

    }

    public void clickStaff() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../staff/staff.fxml"));
        Stage stage = (Stage) staff.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void clickSales() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../sales/sales.fxml"));
        Stage stage = (Stage) sales.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void clickInventory() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../inventory/inventory.fxml"));
        Stage stage = (Stage) inventory.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void clickHistory() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../history/history.fxml"));
        Stage stage = (Stage) history.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void clickFinances() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../finances/finances.fxml"));
        Stage stage = (Stage) finances.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void clickLogistics() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("logistics.fxml"));
        Stage stage = (Stage) logistics.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void clickAddLogistics() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../logistics/add_logistics.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void clickEdit() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../logistics/edit_logistics.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void clickDelete() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../delete_modal.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
