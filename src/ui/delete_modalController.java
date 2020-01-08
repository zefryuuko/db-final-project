package ui;

import database.Inventory;
import database.Logistics;
import database.Sales;
import database.Staff;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import ui.finances.financesController;
import ui.history.historyController;
import ui.logistics.logisticsController;
import ui.sales.salesController;
import ui.staff.position.positionController;
import ui.staff.staffController;
import ui.inventory.inventoryController;

import java.io.IOException;

public class delete_modalController {
    private int id;

    public Button cancel;
    public Button delete;

    private staffController sC;
    private inventoryController iC;
    private positionController pC;
    private logisticsController lC;

    public void setLC(logisticsController lC) {
        this.lC = lC;
    }

    public void setSaC(salesController saC) {
        this.saC = saC;
    }

    private salesController saC;

    public void setPC(positionController pC) {
        this.pC = pC;
    }

    public void setIC(inventoryController iC) {
        this.iC = iC;
    }

    public void setSC(staffController sC) {
        this.sC = sC;
    }

    public void setID(int id) {
        this.id = id;
    }

    public void clickCancel() {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

    public void clickDelete() throws IOException {
        if (Main.primaryStage.getTitle().equals("Lokalisasi Bali - Staff")) {
            Staff.deleteStaff(id);
            sC.refreshTable();
        }
        if (Main.primaryStage.getTitle().equals("Lokalisasi Bali - Sales")) {
            Sales.deleteSales(id);
            saC.refreshTable();
        }
        if (Main.primaryStage.getTitle().equals("Lokalisasi Bali - Inventory")) {
            Inventory.deleteItem(id);
            iC.refreshTable();
        }
//        if (Main.primaryStage.getTitle().equals("Lokalisasi Bali - History")) {
//
//        }
//        if (Main.primaryStage.getTitle().equals("Lokalisasi Bali - Finances")) {
//
//        }
        if (Main.primaryStage.getTitle().equals("Lokalisasi Bali - Logistics")) {
            Logistics.deleteLogisticsEntry(id);
            lC.refreshTable();
        }
        if (Main.primaryStage.getTitle().equals("Lokalisasi Bali - Position")) {
            Staff.deletePosition(id);
            pC.refreshTable();
        }
        Stage stage = (Stage) delete.getScene().getWindow();
        stage.close();
    }
}
