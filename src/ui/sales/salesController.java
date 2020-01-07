package ui.sales;

import database.Finance;
import database.FinanceDetails;
import database.Sales;
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
import ui.staff.add_staffController;
import ui.staff.edit_staffController;
import ui.staff.position.positionController;
import ui.staff.staffController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class salesController {
    public TableView<theSales> table;
    public TableColumn<theSales, String> id;
    public TableColumn<theSales, String> date;
    public TableColumn<theSales, String> staffID;
    public TableColumn<theSales, String> customerName;
    public TableColumn<theSales, String> salesType;
    public TableColumn<theSales, String> totalSales;

    public TableView<theSales2> table2;
    public TableColumn<theSales2, String> id2;
    public TableColumn<theSales2, String> itemName;
    public TableColumn<theSales2, String> itemPrice;

    public Button logout;
    public Button refresh;
    public Button staff;
    public Button sales;
    public Button inventory;
    public Button history;
    public Button finances;
    public Button logistics;
    public Button addSales;
    public Button addItem;
    public Button edit;
    public Button delete;

    Stage stage = new Stage();

    public static class theSales {
        private String id, date, staffID, customerName, salesType, totalSales;

        public String getId() {
            return id;
        }

        public String getDates() {
            return date;
        }

        public String getStaffID() {
            return staffID;
        }

        public String getCustomerName() {
            return customerName;
        }

        public String getSalesType() {
            return salesType;
        }

        public String getTotalSales() {
            return totalSales;
        }

        public theSales(String id, String date, String staffID, String customerName, String salesType, String totalSales) {
            this.id = id;
            this.date = date;
            this.staffID = staffID;
            this.customerName = customerName;
            this.salesType = salesType;
            this.totalSales = totalSales;
        }
    }
    public static class theSales2 {
        private String id, itemName, itemPrice;

        public String getId() {
            return id;
        }

        public String getItemName() {
            return itemName;
        }

        public String getItemPrice() {
            return itemPrice;
        }

        public theSales2(String id, String itemName, String itemPrice) {
            this.id = id;
            this.itemName = itemName;
            this.itemPrice = itemPrice;
        }
    }

    public void refreshTable() {
        table.getItems().clear();
        ArrayList<HashMap<String, String>> query = Sales.getSales();
        for (int i = 0; i < query.size(); i++) {
            String id = null, dates = null, staffID = null, customerName = null, salesType = null, totalSales = null;
            int row = 0;
            for (String value : query.get(i).values()) {
                switch (row) {
                    case 0:
                        dates = value;
                        break;
                    case 1:
                        staffID = value;
                        break;
                    case 2:
                        totalSales = value;
                        break;
                    case 3:
                        id = value;
                        break;
                    case 4:
                        customerName = value;
                        break;
                    case 5:
                        salesType = value;
                        break;
                    default:
                        break;
                }
                row++;
            }
            table.getItems().add(new theSales(id, dates, staffID, customerName, salesType, totalSales));
        }
    }

    public void onClick() {
        table2.getItems().clear();
        ArrayList<HashMap<String, String>> query2 = Sales.getSalesDetails(Integer.parseInt(table.getSelectionModel().getSelectedItem().getId()));
        for (int i = 0; i < query2.size(); i++) {
            String itemName = null, itemPrice = null;
            int row = 0;
            for (String value: query2.get(i).values()) {
                switch (row) {
                    case 0:
                        itemName = value;
                        break;
                    case 1:
                        itemPrice = value;
                        break;
                }
                row++;
            }
            table2.getItems().add(new theSales2(table.getSelectionModel().getSelectedItem().getId(), itemName, itemPrice));
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
        
    public void clickAddSales() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../sales/add_sales.fxml"));
        Parent root = loader.load();
        add_salesController a_sC = loader.getController();
        a_sC.refreshBox();
        a_sC.setSC(this);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void clickAddItem() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../sales/add_item_sales.fxml"));
        Parent root = loader.load();
        add_item_salesController a_i_sC = loader.getController();
        a_i_sC.refreshBox();
        a_i_sC.setSC(this);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void clickEdit() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../sales/edit_sales.fxml"));
        Parent root = loader.load();
        edit_salesController e_sC = loader.getController();
        e_sC.setStaffIDtext(table.getSelectionModel().getSelectedItem().getId());
        e_sC.setSalesTypetext(table.getSelectionModel().getSelectedItem().getSalesType());
        e_sC.setCustomerNametext(table.getSelectionModel().getSelectedItem().getCustomerName());
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
        d_mC.setSaC(this);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
