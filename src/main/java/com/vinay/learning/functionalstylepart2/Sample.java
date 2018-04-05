package com.vinay.learning.functionalstylepart2;

import rx.Observable;
import rx.functions.Action1;

import java.util.Arrays;
import java.util.List;

/**
 * Created by vinayshanbhag on 4/3/18.
 */
public class Sample {

    public static void main(String[] args) {
       List<String> symbols = Arrays.asList("GOOG","AAPL","MFST","AMZN");

       Observable<StockInfo> feed = StockServer.getFeed(symbols);


        System.out.println("Got an observerable");
        System.out.println(feed);
        feed.subscribe(System.out::println);



    }
}
