package com.kce.util;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
   static Connection con;
    public static Connection createDBConnetion(){

        try{
           Class.forName("oracle.jdbc.driver.OracleDriver");
           con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE", "system", "1234");

        }catch (Exception e){
            System.out.println(e);
        }
     return con;
    }
}