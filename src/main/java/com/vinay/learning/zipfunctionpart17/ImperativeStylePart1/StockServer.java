package com.vinay.learning.zipfunctionpart17.ImperativeStylePart1;

import java.util.List;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by vinayshanbhag on 4/4/18.
 */
public class StockServer {
    public static Observable<StockInfo> getFeed(final List<String> symbols) {
        return Observable.create(new Observable.OnSubscribe<StockInfo>() {
            public void call(Subscriber<? super StockInfo> subscriber) {
                System.out.println("You called..");
                for(String symbol: symbols){
                    subscriber.onNext(StockInfo.fetch(symbol));
                }
            }
        });
    }
}
