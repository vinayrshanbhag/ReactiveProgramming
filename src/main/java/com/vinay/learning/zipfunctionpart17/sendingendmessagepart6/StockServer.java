package com.vinay.learning.zipfunctionpart17.sendingendmessagepart6;

import rx.Observable;
import rx.Subscriber;

import java.util.List;

public class StockServer {

    public static Observable<StockInfo> getFeed(List<String> symbols){
        return Observable.create(subscriber -> processSymbols(subscriber,symbols));
    }

    private static void processSymbols(Subscriber<? super StockInfo> subscriber,
                                          List<String> symbols) {

        int count = 0;

        while(count<10){
            symbols.stream()
                    .map(StockInfo::fetch)
                    .forEach(subscriber::onNext);
            count++;
        }

        subscriber.onCompleted();


        // we wont sending this as the end signal is sent
        subscriber.onNext(StockInfo.fetch("GOOG"));
    }

}
