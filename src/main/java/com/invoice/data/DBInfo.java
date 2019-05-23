package com.invoice.data;

public class DBInfo {
    private static String host = "localhost";
    private static String port = "3306";
    private static String user = "someuser";
    private static String pass = "3eidv9djrieew";
    private static String db = "invoice_db";
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String dbURL = "jdbc:mysql://" + host + ":" + port + "/" + db + "";

    public static String getDatabaseURL() {
        return dbURL;
    }

    public static String getUser() {
        return user;
    }

    public static String getPassword() {
        return pass;
    }

    public static String getDriver() {
        return driver;
    }

}
