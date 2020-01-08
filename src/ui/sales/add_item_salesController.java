package ui.sales;

import database.Inventory;
import database.Sales;
import database.Staff;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import ui.staff.staffController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class add_item_salesController {
    public TableView<theItem> table;

    public TableColumn<theItem, String> itemName;
    public TableColumn<theItem, String> itemPrice;
    public TableColumn<theItem, String> itemCount;
    public TableColumn<theItem, String> id;

    public Button edit;
    public Button delete;

    private int primaryID;

    public void setPrimaryID(int primaryID) {
        this.primaryID = primaryID;
    }

    public static class theItem {
        private String id;

        public theItem(String id, String itemName, String itemPrice, String itemCount) {
            this.id = id;
            this.itemName = itemName;
            this.itemPrice = itemPrice;
            this.itemCount = itemCount;
        }

        private String itemName;
        private String itemPrice;
        private String itemCount;

        public String getId() {
            return id;
        }

        public String getItemName() {
            return itemName;
        }

        public String getItemPrice() {
            return itemPrice;
        }

        public String getItemCount() {
            return itemCount;
        }
    }

    public void refreshTable() {
        table.getItems().clear();
        ArrayList<HashMap<String, String>> query = Inventory.getSellableAndAvailable();
        for (int i = 0; i < query.size(); i++) {
            String id = null, itemName = null, itemPrice = null, itemCount = null;
            int row = 0;
            for (String value : query.get(i).values()) {
                switch (row) {
                    case 0:
                        itemPrice = value;
                        break;
                    case 1:
                        itemCount = value;
                        break;
                    case 2:
                        itemName = value;
                        break;
                    case 3:
                        id = value;
                        break;
                    default:
                        break;
                }
                row++;
            }
            table.getItems().add(new theItem(id, itemName, itemPrice, itemCount));
        }
    }
    private salesController sC;

    public void setSC(salesController sC) {
        this.sC = sC;
    }

    public Button cancel;
    public Button done;

    public void clickCancel() {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

    public void clickDone() throws IOException {
        Sales.addSalesDetails(primaryID, Integer.parseInt(table.getSelectionModel().getSelectedItem().getId()));
        sC.refreshTable();
        Stage stage = (Stage) done.getScene().getWindow();
        stage.close();
    }
}
