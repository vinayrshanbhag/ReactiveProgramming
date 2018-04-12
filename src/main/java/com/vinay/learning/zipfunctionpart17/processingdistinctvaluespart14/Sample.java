package com.vinay.learning.zipfunctionpart17.processingdistinctvaluespart14;

import rx.Observable;
import rx.Subscriber;


public class Sample {

    public static void main(String[] args) {
        Observable<Double> source = createSource()
                                     .distinct(value-> (Math.floor(Math.floor(value)/10))*10);
                                    // .distinct();






        source.subscribe(System.out::println,
                         System.out::println,
                        ()-> System.out.println("DONE"));
        
    }

    private static Observable<Double> createSource() {
      return  Observable.create(subscriber -> generateData(subscriber));
    }

    private static void generateData(Subscriber<? super Double> subscriber) {

//        int count = 0;
        while(!subscriber.isUnsubscribed()){
            sleepnow(100);
//            System.out.println("The count is" +count);
            subscriber.onNext(Math.random()*1000);

        }
    }

    private static void sleepnow(int timeinMillis) {
        try {
            Thread.sleep(timeinMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
