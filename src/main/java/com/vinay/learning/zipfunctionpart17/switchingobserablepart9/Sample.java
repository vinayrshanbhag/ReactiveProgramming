package com.vinay.learning.zipfunctionpart17.switchingobserablepart9;

import rx.Observable;

import java.util.Arrays;
import java.util.List;

public class Sample {

    public static void main(String[] args) {
        List<String> symbols = Arrays.asList("GOOG", "AAPL", "MFST", "AMZN");

       getObservable(symbols)
                       .onErrorResumeNext((throwable -> goToBackUpService(throwable,symbols)))
                       .subscribe(
                        System.out::println,
                       (err-> System.out.println(err + "got error")),
                       () -> System.out.println(" No more data coming. end signal....")
        );
    }

    private static Observable<StockInfo> goToBackUpService(Throwable throwable, List<String> symbols) {
        System.out.println("Error Getting another Observable" + throwable);
        return  StockServer.getFeed(symbols);

    }

    public static Observable<StockInfo> getObservable(List<String> symbols){
        return  StockServer.getFeed(symbols);
    }


}
