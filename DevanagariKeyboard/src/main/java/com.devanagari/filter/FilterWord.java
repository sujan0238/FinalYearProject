package com.devanagari.filter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sujan  Chauhan on 6/30/2016.
 */
public class FilterWord {
    public static void main(String[] args) throws Exception {
        List<String> site = new ArrayList<String>();

        site.add("newsabhiyan.txt");
        site.add("kantipur.ekantipur.txt");
        site.add("pahilopost.txt");
        site.add("ujyaaloonline.txt");

       /* File f = new File("file/");
        File files[] = f.listFiles();*/

        for (int i = 0; i < 4; i++) {
            //Input File
            File in = new File("file/"+site.get(i));
            //Output file
            File out = new File("filter/"+site.get(i));

            BufferedReader bufferedReader = new BufferedReader(new FileReader(in));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(out));

            String startLine;

            while((startLine=bufferedReader.readLine())!=null){
                String trimLine = startLine.trim();
                StringBuilder builder = new StringBuilder(trimLine);
                for(char ch: trimLine.toCharArray()){
                    if (!((ch >= Integer.parseInt("00900", 16) && ch <= Integer.parseInt("0097F", 16) || ch == ' ')
                            && !(ch >= Integer.parseInt("00966", 16) && ch <= Integer.parseInt("0096F", 16)))) {
                        builder.deleteCharAt(builder.indexOf(ch + ""));
                    }
                }

                if(builder.length()>0){
                        bufferedWriter.write(builder.toString().trim());

                    }
                bufferedWriter.newLine();
            }

                bufferedWriter.close();
        }


    }
}
