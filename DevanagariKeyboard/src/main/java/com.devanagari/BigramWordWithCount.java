package com.devanagari;

/**
 * Created by Sujan  Chauhan on 8/23/2016.
 */
public class BigramWordWithCount {

    private String wordOne;
    private String wordTwo;
    private long count;

    public BigramWordWithCount(String wordOne, String wordTwo, long count) {
        this.wordOne = wordOne;
        this.wordTwo = wordTwo;
        this.count = count;
    }

    public String getWordOne() {
        return wordOne;
    }

    public void setWordOne(String wordOne) {
        this.wordOne = wordOne;
    }

    public String getWordTwo() {
        return wordTwo;
    }

    public void setWordTwo(String wordTwo) {
        this.wordTwo = wordTwo;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public void incrementCount(){

        count++;
    }
}
