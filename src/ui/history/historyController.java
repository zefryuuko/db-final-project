package ui.history;

import database.Purchase;
import database.Staff;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import ui.staff.staffController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class historyController {
    public TableView<theHistory> table;

    public TableColumn<theHistory, String> id;
    public TableColumn<theHistory, String> date;
    public TableColumn<theHistory, String> type;
    public TableColumn<theHistory, String> name;
    public TableColumn<theHistory, String> vendor;
    public TableColumn<theHistory, String> staffID;
    public TableColumn<theHistory, String> purchaseCost;
    public TableColumn<theHistory, String> buyCount;
    
    public Button logout;
    public Button refresh;
    public Button staff;
    public Button sales;
    public Button inventory;
    public Button history;
    public Button finances;
    public Button logistics;

    public static class theHistory {
        private String date, name, type, vendor, buyCount, staff, id, purchaseCost;

        public theHistory(String date, String name, String type, String vendor, String buyCount, String staff, String id, String purchaseCost) {
            this.date = date;
            this.name = name;
            this.type = type;
            this.vendor = vendor;
            this.buyCount = buyCount;
            this.staff = staff;
            this.id = id;
            this.purchaseCost = purchaseCost;
        }

        public String getDate() {
            return date;
        }

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }

        public String getVendor() {
            return vendor;
        }

        public String getBuyCount() {
            return buyCount;
        }

        public String getStaff() {
            return staff;
        }

        public String getId() {
            return id;
        }

        public String getPurchaseCost() {
            return purchaseCost;
        }
    }

    public void refreshTable() {
        table.getItems().clear();
        ArrayList<HashMap<String, String>> query = Purchase.getPurchases();
        for (int i = 0; i < query.size(); i++) {
            String date = null, name = null, type = null, vendor = null, buyCount = null, staff = null, id = null, purchaseCost = null;
            int row = 0;
            for (String value : query.get(i).values()) {
                switch (row) {
                    case 0:
                        date = value;
                        break;
                    case 1:
                        name = value;
                        break;
                    case 2:
                        type = value;
                        break;
                    case 3:
                        vendor = value;
                        break;
                    case 4:
                        buyCount = value;
                        break;
                    case 5:
                        staff = value;
                        break;
                    case 6:
                        id = value;
                        break;
                    case 7:
                        purchaseCost = value;
                        break;
                    default:
                        break;
                }
                row++;
            }
            table.getItems().add(new theHistory(date, name, type, vendor, buyCount, staff, id, purchaseCost));
        }
    }

    public void clickLogout() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../logout_modal.fxml"));
        Stage stage = new Stage();
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
        Parent root = FXMLLoader.load(getClass().getResource("history.fxml"));
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
}
