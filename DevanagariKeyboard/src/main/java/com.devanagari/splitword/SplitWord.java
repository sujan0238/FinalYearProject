package com.devanagari.splitword;


import com.devanagari.BigramWordWithCount;

import java.io.*;
import java.sql.*;
import java.util.*;

/**
 * Created by Sujan  Chauhan on 6/30/2016.
 */


public class SplitWord {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/devanagari?useUnicode=true&characterEncoding=utf-8";

    static final String USER = "root";
    static final String PASS = "";

    public static void main(String[] args)   throws IOException {
        /*StringBuilder builder = new StringBuilder();
        Map map = new HashMap();*/

        List<String> site = new ArrayList<>();

        site.add("pahilopost.txt");
        site.add("kantipur.ekantipur.txt");
        site.add("newsabhiyan.txt");

        List<BigramWordWithCount> bigramWordsWithCount = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            //Input  and Output file to split
            File inputFile = new File("afterStopChars/" + site.get(i));
            File outputfile = new File("split/" + site.get(i));

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputfile));

            String startLine;
            while ((startLine = reader.readLine()) != null) {

                String[] words = startLine.split(" ");

                for(int j = 0; j < words.length; j++){

                    if(j + 1 == words.length){

                        continue;
                    }
                    if(!words[j].trim().equals("") && !words[j + 1].trim().equals("")){

                        boolean flag = false;
                        for(BigramWordWithCount bigramWordWithCount : bigramWordsWithCount){

                            if(bigramWordWithCount.getWordOne().equals(words[j]) && bigramWordWithCount.getWordTwo().equals(words[j+1])){

                                bigramWordWithCount.incrementCount();
                                flag = true;
                                break;
                            }
                        }
                        if(!flag) {
                            BigramWordWithCount bigramWordWithCount = new BigramWordWithCount(words[j], words[j + 1], 1);

                            bigramWordsWithCount.add(bigramWordWithCount);
                        }
                    }
                }
            }

            System.out.println(bigramWordsWithCount.size());

            writer.close();
        }
        Connection connection = null;
        Statement statement = null;


        try {
            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting.........");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            for(BigramWordWithCount bigramWordWithCount: bigramWordsWithCount){
                //Inserting in database
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT id FROM wordlist WHERE wordOne = '" + bigramWordWithCount.getWordOne() + "' and wordTwo = '" + "';");
                if(resultSet.next()){
                    statement = connection.createStatement();
                    statement.executeUpdate("UPDATE wordlist SET total_count = total_count + 1 WHERE id = " + resultSet.getInt("id"));
                }else{
                    statement = connection.createStatement();
                    statement.execute("INSERT INTO wordlist(wordOne, wordTwo, total_count) values('" + bigramWordWithCount.getWordOne() + "', '" + bigramWordWithCount.getWordTwo() + "', 1);");
                }
                statement.close();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
