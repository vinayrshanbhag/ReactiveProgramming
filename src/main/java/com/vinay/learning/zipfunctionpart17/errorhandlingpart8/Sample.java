package com.vinay.learning.zipfunctionpart17.errorhandlingpart8;

import rx.Observable;

import java.util.Arrays;
import java.util.List;

public class Sample {

    public static void main(String[] args) {
        List<String> symbols = Arrays.asList("GOOG", "AAPL", "MFST", "AMZN");

        final Observable<StockInfo> feed = StockServer.getFeed(symbols);


        feed.subscribe(System.out::println,
                       (err-> System.out.println(err + "got error")),
                       () -> System.out.println(" No more data coming. end signal....")
        );
    }


}
