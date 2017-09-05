package com.devanagari.DBConnection;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Created by Sujan  Chauhan on 8/22/2016.
 */
public class DBConnection {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/devanagari?useUnicode=true&characterEncoding=utf-8";

    static final String USER = "root";
    static final String PASS = "";

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        try{
            System.out.println("Here");
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting.........");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            //Inserting in database
            System.out.println("Inserting........");
            statement = connection.createStatement();
            String sql = "Insert INTO wordList values('संबिधान  ','सभा ',24)";
            statement.executeUpdate(sql);


        }catch(SQLException ex){
            ex.printStackTrace();

        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        finally{
            try{
                if(statement!=null){
                    connection.close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
            try{
                if(connection!=null){
                    connection.close();

                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

    }

}
