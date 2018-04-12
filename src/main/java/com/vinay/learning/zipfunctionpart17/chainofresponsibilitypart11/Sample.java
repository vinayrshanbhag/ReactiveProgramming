package com.vinay.learning.zipfunctionpart17.chainofresponsibilitypart11;

import rx.Observable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sample {

    public static void main(String[] args) {
        List<String> symbols = Arrays.asList("GOOG", "AAPL", "MFST", "AMZN");

        Map<String, Integer> numberOfShares = new HashMap<>();
        numberOfShares.put("GOOG", 100);
        numberOfShares.put("AAPL",200);

      StockServer.getFeed(symbols)
                       .filter(stockInfo -> stockInfo.ticker.equals("AAPL")||stockInfo.ticker.equals("GOOG"))
                       .map(stockInfo-> computeNAV(stockInfo,numberOfShares))
                       .subscribe(
                        System.out::println,
                       (err-> System.out.println(err + "got error")),
                       () -> System.out.println(" No more data coming. end signal....")
        );
    }

    private static StockInfo computeNAV(StockInfo stockInfo, Map<String,Integer> numberOfShares) {

        return new StockInfo(stockInfo.ticker,stockInfo.value*numberOfShares.get(stockInfo.ticker));
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
