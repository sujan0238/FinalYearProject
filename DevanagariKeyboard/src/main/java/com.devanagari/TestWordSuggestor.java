package com.devanagari;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by Sujan  Chauhan on 8/22/2016.
 */
public class TestWordSuggestor {

    public static void main(String[] args) {

        WordSuggestor wordSuggestor = new WordSuggestor();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a word to get suggestion: ");

        String words = scanner.nextLine();

        String lastWord;

        if(words.lastIndexOf(' ') != -1){

            lastWord = words.substring(words.lastIndexOf(' '), words.length()).trim();
        }else{
            lastWord = words.trim();
        }

       // System.out.println("The lastword is : " + lastWord);

        Set<String> suggestions = wordSuggestor.getSuggestion(lastWord);
        System.out.println("The suggestion are: ");
        for(String suggestion: suggestions) {

            System.out.println(suggestion);
        }
    }
}
