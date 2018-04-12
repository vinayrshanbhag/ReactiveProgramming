package com.vinay.learning.zipfunctionpart17.removingnoisepart7;

import rx.Observable;

import java.util.Arrays;
import java.util.List;

public class Sample {

    public static void main(String[] args) {
        List<String> symbols = Arrays.asList("GOOG", "AAPL", "MFST", "AMZN");

        final Observable<StockInfo> feed = StockServer.getFeed(symbols);

        /*feed.subscribe(new Subscriber<StockInfo>() {
            @Override
            public void onCompleted() {
                System.out.println("No more data coming through");
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(StockInfo stockInfo) {
                System.out.println(stockInfo);
            }
        });*/


        feed.subscribe(stockInfo -> System.out.println(stockInfo),
                err -> System.out.println(err),
                () -> System.out.println(" No more data coming. end signal....")
        );

        feed.subscribe(System.out::println,
                System.out::println,
                () -> System.out.println(" No more data coming. end signal....")
        );
    }


}
