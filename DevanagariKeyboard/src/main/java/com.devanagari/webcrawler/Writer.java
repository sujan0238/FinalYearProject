package com.devanagari.webcrawler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

/**
 * Created by Sujan  Chauhan on 6/29/2016.
 */
public class Writer {
    private String siteName;

    public Writer(String siteName){
        this.siteName = siteName;

    }
    public void storeCrawlText(CrawlText crawlText){
        BufferedWriter bufferedWriter  = null;
        try{
            File file = new File("file/" +siteName+ ".txt");

            if(!file.exists()){
                try{
                    file.createNewFile();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

            FileWriter fileWriter = null;
            try{
                fileWriter = new FileWriter(file,true);
            }catch(IOException e){
                e.printStackTrace();
            }

            bufferedWriter = new BufferedWriter(fileWriter);
            try{
                bufferedWriter.write(crawlText.getText().toString());
            }catch(IOException e){
                e.printStackTrace();
            }
        }finally {
            try{
                if(bufferedWriter!=null)
                    bufferedWriter.close();
            }catch(Exception e){
                System.out.println("Error in closing..........."+e);
            }
        }
        }



    public void storeVisitedPage(Set<String> visitedPage){
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("visit/"+siteName+".txt")))){
            for(String pageVisited:visitedPage){
                bufferedWriter.write(pageVisited);
                bufferedWriter.newLine();
            }


        }catch (IOException e){
            e.printStackTrace();
        }

    }
    public void storeErrorPage(Set<String> errorPage){
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("error/"+siteName+".txt")))){
            for(String pageError:errorPage){
                bufferedWriter.write(pageError);
                bufferedWriter.newLine();
            }

        }catch (IOException e){
            e.printStackTrace();
        }


    }
}
