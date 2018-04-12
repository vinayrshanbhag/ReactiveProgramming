package com.vinay.learning.schedulerspart22;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Sample {

    public static void emit(Subscriber<? super Integer> subscriber){
        int count = 0;

        while(count<10){
            System.out.println("Observable " + count + ":" + Thread.currentThread());
            subscriber.onNext(count++);
        }

        sleepForSomeTime(1000);
    }

    private static void sleepForSomeTime(int i) {

        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newFixedThreadPool(100);

        Observable<Object> observable = Observable.create(subscriber -> emit(subscriber))
//                                                  .observeOn(Schedulers.newThread());
//                                                    .observeOn(Schedulers.trampoline());
//                                                     .observeOn(Schedulers.from(executorService));
//                                                       .observeOn(Schedulers.computation());
                                                         .observeOn(Schedulers.io());

        observable.subscribe(data-> printData(data));

        //executorService.shutdown();

        sleepForSomeTime(200000);

    }

    private static void printData(Object data) {
        System.out.println(data + ":" +Thread.currentThread());
        sleepForSomeTime(3000);
    }
}
