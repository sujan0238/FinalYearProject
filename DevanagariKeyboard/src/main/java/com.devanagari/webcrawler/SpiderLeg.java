package com.devanagari.webcrawler;

import com.devanagari.util.URLHandler;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

/**
 * Created by Sujan  Chauhan on 6/29/2016.
 */
public class SpiderLeg {
    private String siteName;
    private int countUrl;
    private Set<String> errorPages;
    private static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome";
    private Document document;
    private List<String> link = new ArrayList<>();

    public SpiderLeg(String rootUrl){
        countUrl = 0;
        errorPages = new HashSet<>();
        siteName = URLHandler.getDomainName(rootUrl);
        System.out.println("---------------"+ siteName + "----------------");

    }

    public CrawlText crawler(String currentUrl){
        CrawlText crawlText = new CrawlText();
        try{
            Connection connection = Jsoup.connect(currentUrl).userAgent(USER_AGENT);
            document = connection.timeout(25*1000).get();

            System.out.println("URL(" + ++countUrl + "): Received web page at " + currentUrl);

            Elements linksOnPage = document.select("a[href]");
            for(Element links : linksOnPage)
                link.add(links.absUrl("href"));

            Elements textElements = document.select("h1,h2,h3,p");
            for(Element textElement:textElements)
                crawlText.appendText(new StringBuilder(textElement.text()));


        }catch(IOException e){
            System.out.println("HTTP request error" + e);
            System.out.println("URL:" +currentUrl);
            errorPages.add(currentUrl);
        }

        return crawlText;

    }

    public List<String> getLink(){

        return link;
    }

    public Set<String> getErrorPage(){

        return errorPages;
    }
}
