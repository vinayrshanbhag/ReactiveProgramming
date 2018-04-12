package com.vinay.learning.zipfunctionpart17.multiplefallbackspart10;

public class StockInfo {

    public final String ticker;
    public final double value;

    public StockInfo(String theTicker, double theValue) {
        ticker = theTicker;
        value = theValue;
    }

    public static StockInfo fetch(String ticker) {
        //We may run into a network error, or just about any other issue fetching the data.
        //We simulate that here with a random exception.

        if(Math.random() > 0.9) throw new RuntimeException("oops, ran into trouble");

        return new StockInfo(ticker, YahooFinance.getPrice(ticker, true));
    }

    @Override
    public String toString() {
        return String.format("%s : %f", ticker, value);
    }
}