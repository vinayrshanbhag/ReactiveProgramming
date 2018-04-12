package com.vinay.learning.zipfunctionpart17.multiplefallbackspart10;

import rx.Observable;
import rx.Subscriber;

import java.util.List;
import java.util.stream.Stream;

public class StockServer {

    public static Observable<StockInfo> getFeed(List<String> symbols){
        return Observable.create(subscriber -> processSymbols(subscriber,symbols));
    }

    private static void processSymbols(Subscriber<? super StockInfo> subscriber,
                                          List<String> symbols) {
        Stream.iterate(0, e->e+1)
                .forEach(index-> pushStockInfo(subscriber, symbols));

//       subscriber.onError(new RuntimeException("Oh oh.."));




    }

    private static void pushStockInfo(Subscriber<? super StockInfo> subscriber, List<String> symbols) {
        symbols.stream()
                .map(StockInfo::fetch)
                .forEach(subscriber::onNext);
    }

}
