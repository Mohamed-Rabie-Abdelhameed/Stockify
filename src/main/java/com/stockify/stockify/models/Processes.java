package com.stockify.stockify.models;

import com.stockify.stockify.Snackbar;

import java.sql.*;
import java.util.Arrays;

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

    public static int getNumberOfSuppliers(){
        String query = "SELECT COUNT(*) FROM suppliers";
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

    public static int getNumberOfCategories(){
        String query = "SELECT COUNT(*) FROM categories";
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

    public static Order[] getAllOrders() {
        String query = "SELECT * FROM orders";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            int count = 0;
            while (rs.next()) {
                count++;
            }
            Order[] orders = new Order[count];
            rs = pst.executeQuery();
            int i = 0;
            while (rs.next()) {
                orders[i] = new Order(rs.getInt("order_id"),rs.getDate("order_date") ,rs.getDate("delivery_date"),rs.getString("status"), rs.getInt("product_id"), rs.getInt("quantity"));
                i++;
            }
            return orders;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean updateOrder(Order order) {
        String query = "UPDATE orders SET order_date = ?, delivery_date = ?, status = ?, product_id = ?, quantity = ? WHERE order_id = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setDate(1, (Date) order.getOrderDate());
            pst.setDate(2, (Date) order.getDeliveryDate());
            pst.setString(3, order.getStatus());
            pst.setInt(4, order.getProductId());
            pst.setInt(5, order.getQuantity());
            pst.setInt(6, order.getOrderId());
            int res = pst.executeUpdate();
            return res > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean deleteOrder(Order order) {
        String query = "DELETE FROM orders WHERE order_id = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, order.getOrderId());
            int res = pst.executeUpdate();
            return res > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addOrder(Order newOrder) {
        String query = "INSERT INTO orders (order_date, delivery_date, status, product_id, quantity) VALUES (?, ?, ?, ?, ?)";
        java.sql.Date sqlOrderDate = new java.sql.Date(newOrder.getOrderDate().getTime());
        java.sql.Date sqlDeliveryDate = new java.sql.Date(newOrder.getDeliveryDate().getTime());
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setDate(1, sqlOrderDate);
            pst.setDate(2, sqlDeliveryDate);
            pst.setString(3, newOrder.getStatus());
            pst.setInt(4, newOrder.getProductId());
            pst.setInt(5, newOrder.getQuantity());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getNumberOfOrders(){
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

    public static int getNumberOfOrdersByStatus(String status){
        String query = "SELECT COUNT(*) FROM orders WHERE status = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, status);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static double getTotalCost(){
        double totalCost = 0.0;
        String query = "Select SUM(unit_price * quantity) FROM products, orders WHERE products.product_id = orders.product_id";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                totalCost = rs.getDouble(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return totalCost;
    }

    public static Supplier[] getAllSuppliers() {
        String query = "SELECT * FROM suppliers";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            int count = 0;
            while (rs.next()) {
                count++;
            }
            Supplier[] suppliers = new Supplier[count];
            rs = pst.executeQuery();
            int i = 0;
            while (rs.next()) {
                suppliers[i] = new Supplier(rs.getInt("supplier_id"), rs.getString("name"), rs.getString("address"), rs.getString("phone"), rs.getString("email"));
                i++;
            }
            return suppliers;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean updateSupplier(Supplier supplier) {
        String query = "UPDATE suppliers SET name = ?, address = ?, phone = ?, email = ? WHERE supplier_id = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, supplier.getName());
            pst.setString(2, supplier.getAddress());
            pst.setString(3, supplier.getPhone());
            pst.setString(4, supplier.getEmail());
            pst.setInt(5, supplier.getId());
            int res = pst.executeUpdate();
            return res > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addSupplier(Supplier newSupplier) {
        String query = "INSERT INTO suppliers (name, address, phone, email) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, newSupplier.getName());
            pst.setString(2, newSupplier.getAddress());
            pst.setString(3, newSupplier.getPhone());
            pst.setString(4, newSupplier.getEmail());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean deleteSupplier(Supplier supplier) {
        String query = "DELETE FROM suppliers WHERE supplier_id = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, supplier.getId());
            int res = pst.executeUpdate();
            return res > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean updateCategory(Category category) {
        String query = "UPDATE categories SET category_name = ? WHERE category_id = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, category.getName());
            pst.setInt(2, category.getId());
            int res = pst.executeUpdate();
            return res > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Category[] getAllCategories() {
        String query = "SELECT * FROM categories";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            int count = 0;
            while (rs.next()) {
                count++;
            }
            Category[] categories = new Category[count];
            rs = pst.executeQuery();
            int i = 0;
            while (rs.next()) {
                categories[i] = new Category(rs.getInt("category_id"), rs.getString("category_name"));
                i++;
            }
            return categories;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void addCategory(Category newCategory) {
        String query = "INSERT INTO categories (category_name) VALUES (?)";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, newCategory.getName());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean deleteCategory(Category category) {
        String query = "DELETE FROM categories WHERE category_id = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, category.getId());
            int res = pst.executeUpdate();
            return res > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getBestCategory() {
        String query = "SELECT category_name, COUNT(*) AS count FROM categories, products WHERE categories.category_id = products.category_id GROUP BY category_name ORDER BY count DESC LIMIT 1";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getString("category_name");
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String[] getLowInStockCategories() {
        String query = "SELECT category_name FROM categories, products WHERE categories.category_id = products.category_id AND quantity_in_stock < 10 GROUP BY category_name";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            int count = 0;
            while (rs.next()) {
                count++;
            }
            String[] categories = new String[count];
            rs = pst.executeQuery();
            int i = 0;
            while (rs.next()) {
                categories[i] = rs.getString("category_name");
                i++;
            }
            return categories;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    get the quantity of each of categories
    public static ChartArrays getQuantityOfEachCategory() {
        String query = "SELECT category_name, COUNT(*) AS count FROM categories, products WHERE categories.category_id = products.category_id GROUP BY category_name";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            int count = 0;
            while (rs.next()) {
                count++;
            }
            String[] categories = new String[count];
            int[] quantity = new int[count];
            rs = pst.executeQuery();
            int i = 0;
            while (rs.next()) {
                categories[i] = rs.getString("category_name");
                quantity[i] = rs.getInt("count");
                i++;
            }
            return new ChartArrays(categories, quantity);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
