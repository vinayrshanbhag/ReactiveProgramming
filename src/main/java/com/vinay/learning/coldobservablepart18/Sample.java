package com.vinay.learning.coldobservablepart18;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;
// Default is cold observable, it creates different sessions
// for different subscription
public class Sample {


    public static void main(String[] args) throws InterruptedException {
        Observable<Object> observable = Observable.create(subscriber -> process(subscriber));

         observable.subscribeOn(Schedulers.computation())
                   .subscribe(data->System.out.println("s1-- "+ data));

         Thread.sleep(5000);

        System.out.println("Subsribing another time ");
        observable.subscribeOn(Schedulers.computation())
                   .subscribe(data-> System.out.println("s2--"+ data ));

        Thread.sleep(50000);
     }

    private static void process(Subscriber<? super Integer> subscriber) {
        System.out.println("Received subscriber");
        int count = 0;
        while(count<100){

            subscriber.onNext(count);
            sleepForSomeTime(1000);
            count++;
        }

        subscriber.onCompleted();
    }

    private static void sleepForSomeTime(int timeinMillis) {
        try {
            Thread.sleep(timeinMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
