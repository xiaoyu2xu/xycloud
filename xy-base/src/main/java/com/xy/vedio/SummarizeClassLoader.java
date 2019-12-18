package com.xy.vedio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by asus on 2019/8/9.
 */
public class SummarizeClassLoader {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","username","password");
    }
}
