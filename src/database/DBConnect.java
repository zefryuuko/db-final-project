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
    private Connection con;
    private Statement st;
    private ResultSet rs;

    DBConnect() {
        try {  //in-capsulate the function into a try and catch

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://db.zryk.xyz", "david", "Password123");
            st = con.createStatement();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
