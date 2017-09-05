package com.devanagari;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Sujan  Chauhan on 8/22/2016.
 */
public class WordSuggestor {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/devanagari?useUnicode=true&characterEncoding=utf-8";

    static final String USER = "root";
    static final String PASS = "";

    public Set<String> getSuggestion(String inputWord){

        Set<String> suggestions = new HashSet<>();
        Connection connection = null;
        Statement statement = null;
        try{
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting.........");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            //Inserting in database
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM wordlist WHERE wordOne = '" + inputWord + "' order by total_count desc");

            while(resultSet.next()){

                suggestions.add(resultSet.getString("wordTwo").trim());
            }
            return suggestions;
        }catch(SQLException ex){
            ex.printStackTrace();

        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }
}
