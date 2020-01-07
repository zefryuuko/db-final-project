package ui.staff;

import database.Staff;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ui.Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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
        System.out.println("asdsadas");
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
        Parent root = FXMLLoader.load(getClass().getResource("../sales/sales.fxml"));
        Stage stage = (Stage) sales.getScene().getWindow();
        stage.setTitle("Lokalisasi Bali - Sales");
        stage.setScene(new Scene(root));
    }

    public void clickInventory() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../inventory/inventory.fxml"));
        Stage stage = (Stage) inventory.getScene().getWindow();
        stage.setTitle("Lokalisasi Bali - Inventory");
        stage.setScene(new Scene(root));
    }

    public void clickHistory() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../history/history.fxml"));
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

    public void clickAddStaff() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../staff/add_staff.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void clickEdit() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../staff/edit_staff.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void clickDelete() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../delete_modal.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }
}
