package ui.staff;

import database.Staff;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ui.Main;
import ui.delete_modalController;
import ui.finances.financesController;
import ui.history.historyController;
import ui.inventory.inventoryController;
import ui.logistics.logisticsController;
import ui.sales.salesController;
import ui.staff.position.positionController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class staffController {
    public TableView<theStaff> table;

    public TableColumn<theStaff, String> staffID;
    public TableColumn<theStaff, String> firstName;
    public TableColumn<theStaff, String> surname;
    public TableColumn<theStaff, String> salary;
    public TableColumn<theStaff, String> position;
    public TableColumn<theStaff, String> status;

    public Button logout;
    public Button refresh;
    public Button staff;
    public Button sales;
    public Button inventory;
    public Button history;
    public Button finances;
    public Button logistics;
    public Button addStaff;
    public Button addPosition;
    public Button edit;
    public Button delete;

    Stage stage = new Stage();

    public static class theStaff {
        private String id, firstName, surname, salary, position, status;

        public theStaff(String id, String firstName, String surname, String salary, String position, String status) {
            this.id = id;
            this.firstName = firstName;
            this.surname = surname;
            this.salary = salary;
            this.position = position;
            this.status = status;
        }

        public String getId() {
            return id;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getSurname() {
            return surname;
        }

        public String getSalary() {
            return salary;
        }

        public String getPosition() {
            return position;
        }

        public String getStatus() {
            return status;
        }
    }

    public void refreshTable() {
        table.getItems().clear();
        ArrayList<HashMap<String, String>> query = Staff.getStaffs();
        for (int i = 0; i < query.size(); i++) {
            String id = null, firstName = null, surname = null, salary = null, position = null, status = null;
            int row = 0;
            for (String value : query.get(i).values()) {
                switch (row) {
                    case 0:
                        firstName = value;
                        break;
                    case 1:
                        surname = value;
                        break;
                    case 2:
                        id = value;
                        break;
                    case 3:
                        position = value;
                        break;
                    case 4:
                        salary = value;
                        break;
                    case 5:
                        status = value;
                        break;
                    default:
                        break;
                }
                row++;
            }
        table.getItems().add(new theStaff(id, firstName, surname, salary, position, status));
        }
    }

    public void showUnpaid() {
        table.getItems().clear();
        ArrayList<HashMap<String, String>> query = Staff.getUnpaidStaffs();
        for (int i = 0; i < query.size(); i++) {
            String id = null, firstName = null, surname = null, salary = null, position = null, status = null;
            int row = 0;
            for (String value : query.get(i).values()) {
                switch (row) {
                    case 0:
                        firstName = value;
                        break;
                    case 1:
                        surname = value;
                        break;
                    case 2:
                        id = value;
                        break;
                    case 3:
                        position = value;
                        break;
                    case 4:
                        salary = value;
                        break;
                    case 5:
                        status = value;
                        break;
                    default:
                        break;
                }
                row++;
            }
            table.getItems().add(new theStaff(id, firstName, surname, salary, position, status));
        }
    }

    public void clickLogout() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../logout_modal.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void clickRefresh() throws IOException {
        refreshTable();
    }

    public void clickStaff() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("staff.fxml"));
        Parent root = loader.load();
        staffController sC = loader.getController();
        sC.refreshTable();
        Stage stage = (Stage) staff.getScene().getWindow();
        stage.setTitle("Lokalisasi Bali - Staff");
        stage.setScene(new Scene(root));
    }

    public void clickSales() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../sales/sales.fxml"));
        Parent root = loader.load();
        salesController sC = loader.getController();
        sC.refreshTable();
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../finances/finances.fxml"));
        Parent root = loader.load();
        financesController fC = loader.getController();
        fC.refreshTable();
        Stage stage = (Stage) finances.getScene().getWindow();
        stage.setTitle("Lokalisasi Bali - Finances");
        stage.setScene(new Scene(root));
    }

    public void clickLogistics() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../logistics/logistics.fxml"));
        Parent root = loader.load();
        logisticsController lC = loader.getController();
        lC.refreshTable();
        Stage stage = (Stage) logistics.getScene().getWindow();
        stage.setTitle("Lokalisasi Bali - Logistics");
        stage.setScene(new Scene(root));
    }

    public void clickAddStaff() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../staff/add_staff.fxml"));
        Parent root = loader.load();
        add_staffController a_sC = loader.getController();
        a_sC.refreshBox();
        a_sC.setSC(this);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void clickPosition() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../staff/position/position.fxml"));
        Parent root = loader.load();
        positionController pC = loader.getController();
        pC.refreshTable();
        Stage stage = (Stage) addPosition.getScene().getWindow();
        stage.setTitle("Lokalisasi Bali - Position");
        stage.setScene(new Scene(root));
    }

    public void clickPay() {
        if (Staff.isStaffPaid(Integer.parseInt(table.getSelectionModel().getSelectedItem().getId()))) {
            new Alert(Alert.AlertType.INFORMATION, "The selected staff is already paid this month").showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to pay the salary for "
                    + table.getSelectionModel().getSelectedItem().getId()
                    + " - " + table.getSelectionModel().getSelectedItem().getFirstName()
                    + " " + table.getSelectionModel().getSelectedItem().getSurname()
                    + " for " + table.getSelectionModel().getSelectedItem().getSalary() + "?");
            Optional<ButtonType> choice = alert.showAndWait();
            if (choice.get() == ButtonType.OK) {
                Staff.payStaff(Integer.parseInt(table.getSelectionModel().getSelectedItem().getId()));
                new Alert(Alert.AlertType.INFORMATION, "Payment recorded.").showAndWait();
                refreshTable();
            }
        }
    }

    public void clickEdit() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../staff/edit_staff.fxml"));
        Parent root = loader.load();
        edit_staffController e_sC = loader.getController();
        e_sC.setID(Integer.parseInt(table.getSelectionModel().getSelectedItem().getId()));
        e_sC.refreshBox();
        e_sC.setSC(this);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void clickDelete() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../delete_modal.fxml"));
        Parent root = loader.load();
        delete_modalController d_mC = loader.getController();
        d_mC.setID(Integer.parseInt(table.getSelectionModel().getSelectedItem().getId()));
        d_mC.setSC(this);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
