package ui.finances;

import database.Finance;
import database.FinanceDetails;
import database.IncomeAndExpense;
import database.Purchase;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import ui.history.historyController;
import ui.inventory.inventoryController;
import ui.logistics.logisticsController;
import ui.sales.salesController;
import ui.staff.staffController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class financesController {
    public TableView<theFinances> table;
    public TableColumn<theFinances, String> date;
    public TableColumn<theFinances, String> income;
    public TableColumn<theFinances, String> expenses;
    public TableView<theExpenses> table2;
    public TableColumn<theExpenses, String> date2;
    public TableColumn<theExpenses, String> detail;
    public TableColumn<theExpenses, String> amount;

    public Button logout;
    public Button refresh;
    public Button staff;
    public Button sales;
    public Button inventory;
    public Button history;
    public Button finances;
    public Button logistics;

    public static class theFinances {
        public String getDate() {
            return date;
        }

        public int getIncome() {
            return income;
        }

        public int getExpenses() {
            return expenses;
        }

        public theFinances(String date, int income, int expenses) {
            this.date = date;
            this.income = income;
            this.expenses = expenses;
        }

        private String date;
        private int income, expenses;
    }

    public static class theExpenses {


        private String date, detail, amount;

        public String getDate() {
            return date;
        }

        public String getDetail() {
            return detail;
        }

        public String getAmount() {
            return amount;
        }

        public theExpenses(String date, String detail, String amount) {
            this.date = date;
            this.detail = detail;
            this.amount = amount;
        }
    }

    public void refreshTable() {
        table.getItems().clear();
        ArrayList<IncomeAndExpense> query = Finance.getFinanceDetails();
        for (int i = 0; i < query.size(); i++) {
            IncomeAndExpense curr = query.get(i);
            table.getItems().add(new theFinances(curr.getDate(), curr.getIncome(), curr.getExpenses()));
        }
    }

    public void onClick() {
        table2.getItems().clear();
        System.out.println(table.getSelectionModel().getSelectedItem().getDate());
        ArrayList<FinanceDetails> query2 = Finance.getFinanceDetailsByDate(table.getSelectionModel().getSelectedItem().getDate());
        for (int i = 0; i < query2.size(); i++) {
            FinanceDetails curr = query2.get(i);
            table2.getItems().add(new theExpenses(curr.getDate(), curr.getDetails(), Integer.toString(curr.getAmount())));
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
}
