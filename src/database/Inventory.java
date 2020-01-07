package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Inventory extends DBConnect {
    // -----
    // Items
    // -----

    // CREATE
    public static boolean createItem(String itemName, String itemVendor, int itemType, int itemStored, int itemPrice, boolean itemSellable) {
        try {
            String query = "INSERT INTO Item (item_name, item_vendor, item_type, item_stored, item_price, item_sellable) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, itemName);
            pst.setString(2, itemVendor);
            pst.setInt(3, itemType);
            pst.setInt(4, itemStored);
            pst.setInt(5, itemPrice);
            pst.setBoolean(6, itemSellable);
            int affectedRows = pst.executeUpdate();

            if (affectedRows == 0) return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    // READ
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
                result.add(row);
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

    // UPDATE
    public static boolean updateItem(int itemId, String itemName, String itemVendor, int itemType, int itemStored, int itemPrice, boolean itemSellable) {
        try {
            String query = "UPDATE Item SET item_name = ?, item_vendor = ?, item_type = ?, item_stored = ?, item_price = ?, item_sellable = ? WHERE item_id = ?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, itemName);
            pst.setString(2, itemVendor);
            pst.setInt(3, itemType);
            pst.setInt(4, itemStored);
            pst.setInt(5, itemPrice);
            pst.setBoolean(6, itemSellable);
            pst.setInt(7, itemId);
            int affectedRows = pst.executeUpdate();

            if (affectedRows == 0) return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static boolean updateItemCount(int itemId, int itemAddCount) {
        try {
            String query = "UPDATE Item SET item_stored + ? WHERE item_id = ?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, itemAddCount);
            pst.setInt(2, itemId);
            int affectedRows = pst.executeUpdate();

            if (affectedRows == 0) return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    // DELETE
    public static boolean deleteItem(int itemId) {
        try {
            String query = "DELETE FROM Item WHERE item_id = ?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, itemId);
            int affectedRows = pst.executeUpdate();

            if (affectedRows == 0) return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    // ----------
    // Item Types
    // ----------

    // CREATE
    public static boolean addItemType(String typeName) {
        try {
            String query = "INSERT INTO ItemType (type_name) VALUES (?)";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, typeName);
            int affectedRows = pst.executeUpdate();

            if (affectedRows == 0) return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    // READ
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

    // UPDATE
    public static boolean updateItemType(int typeId, String typeName) {
        try {
            String query = "UPDATE ItemType SET type_name = ? WHERE item_id = ?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, typeName);
            pst.setInt(2, typeId);
            int affectedRows = pst.executeUpdate();

            if (affectedRows == 0) return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    // DELETE
    public static boolean deleteItemType(int itemId) {
        try {
            String query = "DELETE FROM ItemType WHERE item_id = ?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, itemId);
            int affectedRows = pst.executeUpdate();

            if (affectedRows == 0) return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
