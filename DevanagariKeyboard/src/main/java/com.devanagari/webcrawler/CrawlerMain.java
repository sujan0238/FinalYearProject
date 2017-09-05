package com.devanagari.webcrawler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Sujan  Chauhan on 6/29/2016.
 */
public class CrawlerMain {

    public static void main(String[] args) {
        List<String> siteToCrawl = new ArrayList<String>();

        siteToCrawl.add("http://www.pahilopost.com/");
        siteToCrawl.add("http://www.newsabhiyan.com.np/");
        siteToCrawl.add("http://ujyaaloonline.com/");
        siteToCrawl.add("http://kantipur.ekantipur.com/");

/*
        siteToCrawl.add("http://setopati.com/");
        siteToCrawl.add("http://www.onlinekhabar.com/");
        siteToCrawl.add("http://baahrakhari.com/np/");
        siteToCrawl.add("http://www.sajhapost.com/");
        siteToCrawl.add("http://saptahik.ekantipur.com/");
        siteToCrawl.add("http://nepal.ekantipur.com/");
        siteToCrawl.add("http://nari.ekantipur.com/");
        siteToCrawl.add("http://www.khabardabali.com/");
        siteToCrawl.add("http://www.nepalipatra.com/");
        siteToCrawl.add("http://www.nepalsarokar.com/");
        siteToCrawl.add("http://gorkhapatraonline.com/");
        siteToCrawl.add("http://www.nepalihimal.com/");
        siteToCrawl.add("http://www.madheshvani.com/");
        siteToCrawl.add("http://www.sandakpuronline.com/");*/


        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for(final String site:siteToCrawl){
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    new Crawl(site).search();
                }
            };
            executorService.execute(run);

        }
        executorService.shutdown();



    }
}
