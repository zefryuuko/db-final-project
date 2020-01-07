package ui.inventory;

import database.Inventory;
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

public class inventoryController {
    public TableView<theInventory> table;

    public TableColumn<theInventory, String> itemID;
    public TableColumn<theInventory, String> name;
    public TableColumn<theInventory, String> type;
    public TableColumn<theInventory, String> stored;
    public TableColumn<theInventory, String> vendor;
    public TableColumn<theInventory, String> sellable;
    public TableColumn<theInventory, String> sellPrice;
    
    public Button logout;
    public Button refresh;
    public Button staff;
    public Button sales;
    public Button inventory;
    public Button history;
    public Button finances;
    public Button logistics;
    public Button addItem;
    public Button edit;
    public Button delete;

    public static class theInventory {
        public String getVendor() {
            return vendor;
        }

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }

        public int getPrice() {
            return price;
        }

        public int getStored() {
            return stored;
        }

        public int getId() {
            return id;
        }

        public boolean isSellable() {
            return sellable;
        }

        public theInventory(String vendor, String name, String type, int price, int stored, int id, boolean sellable) {
            this.vendor = vendor;
            this.name = name;
            this.type = type;
            this.price = price;
            this.stored = stored;
            this.id = id;
            this.sellable = sellable;
        }

        private String vendor, name, type;
        private int price, stored, id;
        private boolean sellable;
    }

    public void refreshTable() {
        table.getItems().clear();
        ArrayList<HashMap<String, String>> query = Inventory.getItems();
        for (int i = 0; i < query.size(); i++) {
            String vendor = null, name = null, type = null;
            int price = 0, stored = 0, id = 0;
            boolean sellable = false;
            int row = 0;
            for (String value : query.get(i).values()) {
                switch (row) {
                    case 0:
                        vendor = value;
                        break;
                    case 1:
                        price = Integer.parseInt(value);
                        break;
                    case 2:
                        stored = Integer.parseInt(value);
                        break;
                    case 3:
                        name = value;
                        break;
                    case 4:
                        id = Integer.parseInt(value);
                        break;
                    case 5:
                        if (value.equals("true")) sellable = true;
                        else sellable = false;
                        break;
                    case 6:
                        type = value;
                    default:
                        break;
                }
                row++;
            }
            table.getItems().add(new theInventory(vendor, name, type, price, stored, id, sellable));
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
        Parent root = FXMLLoader.load(getClass().getResource("inventory.fxml"));
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

    public void clickAddItem() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../inventory/add_item.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void clickEdit() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../inventory/edit_item.fxml"));
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
