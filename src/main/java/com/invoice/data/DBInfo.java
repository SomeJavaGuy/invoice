package com.invoice.data;

// https://remotemysql.com/phpmyadmin/index.php


public class DBInfo {
    private static String host = "remotemysql.com";
    private static String port = "3306";
    private static String user = "psM5g6VQz2";
    private static String pass = "JOmGCCh5HB";
    private static String db = "psM5g6VQz2";
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
