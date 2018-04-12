package com.vinay.learning.zipfunctionpart17.switchingobserablepart9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class YahooFinance {
  public static double getPriceOrig(final String ticker) {
    try {
      final URL url = 
        new URL("http://ichart.finance.yahoo.com/table.csv?s=" + ticker);
      final BufferedReader reader = 
        new BufferedReader(new InputStreamReader(url.openStream()));
      final String data = reader.lines().skip(1).limit(1).findFirst().get();
      final String[] dataItems = data.split(",");
      double price = Double.parseDouble(dataItems[dataItems.length - 1]);      
      return price;
    } catch(Exception ex) {
      throw new RuntimeException(ex);
    }
  }

  public static double getPriceMock(final String ticker) {
    return Math.random() * 2000;
  }
  
  public static double getPrice(final String ticker, boolean useMock) {
    return useMock ? getPriceMock(ticker) : getPriceOrig(ticker);
  }
}
