package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Purchase extends DBConnect{
    // CREATE
    public static boolean addNewItemPurchase(String itemName, String itemVendor, int itemType, int itemStored, int itemPrice, boolean itemSellable, int staffId, int purchaseCount, int purchasePriceTotal) {
        int itemId = Inventory.createItemReturnItemId(itemName, itemVendor, itemType, itemStored, itemPrice, itemSellable);
        if (itemId == -1) return false;

        try {
            String query = "INSERT INTO PurchaseHistory (purchase_date, staff_id, item_id, purchase_count, purchase_price_total) VALUES (NOW(), ?, ?, ?, ?)";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, staffId);
            pst.setInt(2, itemId);
            pst.setInt(3, purchaseCount);
            pst.setInt(4, purchasePriceTotal);
            int affectedRows = pst.executeUpdate();

            if (affectedRows == 0) return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    // READ
    public static ArrayList<HashMap<String, String>> getPurchases() {
        ArrayList<HashMap<String, String>> result = new ArrayList<>();

        try {
            String query = "SELECT p.purchase_id, p.purchase_date, p.staff_id, s.staff_fname, s.staff_lname, i.item_name, i.item_vendor, t.type_name, p.purchase_count, p.purchase_price_total FROM PurchaseHistory p LEFT JOIN Staff s ON p.staff_id = s.staff_id LEFT JOIN Item i on p.item_id = i.item_id LEFT JOIN ItemType t ON i.item_type = t.type_id;";
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                HashMap<String, String> row = new HashMap<>();
                row.put("id", Integer.toString(rs.getInt("purchase_id")));
                row.put("date", rs.getDate("purchase_date").toString());
                row.put("staff", Integer.toString(rs.getInt("staff_id")) + " - " + rs.getString("staff_fname") + " " + rs.getString("staff_lname"));
                row.put("itemName", rs.getString("item_name"));
                row.put("itemType", rs.getString("type_name"));
                row.put("itemVendor", rs.getString("item_vendor"));
                row.put("purchaseCount", Integer.toString(rs.getInt("purchase_count")));
                row.put("purchaseTotalPrice", Integer.toString(rs.getInt("purchase_price_total")));
                result.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }

        return result;
    }

    // UPDATE
    public static boolean addExistingItemPurchase(int itemId, int staffId, int purchaseCount, int purchasePriceTotal) {
        boolean updateItemStatus = Inventory.updateItemCount(itemId, purchaseCount);
        if (!updateItemStatus) return false;

        try {
            String query = "INSERT INTO PurchaseHistory (purchase_date, staff_id, item_id, purchase_count, purchase_price_total) VALUES (NOW(), ?, ?, ?, ?)";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, staffId);
            pst.setInt(2, itemId);
            pst.setInt(3, purchaseCount);
            pst.setInt(4, purchasePriceTotal);
            int affectedRows = pst.executeUpdate();

            if (affectedRows == 0) return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    // DELETE
}
