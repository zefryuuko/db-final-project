package ui.logistics;

import database.Logistics;
import database.Staff;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import ui.delete_modalController;
import ui.finances.financesController;
import ui.history.historyController;
import ui.inventory.inventoryController;
import ui.sales.salesController;
import ui.staff.add_staffController;
import ui.staff.edit_staffController;
import ui.staff.position.positionController;
import ui.staff.staffController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class logisticsController {
    public TableView<theLogistics> table;

    public TableColumn<theLogistics, String> id;
    public TableColumn<theLogistics, String> staffID;
    public TableColumn<theLogistics, String> customerName;
    public TableColumn<theLogistics, String> customerAddress;
    public TableColumn<theLogistics, String> logisticProvider;
    public TableColumn<theLogistics, String> trackingNumber;
    public TableColumn<theLogistics, String> dateSent;

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

    Stage stage = new Stage();

    public static class theLogistics {
        private String id, staffID, customerName, customerAddress, logisticProvider, trackingNumber, dateSent;

        public theLogistics(String id, String staffID, String customerName, String customerAddress, String logisticProvider, String trackingNumber, String dateSent) {
            this.id = id;
            this.staffID = staffID;
            this.customerName = customerName;
            this.customerAddress = customerAddress;
            this.logisticProvider = logisticProvider;
            this.trackingNumber = trackingNumber;
            this.dateSent = dateSent;
        }

        public String getId() {
            return id;
        }

        public String getStaffID() {
            return staffID;
        }

        public String getCustomerName() {
            return customerName;
        }

        public String getCustomerAddress() {
            return customerAddress;
        }

        public String getLogisticProvider() {
            return logisticProvider;
        }

        public String getTrackingNumber() {
            return trackingNumber;
        }

        public String getDateSent() {
            return dateSent;
        }
    }

    public void refreshTable() {
        table.getItems().clear();
        ArrayList<HashMap<String, String>> query = Logistics.getLogisticsDetails();
        for (int i = 0; i < query.size(); i++) {
            String id = null, staffID = null, customerName = null, customerAddress = null, logisticProvider = null, trackingNumber = null, dateSent = null;
            int row = 0;
            for (String value : query.get(i).values()) {
                switch (row) {
                    case 0:
                        customerAddress = value;
                        break;
                    case 1:
                        id = value;
                        break;
                    case 2:
                        customerName = value;
                        break;
                    case 3:
                        dateSent = value;
                        break;
                    case 4:
                        trackingNumber = value;
                        break;
                    case 5:
                        staffID = value;
                        break;
                    case 6:
                        logisticProvider = value;
                        break;
                    default:
                        break;
                }
                row++;
            }
            table.getItems().add(new theLogistics(id, staffID, customerName, customerAddress, logisticProvider, trackingNumber, dateSent));
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../staff/staff.fxml"));
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

    public void clickAddLogistics() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../logistics/add_logistics.fxml"));
        Parent root = loader.load();
        add_logisticsController a_sC = loader.getController();
        a_sC.refreshBox();
        a_sC.setLC(this);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void clickEdit() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../logistics/edit_logistics.fxml"));
        Parent root = loader.load();
        edit_logisticsController e_lC = loader.getController();
        e_lC.setID(Integer.parseInt(table.getSelectionModel().getSelectedItem().getId()));
        e_lC.refreshBox();
        e_lC.setLC(this);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void clickDelete() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../delete_modal.fxml"));
        Parent root = loader.load();
        delete_modalController d_mC = loader.getController();
        d_mC.setID(Integer.parseInt(table.getSelectionModel().getSelectedItem().getId()));
        d_mC.setLC(this);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
