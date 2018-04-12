package com.vinay.learning.zipfunctionpart17;

import rx.Observable;
import rx.Subscriber;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class Sample {

    public static void main(String[] args) throws InterruptedException {
        createSource(1,10)
                .zipWith(createSource(1,10),(data1 ,data2)-> (data1+ "---"+data2))
                .subscribe(System.out::println);

      // Frequency is different, but it doesnt matter
        createSource(1,10)
                .zipWith(createSource(3,10),(data1 ,data2)-> (data1+ "---"+data2))
                .subscribe(System.out::println);

       //Frequency and size is differnt, it will only print as many as the smaller one.
        createSource(1,5)
                .zipWith(createSource(3,10),(data1 ,data2)-> (data1+ "---"+data2))
                .subscribe(System.out::println);

        Thread.sleep(30000);

    }

    private static Observable<Long> createSource(int time , int count) {
       return Observable.interval(time, TimeUnit.SECONDS)
                        .limit(count);

    }



}
