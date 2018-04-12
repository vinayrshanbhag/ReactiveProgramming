package com.vinay.learning.zipfunctionpart17.unsubscribepart5;

import rx.Observable;
import rx.Subscriber;

import java.util.Arrays;
import java.util.List;

/**
 * Created by vinayshanbhag on 4/3/18.
 */
public class Sample {

    public static void main(String[] args) throws InterruptedException {
       List<String> symbols = Arrays.asList("GOOG","AAPL","MFST","AMZN");

       Observable<StockInfo> feed = StockServer.getFeed(symbols)
                                               .takeWhile(stockInfo -> stockInfo.value>60);



        System.out.println("Got an observerable");
        System.out.println(feed);
//        feed.subscribe(System.out::println);

        feed.subscribe(new Subscriber<StockInfo>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(StockInfo stockInfo) {
                System.out.println(stockInfo);
            }
        });
        Thread.sleep(5000);

    }
}
