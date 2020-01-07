package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.sql.*;
import java.util.ArrayList;

public class DBConnect {
    public static Connection connection;
    public DBConnect() {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://db.zryk.xyz/lokalisasibali", "david", "Password123");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
