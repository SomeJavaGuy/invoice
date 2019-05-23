package com.invoice.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBController {

    public void addProductToDatabase(Product product) {

        Connection connection = getConnection();
        PreparedStatement statement;

        try {
            statement = connection.prepareStatement("INSERT INTO products ( name , netto , vat) VALUES (?,?,?)");

            statement.setString(1, product.getName());
            statement.setString(2, product.getNettoPrice());
            statement.setString(3, product.getVAT());

        } catch (SQLException e) {
            System.out.println("Error. Can not create the statement: " + e);
            return;
        }

        try {
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error. Problem with executeUpdate: " + e);
            return;
        }

        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error. Problem with closing connection: " + e);
        }
    }

    public List<Product> getProductsList() {

        List<Product> productsList = new ArrayList<>();

        Connection connection = getConnection();
        Statement statement;
        ResultSet result = null;


        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("Error. Can not create the statement: " + e);
            return null;
        }

        try  {
            result = statement.executeQuery("SELECT * FROM products");
        } catch (SQLException e) {
            System.out.println("Error. Problem with executeQuery: " + e);
            return null;
        }

        try {
            while (result.next()) {
                Product product = new Product(result.getString(2), result.getString(3), result.getString(4));
                productsList.add(product);
            }
        } catch (SQLException e) {
            System.out.println("Error. Problem reading data: " + e);
            return null;
        }

        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error. Problem with closing connection: " + e);
            return null;
        }

        return productsList;
    }

    private Connection getConnection() {

        Connection connection;

        String dbURL = DBInfo.getDatabaseURL();
        String dbUser = DBInfo.getUser();
        String dbPassword = DBInfo.getPassword();

        try {
            Class.forName(DBInfo.getDriver());
        } catch (ClassNotFoundException e) {
            System.out.println("Error. Driver class not found: " + e);
        }

        try {
            connection = DriverManager.getConnection(dbURL, dbUser, dbPassword);
            return connection;
        } catch (SQLException e) {
            System.out.println("Error. Connection problem: " + e);
        }

        return null;
    }

}
