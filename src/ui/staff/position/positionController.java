package ui.staff.position;

import database.Staff;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import ui.delete_modalController;
import ui.history.historyController;
import ui.inventory.inventoryController;
import ui.staff.staffController;

import java.io.IOException;
import java.util.HashMap;

public class positionController {
    public TableView<thePosition> table;

    public TableColumn<thePosition, String> id;
    public TableColumn<thePosition, String> positionName;

    public Button logout;
    public Button refresh;
    public Button staff;
    public Button sales;
    public Button inventory;
    public Button history;
    public Button finances;
    public Button logistics;
    public Button addPosition;
    public Button edit;
    public Button delete;

    Stage stage = new Stage();

    public static class thePosition {
        public thePosition(int id, String positionName) {
            this.id = id;
            this.positionName = positionName;
        }

        public int getId() {
            return id;
        }

        public String getPositionName() {
            return positionName;
        }

        private String positionName;
        private int id;
    }

    public void refreshTable() {
        table.getItems().clear();
        HashMap<Integer, String> query = Staff.getStaffPositionDetails();
        for (int key : query.keySet()) {
            table.getItems().add(new thePosition(key, query.get(key)));
        }
    }

    public void clickLogout() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../logout_modal.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void clickRefresh() throws IOException {
        refreshTable();
    }

    public void clickStaff() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../staff.fxml"));
        Parent root = loader.load();
        staffController sC = loader.getController();
        sC.refreshTable();
        Stage stage = (Stage) staff.getScene().getWindow();
        stage.setTitle("Lokalisasi Bali - Staff");
        stage.setScene(new Scene(root));
    }

    public void clickSales() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../sales/sales.fxml"));
        Stage stage = (Stage) sales.getScene().getWindow();
        stage.setTitle("Lokalisasi Bali - Sales");
        stage.setScene(new Scene(root));
    }

    public void clickInventory() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../inventory/inventory.fxml"));
        Parent root = loader.load();
        inventoryController iC = loader.getController();
        iC.refreshTable();
        Stage stage = (Stage) inventory.getScene().getWindow();
        stage.setTitle("Lokalisasi Bali - Inventory");
        stage.setScene(new Scene(root));
    }

    public void clickHistory() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../history/history.fxml"));
        Parent root = loader.load();
        historyController hC = loader.getController();
        hC.refreshTable();
        Stage stage = (Stage) history.getScene().getWindow();
        stage.setTitle("Lokalisasi Bali - History");
        stage.setScene(new Scene(root));
    }

    public void clickFinances() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../finances/finances.fxml"));
        Stage stage = (Stage) finances.getScene().getWindow();
        stage.setTitle("Lokalisasi Bali - Finances");
        stage.setScene(new Scene(root));
    }

    public void clickLogistics() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../logistics/logistics.fxml"));
        Stage stage = (Stage) logistics.getScene().getWindow();
        stage.setTitle("Lokalisasi Bali - Logistics");
        stage.setScene(new Scene(root));
    }

    public void clickAddPosition() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("add_position.fxml"));
        Parent root = loader.load();
        add_positionController a_pC = loader.getController();
        a_pC.setPC(this);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void clickEdit() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("edit_position.fxml"));
        Parent root = loader.load();
        edit_positionController e_pC = loader.getController();
        e_pC.setID(table.getSelectionModel().getSelectedItem().getId());
        e_pC.setName(table.getSelectionModel().getSelectedItem().getPositionName());
        e_pC.refreshBox();
        e_pC.setPC(this);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void clickDelete() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../delete_modal.fxml"));
        Parent root = loader.load();
        delete_modalController d_mC = loader.getController();
        d_mC.setID(table.getSelectionModel().getSelectedItem().getId());
        d_mC.setPC(this);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
