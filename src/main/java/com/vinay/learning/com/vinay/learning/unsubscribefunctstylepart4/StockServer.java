package com.vinay.learning.com.vinay.learning.unsubscribefunctstylepart4;

import rx.Observable;
import rx.Subscriber;

import java.util.List;
import java.util.stream.Stream;

/**
 * Created by vinayshanbhag on 4/4/18.
 */
public class StockServer {
    public static Observable<StockInfo> getFeed(final List<String> symbols) {


        return Observable.create(subscriber -> produceData(subscriber,symbols));

    }


    private static void produceData(Subscriber<? super StockInfo> subscriber, List<String> symbols) {
        System.out.println("You called..");

        Stream.iterate(0, e->e+1)
               .map(index->symbols.get(index%symbols.size()))
                .filter(index->!subscriber.isUnsubscribed())
                .map(StockInfo::fetch)
                .forEach(subscriber::onNext);




    }
}
