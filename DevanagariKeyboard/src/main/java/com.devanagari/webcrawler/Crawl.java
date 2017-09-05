package com.devanagari.webcrawler;

import com.devanagari.util.URLHandler;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Sujan  Chauhan on 6/29/2016.
 */
public class Crawl {
    private Set<String> visitedPages = new HashSet<>();
    private Deque<String> pagesToVisit = new ArrayDeque<>();
    private Writer fileWriter;
    private String rootUrl;
    private SpiderLeg leg;
    private CrawlText crawlText = new CrawlText();
    private String siteName;

    public Crawl(String rootUrl){
        this.rootUrl = rootUrl;
        siteName = URLHandler.getDomainName(rootUrl);
        fileWriter = new Writer(siteName);
        leg = new SpiderLeg(rootUrl);
    }

    public void search(){
        pagesToVisit.push(rootUrl);
        while(pagesToVisit.size()!=0){
            String visitedUrl = pagesToVisit.pop();
            crawlText.joinText(leg.crawler(visitedUrl));
            visitedPages.add(visitedUrl);
            for(String link : leg.getLink()) {
                if (link.contains(rootUrl)
                        &&!link.equalsIgnoreCase(rootUrl)
                        && !pagesToVisit.contains(link)
                        && !visitedPages.contains(link))
                        pagesToVisit.push(link);

                if(crawlText.getLength() >= 394240){
                    System.out.println("Saving............." +siteName);
                    saveCrawlText();
                    crawlText.reset();
                }
             }
         }
        if(crawlText.getLength()!=0){
            System.out.println("Saving the remaining....." +siteName);
            saveCrawlText();
            crawlText.reset();
        }
        System.out.println("Visited pages" +visitedPages.size());
        saveVisitedPage();
        saveErrorPage();
    }
        public void saveCrawlText(){

            fileWriter.storeCrawlText(crawlText);
        }

    public void saveVisitedPage(){

        fileWriter.storeVisitedPage(visitedPages);
    }

    public void saveErrorPage(){
        fileWriter.storeErrorPage(leg.getErrorPage());

    }

}
