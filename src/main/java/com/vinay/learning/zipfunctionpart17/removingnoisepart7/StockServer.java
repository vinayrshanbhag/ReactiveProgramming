package com.vinay.learning.zipfunctionpart17.removingnoisepart7;

import rx.Observable;
import rx.Subscriber;

import java.util.List;
import java.util.stream.IntStream;

public class StockServer {

    public static Observable<StockInfo> getFeed(List<String> symbols){
        return Observable.create(subscriber -> processSymbols(subscriber,symbols));
    }

    private static void processSymbols(Subscriber<? super StockInfo> subscriber,
                                          List<String> symbols) {


      //  Stream.iterate(0, e-> e+1)
               // .limit(4)
        IntStream.range(1,5)
                .forEach(index-> pushStockInfo(subscriber, symbols));



        /*int count = 0;

        while(count<10){
            pushStockInfo(subscriber, symbols);
            count++;
        }*/

        subscriber.onCompleted();


        // we wont sending this as the end signal is sent
        subscriber.onNext(StockInfo.fetch("GOOG"));
    }

    private static void pushStockInfo(Subscriber<? super StockInfo> subscriber, List<String> symbols) {
        symbols.stream()
                .map(StockInfo::fetch)
                .forEach(subscriber::onNext);
    }

}
