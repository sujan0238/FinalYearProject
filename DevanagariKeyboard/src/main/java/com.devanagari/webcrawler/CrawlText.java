package com.devanagari.webcrawler;

/**
 * Created by Sujan  Chauhan on 6/29/2016.
 */
public class CrawlText {
    private StringBuilder  text = new StringBuilder();

    public StringBuilder getText(){
        return text;
    }

    public CrawlText appendText(StringBuilder text){
        this.text.append(text);
        return this;
    }

    public CrawlText joinText(CrawlText crawlText){
        text.append(crawlText.getText());
        return this;

    }

    public int getLength(){
        return text.length();
    }

    public CrawlText reset() {
        text.setLength(0);
        return this;
    }
}
