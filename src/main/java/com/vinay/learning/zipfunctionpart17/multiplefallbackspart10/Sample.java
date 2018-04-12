package com.vinay.learning.zipfunctionpart17.multiplefallbackspart10;

import rx.Observable;

import java.util.Arrays;
import java.util.List;

public class Sample {

    public static void main(String[] args) {
        List<String> symbols = Arrays.asList("GOOG", "AAPL", "MFST", "AMZN");

       getObservable(symbols,5)
                       .subscribe(
                        System.out::println,
                       (err-> System.out.println(err + "got error")),
                       () -> System.out.println(" No more data coming. end signal....")
        );
    }

    private static Observable<StockInfo> getBackUpService(Throwable throwable, List<String> symbols, int count) {
        System.out.println("Error Getting another Observable" + throwable);
        if(count>1){
            return  getObservable(symbols,count-1);
        }
        else{
            return StockServer.getFeed(symbols);
        }


    }

    public static Observable<StockInfo> getObservable(List<String> symbols, int count){

        return  StockServer.getFeed(symbols)
                           .onErrorResumeNext(throwable -> getBackUpService(throwable,symbols,count));
    }


}
