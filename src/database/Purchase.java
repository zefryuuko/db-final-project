package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Purchase extends DBConnect{
    // CREATE


    // READ
    public static ArrayList<HashMap<String, String>> getPurchases() {
        ArrayList<HashMap<String, String>> result = new ArrayList<>();

        try {
            String query = "SELECT p.purchase_id, p.purchase_date, p.staff_id, s.staff_fname, s.staff_lname, i.item_name, t.type_name, p.purchase_count, p.purchase_price_total FROM PurchaseHistory p LEFT JOIN Staff s ON p.staff_id = s.staff_id LEFT JOIN Item i on p.item_id = i.item_id LEFT JOIN ItemType t ON i.item_type = t.type_id;";
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                HashMap<String, String> row = new HashMap<>();
                row.put("id", Integer.toString(rs.getInt("purchase_id")));
                row.put("date", rs.getDate("purchase_date").toString());
                row.put("staff", Integer.toString(rs.getInt("staff_id")) + " - " + rs.getString("staff_fname") + " " + rs.getString("staff_lname"));
                row.put("itemName", rs.getString("item_name"));
                row.put("itemType", rs.getString("type_name"));
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

    // DELETE
}
