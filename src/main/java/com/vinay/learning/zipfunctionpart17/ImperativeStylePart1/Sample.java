package com.vinay.learning.zipfunctionpart17.ImperativeStylePart1;

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
        feed.subscribe(new Action1<StockInfo>() {
            public void call(StockInfo stockInfo) {
                System.out.println(stockInfo);
            }
        });



    }
}
