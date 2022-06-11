package com.example.pnvproject.dataa;

import com.example.pnvproject.models.Painting;

import java.sql.*;
import java.util.ArrayList;

public class DBConnect {
    private Connection connection;

    public static final String URL = "jdbc:mysql://localhost/project";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";

    public DBConnect(){
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connect successfully!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Phone> getPhone(){
        ArrayList<Phone> list = new ArrayList<>();
        String sql = "SELECT * FROM phone";
        try {
            ResultSet results = connection.prepareStatement(sql).executeQuery();
            while (results.next()){
//                System.out.println(results.getInt("id"));
//                System.out.println(results.getString("name"));
//                System.out.println(results.getFloat("score"));
                Phone phones = new Phone(
                        results.getInt("id"),
                        results.getString("phonename"),
                        results.getFloat("phoneprice"),
                        results.getInt("quantity")
                );
                list.add(phones);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
    public void insertPhone(Phone phones){
        String sql = "INSERT INTO phone (phonename, phoneprice, quantity) VALUE ('"+phones.phonename+"','"+phones.phoneprice
                +"','"+phones.quantity+"')";
        try {
            connection.prepareStatement(sql).executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updatePhone(Phone phones){
        String sql = "UPDATE phone SET phonename = '"+ phones.phonename +"', phoneprice = '"+phones.phoneprice+
              "', quantity = '"+phones.quantity+"' WHERE id = "+ phones.id;
        try {
            connection.prepareStatement(sql).executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletePhone(int id){
        String sql = "DELETE FROM phone WHERE id = "+ id;
        try {
            connection.prepareStatement(sql).executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
