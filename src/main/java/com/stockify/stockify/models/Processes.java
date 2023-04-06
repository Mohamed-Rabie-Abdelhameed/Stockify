package com.stockify.stockify.models;

import com.stockify.stockify.Snackbar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Processes {
    static Connection conn = DB.connectDB();

    public static Product[] getAllProducts() {
        String query = "SELECT * FROM products";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            int count = 0;
            while (rs.next()) {
                count++;
            }
            Product[] products = new Product[count];
            rs = pst.executeQuery();
            int i = 0;
            while (rs.next()) {
                products[i] = new Product(rs.getInt("product_id"), rs.getString("product_name"), rs.getInt("category_id"),
                        rs.getInt("supplier_id"), rs.getDouble("unit_price"), rs.getInt("quantity_in_stock"));
                i++;
            }
            return products;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getInStock(){
        String query = "SELECT COUNT(*) FROM products";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getToBeReceived() {
        String query = "SELECT COUNT(*) FROM orders";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean updateProduct(Product product) {
        String query = "UPDATE products SET product_name = ?, category_id = ?, supplier_id = ?, unit_price = ?, quantity_in_stock = ? WHERE product_id = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, product.getName());
            pst.setInt(2, product.getCategoryID());
            pst.setInt(3, product.getSupplierID());
            pst.setDouble(4, product.getPrice());
            pst.setInt(5, product.getQuantity());
            pst.setInt(6, product.getId());
            int res = pst.executeUpdate();
            return res > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteProduct(int id) {
        String query = "DELETE FROM products WHERE product_id = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean addProduct(Product product) {
        String query = "INSERT INTO products (product_name, category_id, supplier_id, unit_price, quantity_in_stock) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, product.getName());
            pst.setInt(2, product.getCategoryID());
            pst.setInt(3, product.getSupplierID());
            pst.setDouble(4, product.getPrice());
            pst.setInt(5, product.getQuantity());
            int res = pst.executeUpdate();
            return res > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
