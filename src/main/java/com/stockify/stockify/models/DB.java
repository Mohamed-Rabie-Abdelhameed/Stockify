package com.stockify.stockify.models;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
    public static Connection connectDB(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/inventory_management", "root", "");
            return conn;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
