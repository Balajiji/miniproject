package com.kce.Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.kce.bean.Customer;
import com.kce.util.DBConnection;

public class CustomerDao implements CustomerDaoInt{
    static Connection con=null;

    public static void createTable() {
        // SQL statement to create the table
    	con=DBConnection.createDBConnetion();
    	try {
//    	 Statement statement=con.createStatement();
       // statement.execute("drop table customer");
//        String createTableQuery = "CREATE TABLE customer (id INT, name VARCHAR(255), amount decimal(10,2),age INT)";
//
//        
//            
//            // Execute the create table query
//           
//            statement.executeUpdate(createTableQuery);
            System.out.println("Table created successfully.");
             
        } catch (Exception e) {
            System.err.println("Error creating table: " + e.getMessage());
        }
    }
    public void createCustomer(Customer user) {
       con =DBConnection.createDBConnetion();
      String query="insert into customer values(?,?,?,?)";
       try{
           PreparedStatement pstm=con.prepareStatement(query);
           pstm.setInt(1,user.getId());
           pstm.setString(2,user.getName());
           pstm.setDouble(3,user.getAmount());
           pstm.setInt(4,user.getAge());
          int cnt= pstm.executeUpdate();
          if(cnt>0)
              System.out.println("Customer Data Inserted Successfully !!!");


       }catch (Exception e){
           System.out.println(e);
       }

    }

    @Override
    public void showAllCustomer() {
        con=DBConnection.createDBConnetion();
        String query="select * from customer";
        System.out.println("Customer Details :");
        System.out.println("---------------------------------------------");

        System.out.format("%s\t%s\t%s\t%s\n","ID","Name","Loan_Amount","age");
        System.out.println("---------------------------------------------");

        try{
            Statement stmt=con.createStatement();
            ResultSet result= stmt.executeQuery(query);
            while (result.next()){
                System.out.format("%d\t%s\t%f\t%d\n",
                        result.getInt(1),
                        result.getString(2),
                        result.getDouble(3),
                        result.getInt(4));
                        //result.getInt(5);
                System.out.println("---------------------------------------------");

            }
            

        }catch (Exception e){
            System.out.println(e);
        }

    }

    @Override
    public void showCustomerBasedOnID(int id) {
        con=DBConnection.createDBConnetion();
        String query="select * from customer where id="+id;
        try{
            Statement stmt=con.createStatement();
           ResultSet result= stmt.executeQuery(query);
           while (result.next()){
               System.out.format("%d\t%s\t%f\t%d\n",
                       result.getInt(1),
                       result.getString(2),
                       result.getDouble(3),
                       result.getInt(4));

           }

        }
        catch (Exception e){
            System.out.println(e);
        }

    }

    @Override
    public void updateCustomer(int id, String name) {
        con=DBConnection.createDBConnetion();
        String query="update customer set name=? where id=?";
        try{
            PreparedStatement pstm=con.prepareStatement(query);
            pstm.setString(1,name);
            pstm.setInt(2,id);
            int cnt=pstm.executeUpdate();
            if(cnt!=0)
                System.out.println("Customer Details updated successfully !!");

        }catch (Exception e){
           e.printStackTrace();
        }

    }

    @Override
    public void deleteCustomer(int id) {
        con=DBConnection.createDBConnetion();
        String query="delete from Customer where id=?";
        try{
            PreparedStatement pstm=con.prepareStatement(query);
            pstm.setInt(1,id);
           int cnt= pstm.executeUpdate();
           if(cnt!=0)
               System.out.println("Customer Data Deleted Successfully!!! "+id);

        }catch (Exception e){
            System.out.println(e);
        }
    }


}