package com.invoice.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private static String _host = "localhost";
    private static String _port = "8082";
    private static String _user = "someuser";
    private static String _pass = "3eidv9djrieew";
    private static String _db = "invoice_base";

    private static Connection connection;

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        if (connection == null) {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://" + _host + ":" + _port + "/" + _db + "";
            connection = DriverManager.getConnection(url, _user, _pass);
        }
        return connection;
    }

    public static List getProductsList() {
        List<Product> productsList = new ArrayList<>();
        try (PreparedStatement pstmt = getConnection().prepareStatement("SELECT * FROM product");
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Product product = new Product(rs.getString(1), rs.getString(2), rs.getString(3));
                productsList.add(product);
            }
        } catch (Exception e) {
            System.out.println("Error in getData" + e);
        }
        return productsList;
    }

    public static void addProductToDatabase(Product product) {
        try (PreparedStatement pstmt = getConnection().prepareStatement("INSERT INTO product (name, nettoPrice, bruttoPrice, VAT) " +
                "VALUES (" + product.getName() + ", " + product.getNettoPrice() + ", " + product.getBruttoPrice() + ", " + product.getVAT() + ")")) {
        } catch (Exception e) {
            System.out.println("Error in getData" + e);
        }
    }

}
