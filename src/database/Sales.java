package database;

import com.sun.org.apache.bcel.internal.generic.LDIV;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public class Sales extends DBConnect{
    // CREATE
    public static boolean addSales(int staffId, String custName, int salesType) {
        try {
            String query = "INSERT INTO Sales (staff_id, cust_name, sales_type, sales_datetime) VALUES (?, ?, ?, NOW())";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, staffId);
            pst.setString(2, custName);
            pst.setInt(3, salesType);
            int affectedRows = pst.executeUpdate();

            if (affectedRows == 0) return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static boolean addSalesDetails(int salesId, int item_id) {
        try {
            String query = "INSERT INTO SalesDetails (sales_id, item_id) VALUES (?, ?)";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, salesId);
            pst.setInt(2, item_id);
            int affectedRows = pst.executeUpdate();

            if (affectedRows == 0) return false;
            else {
                // Deduct item on stock by one
                String deductQuery = "UPDATE Item SET item_stored = item_stored - 1 WHERE item_id = ?";
                PreparedStatement pstd = connection.prepareStatement(deductQuery);
                pstd.setInt(1, item_id);
                pstd.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    // READ
    public static ArrayList<HashMap<String, String>> getSales() {
        ArrayList<HashMap<String, String>> result = new ArrayList<>();

        try {
            String query = "SELECT s.sales_id, s.sales_datetime, s.staff_id, s.cust_name, i.staff_fname, i.staff_lname, t.type_name, SUM(m.item_price) as total_sales FROM Sales s LEFT JOIN Staff i ON s.staff_id = i.staff_id LEFT JOIN SalesType t ON s.sales_type = t.type_id LEFT JOIN SalesDetails d ON s.sales_id = d.sales_id LEFT JOIN Item m on d.item_id = m.item_id GROUP BY sales_id;";
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                HashMap<String, String> row = new HashMap<>();
                row.put("id", Integer.toString(rs.getInt("sales_id")));
                row.put("date", rs.getDate("sales_datetime").toString());
                row.put("staff", Integer.toString(rs.getInt("staff_id")) + " - " + rs.getString("staff_fname") + " " + rs.getString("staff_lname"));
                row.put("custName", rs.getString("cust_name"));
                row.put("type", rs.getString("type_name"));
                row.put("totalSales", rs.getString("total_sales"));
                result.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }

        return result;
    }

    public static ArrayList<HashMap<String, String>> getSalesDetails(int salesId) {
        ArrayList<HashMap<String, String>> result = new ArrayList<>();

        try {
            String query = "SELECT d.item_id, i.item_name, i.item_price FROM SalesDetails d LEFT JOIN Item i ON i.item_id = d.item_id WHERE d.sales_id = ?;";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, salesId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                HashMap<String, String> row = new HashMap<>();
                row.put("id", Integer.toString(rs.getInt("item_id")));
                row.put("itemName", rs.getString("item_name"));
                row.put("itemPrice", Integer.toString(rs.getInt("item_price")));
                result.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }

        return result;
    }

    public static HashMap<Integer, String> getSalesTypes() {
        HashMap<Integer, String> result = new HashMap<>();

        try {
            String query = "SELECT * FROM SalesType";
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while(rs.next()) {
                result.put(rs.getInt("type_id"), rs.getString("type_name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }

        return result;
    }

    // UPDATE
    public static boolean updateSales(int salesId, String custName, int salesType) {
        try {
            String query = "UPDATE Sales SET cust_name = ?, sales_type = ? WHERE sales_id = ?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, custName);
            pst.setInt(2, salesType);
            pst.setInt(3, salesId);
            int affectedRows = pst.executeUpdate();

            if (affectedRows == 0) return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    // DELETE
    public static boolean deleteSales(int salesId) {
        try {
            String query = "DELETE FROM Sales WHERE sales_id = ?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, salesId);
            int affectedRows = pst.executeUpdate();

            if (affectedRows == 0) return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

}
