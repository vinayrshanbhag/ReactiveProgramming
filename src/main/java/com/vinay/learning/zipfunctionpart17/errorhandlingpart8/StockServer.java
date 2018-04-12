package com.vinay.learning.zipfunctionpart17.errorhandlingpart8;

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
        IntStream.range(1,5)
                .forEach(index-> pushStockInfo(subscriber, symbols));

//       subscriber.onError(new RuntimeException("Oh oh.."));

        subscriber.onNext(new StockInfo(" ",0.0));
        subscriber.onCompleted();


    }

    private static void pushStockInfo(Subscriber<? super StockInfo> subscriber, List<String> symbols) {
        symbols.stream()
                .map(StockInfo::fetch)
                .forEach(subscriber::onNext);
    }

}
