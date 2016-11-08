package uk.co.compendiumdev.letsautomate.googlesearch;

import org.junit.Test;

import java.util.List;

/**
 * Created by Alan on 07/11/2016.
 */
public class PageRankRefactoredTest {

    @Test
    public void pageRankTool(){

        // TODO
        //PageRankResult result = new GooglePageRankChecker("https://google.co.uk").checkRank("prolog programmer", "compendiumdev.co.uk");


        GoogleSearch google = new GoogleSearch("https://google.co.uk");

        String searchTerm=  "selenium training"; // "prolog programmer";;     "dear evil tester"; //
        String matchInUrl="compendiumdev.co.uk";    // page 2 on google.co.uk but not on google.com

        // search page

        google.searchFor(searchTerm, matchInUrl);

        // search results page

        int pageNumber=0;
        boolean foundMatch=false;
        boolean haveMoreResults;
        String foundMatchingUrl="";

        do{

            pageNumber++;

            google.results().waitUntilAvailable();

            List<TitledUrl> titledUrls = google.results().returnUrls();

            for(TitledUrl aUrl : titledUrls){

                System.out.println(aUrl.href);
                System.out.println(aUrl.title);

                if(aUrl.href.contains(matchInUrl)){
                    System.out.println("****FOUND MATCH*** on page " + pageNumber);
                    foundMatchingUrl = aUrl.href;
                    foundMatch=true;
                }

            }

            haveMoreResults = google.results().getMoreResults();

        }while(!foundMatch && haveMoreResults);

        if(foundMatch) {
            System.out.println(String.format("Found match on Page Number %d - %s", pageNumber, foundMatchingUrl));
        }else{
            System.out.println(String.format("Found no match after Page Number %d", pageNumber));
        }

        google.endSearch();
    }
}
