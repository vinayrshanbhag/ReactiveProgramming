package com.vinay.learning.zipfunctionpart17.concatenatingObservablespart16;

import rx.Observable;
import rx.Subscriber;

import java.util.Arrays;

public class Sample {

    public static void main(String[] args) {
        Observable<Integer> source = Observable.concat(createSource(1),
                                                        createSource(100));

//        Doesnt work!!! as the types are different.
//        source = Observable.concat(createSource(1),
//                Observable.from(Arrays.asList("a","b","c")));




        source.subscribe(System.out::println,
                System.out::println,
                ()-> System.out.println("DONE"));

    }

    private static Observable<Integer> createSource(int start) {
        return  Observable.create(subscriber -> generateData(subscriber,start));
    }

    private static void generateData(Subscriber<? super Integer> subscriber,int start) {

        int count = start;
        while (count++ < (start +10)) {
            sleepnow(100);
            subscriber.onNext(count);


        }
        subscriber.onCompleted();
    }

    private static void sleepnow(int timeinMillis) {
        try {
            Thread.sleep(timeinMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
