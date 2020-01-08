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
import ui.delete_modalController;
import ui.finances.financesController;
import ui.history.historyController;
import ui.logistics.logisticsController;
import ui.sales.salesController;
import ui.staff.add_staffController;
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

    Stage stage = new Stage();

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

    public void clickAddItem() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../inventory/add_item.fxml"));
        Parent root = loader.load();
        add_itemController a_iC = loader.getController();
        a_iC.setIC(this);
        a_iC.setID(table.getSelectionModel().getSelectedItem().getId());
        a_iC.setName2(table.getSelectionModel().getSelectedItem().getName());
        a_iC.refreshBox();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void clickNewItem() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../inventory/new_item.fxml"));
        Parent root = loader.load();
        new_itemController n_iC = loader.getController();
        n_iC.setIC(this);
        n_iC.refreshBox();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void clickNewItemType() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../inventory/add_type.fxml"));
        Parent root = loader.load();
        add_typeController a_tC = loader.getController();
        a_tC.setIC(this);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void clickEdit() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../inventory/edit_item.fxml"));
        Parent root = loader.load();
        edit_itemController e_iC = loader.getController();
        e_iC.setID(table.getSelectionModel().getSelectedItem().getId());
        e_iC.setIC(this);
        e_iC.refreshBox();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void clickDelete() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../delete_modal.fxml"));
        Parent root = loader.load();
        delete_modalController d_mC = loader.getController();
        d_mC.setID(table.getSelectionModel().getSelectedItem().getId());
        d_mC.setIC(this);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
