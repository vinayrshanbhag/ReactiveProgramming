package com.vinay.learning.zipfunctionpart17.unsubscribepart5;

import rx.Observable;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by vinayshanbhag on 4/4/18.
 */
public class StockServer {
    public static Observable<StockInfo> getFeed(final List<String> symbols) {


        return Observable.interval(1,TimeUnit.SECONDS)
                         .map(index -> symbols.get((int)(index%symbols.size())))
                         .map(StockInfo::fetch);




    }



}
