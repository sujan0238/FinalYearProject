package com.devanagari.util;

/**
 * Created by Sujan  Chauhan on 6/29/2016.
 */
public class URLHandler {
    public static String getDomainName(String rootUrl){

        String siteNames = rootUrl;
        siteNames = siteNames.replaceAll("/","");
        siteNames = siteNames.replaceAll("http:","");
        siteNames = siteNames.replaceAll("www.","");
        siteNames = siteNames.replaceAll(".com","");
        siteNames = siteNames.replaceAll(".np","");

        return siteNames;

    }
}
