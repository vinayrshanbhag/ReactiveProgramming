package com.vinay.learning.functionalstylepart2;

import rx.Observable;
import rx.Subscriber;

import java.util.List;

/**
 * Created by vinayshanbhag on 4/4/18.
 */
public class StockServer {
    public static Observable<StockInfo> getFeed(final List<String> symbols) {

        Observable.OnSubscribe<StockInfo> processData = subscriber ->
                symbols.stream()
                       .map(StockInfo::fetch)
                       .forEach(subscriber::onNext);

        return Observable.create(processData);

    }


    private static void produceData(Subscriber<? super StockInfo> subscriber, List<String> symbols) {
        System.out.println("You called..");

        symbols.stream()
                .map(StockInfo::fetch)
                .forEach(subscriber::onNext);
    }
}
