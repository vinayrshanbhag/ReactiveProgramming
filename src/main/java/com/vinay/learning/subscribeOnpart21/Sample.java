package com.vinay.learning.subscribeOnpart21;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;


// All the three components. subscriber, observable and notification send it on
//same thread.
public class Sample {

    public static void sleep(int ms)  {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
        }
    }

    public static void emit(Subscriber<? super Integer> subscriber){

        int count = 0;
        while(count<1000){
            subscriber.onNext(count);
            System.out.println("Observable " + Thread.currentThread());
           sleep(10000);
            count++;
        }


    }


    public static void main(String[] args) {
        Observable<Integer> observable = Observable.create(subscriber -> emit(subscriber))
                                                    .map((data-> transform((Integer)data)))
                                                    .subscribeOn(Schedulers.computation());

// subscribe on makes sure, we get a separate thread for different threads.
        observable.subscribe(data-> printData(data));

        sleep(1000000);
    }

    private static Integer transform(Integer data) {
        System.out.println("transform " + Thread.currentThread());

        return data *2;
    }

    private static void printData(Integer data) {

        System.out.println(data + ":"+Thread.currentThread());
    }
}
