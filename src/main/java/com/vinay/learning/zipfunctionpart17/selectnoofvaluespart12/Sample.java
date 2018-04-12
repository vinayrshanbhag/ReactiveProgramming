package com.vinay.learning.zipfunctionpart17.selectnoofvaluespart12;

import rx.Observable;
import rx.Subscriber;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;


public class Sample {

    public static void main(String[] args) {
        Observable<Integer> source = createSource();
                                                //.take(10);
                                               // .takeWhile(value-->value<7);
                                                 // .filter(value -> value<7)
                                                 // .takeUntil(value  -> value==6);
                                                    //.takeFirst(value-> value <7)
                                                   // .take(2, TimeUnit.SECONDS);




        source.subscribe(System.out::println,
                        System.out::println,
                        ()-> System.out.println("DONE"));
        
    }

    private static Observable<Integer> createSource() {
      return  Observable.create(subscriber -> generateData(subscriber));
    }

    private static void generateData(Subscriber<? super Integer> subscriber) {

        int count = 0;
        while(!subscriber.isUnsubscribed()){
            sleepnow(100);
//            System.out.println("The count is" +count);
            subscriber.onNext(count);
            count++;
            if(count >9)
                count =0;
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
