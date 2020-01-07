package ui;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import ui.finances.financesController;
import ui.history.historyController;
import ui.logistics.logisticsController;
import ui.sales.salesController;
import ui.staff.staffController;
import ui.inventory.inventoryController;

import java.io.IOException;

public class delete_modalController {
    public Button cancel;
    public Button delete;

    public void clickCancel() {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

    public void clickDelete() throws IOException {
        if (Main.primaryStage.getTitle().equals("Lokalisasi Bali - Staff")) {
            staffController sC = Main.loader.getController();
        }
        if (Main.primaryStage.getTitle().equals("Lokalisasi Bali - Sales")) {
            salesController sC = Main.loader.getController();
        }
        if (Main.primaryStage.getTitle().equals("Lokalisasi Bali - Inventory")) {
            inventoryController iC = Main.loader.getController();
        }
        if (Main.primaryStage.getTitle().equals("Lokalisasi Bali - History")) {
            historyController hC = Main.loader.getController();
        }
        if (Main.primaryStage.getTitle().equals("Lokalisasi Bali - Finances")) {
            financesController fC = Main.loader.getController();
        }
        if (Main.primaryStage.getTitle().equals("Lokalisasi Bali - Logistics")) {
            logisticsController lC = Main.loader.getController();
        }
        // Do mysql stuff here.
        Stage stage = (Stage) delete.getScene().getWindow();
        stage.close();
    }
}
