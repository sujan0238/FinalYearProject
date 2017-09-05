package com.devanagari.lineSeparated;

import java.io.*;
import java.util.*;

/**
 * Created by Sujan  Chauhan on 8/16/2016.
 */
public class LineSeparator {

    public static void main(String[] args) throws IOException {

        Map map = new HashMap();

        List<String> site = new ArrayList<>();

        site.add("newsabhiyan.txt");
        site.add("ujyaaloonline.txt");
        site.add("pahilopost.txt");
        site.add("kantipur.ekantipur.txt");

        for (int i = 0; i < 4; i++) {
            //Input  and Output file to split
            File inputFile = new File("filter/" + site.get(i));
            File outputfile = new File("afterStopChars/" + site.get(i));

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputfile));

            String startLine;
            while ((startLine = reader.readLine()) != null) {

                char[] chars = startLine.toCharArray();
                for(char c: chars) {
                    if (c == Integer.parseInt("00964", 16) || c == Integer.parseInt("00967D", 16)) {

                        writer.newLine();
                    }else{
                        writer.write(c);
                    }
                }
                writer.newLine();
            }

            writer.close();
        }
    }
}
