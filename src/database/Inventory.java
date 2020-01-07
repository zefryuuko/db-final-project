package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Inventory extends DBConnect {
    // Items

    public static ArrayList<HashMap<String, String>> getItems() {
        ArrayList<HashMap<String, String>> result = new ArrayList<>();

        try {
            String query = "SELECT item_id, item_name, item_vendor, t.type_name, item_stored, item_price, item_sellable FROM Item LEFT JOIN ItemType t ON item_type = t.type_id";
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                HashMap<String, String> row = new HashMap<>();
                row.put("id", Integer.toString(rs.getInt("item_id")));
                row.put("name", rs.getString("item_name"));
                row.put("vendor", rs.getString("item_vendor"));
                row.put("type", rs.getString("type_name"));
                row.put("stored", Integer.toString(rs.getInt("item_stored")));
                row.put("price", Integer.toString(rs.getInt("item_price")));
                row.put("sellable", Boolean.toString(rs.getBoolean("item_sellable")));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }

        return result;
    }

    public static HashMap<String, String> getItem(int itemId) {
        HashMap<String, String> result = new HashMap<>();

        try {
            String query = "SELECT item_id, item_name, item_vendor, t.type_name, item_stored, item_price, item_sellable FROM Item LEFT JOIN ItemType t ON item_type = t.type_id WHERE item_id = ?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, itemId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                result.put("id", Integer.toString(rs.getInt("item_id")));
                result.put("name", rs.getString("item_name"));
                result.put("vendor", rs.getString("item_vendor"));
                result.put("type", rs.getString("type_name"));
                result.put("stored", Integer.toString(rs.getInt("item_stored")));
                result.put("price", Integer.toString(rs.getInt("item_price")));
                result.put("sellable", Boolean.toString(rs.getBoolean("item_sellable")));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }

        return result;
    }

    // Item Types

    public static ArrayList<HashMap<String, String>> getItemTypes() {
        ArrayList<HashMap<String, String>> result = new ArrayList<>();

        try {
            String query = "SELECT type_id, type_name FROM ItemType";
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                HashMap<String, String> row = new HashMap<>();
                row.put("id", Integer.toString(rs.getInt("type_id")));
                row.put("name", rs.getString("type_name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }

        return result;
    }
}
