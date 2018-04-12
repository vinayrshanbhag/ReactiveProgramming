package com.vinay.learning.threadingpart20;

import rx.Observable;
import rx.Subscriber;


// All the three components. subscriber, observable and notification send it on
//same thread.
public class Sample {

    public static void sleep(int ms)  {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
    }

    public static void emit(Subscriber<? super Integer> subscriber){

        int count = 0;
        while(count<1000){
            subscriber.onNext(count);
            System.out.println("Observable" + Thread.currentThread());
            sleep(1000);
            count++;
        }


    }


    public static void main(String[] args) {
        Observable<Integer> observable = Observable.create(subscriber -> emit(subscriber))
                                                    .map((data-> transform((Integer)data)));

        observable.subscribe(data-> printData(data));
    }

    private static Integer transform(Integer data) {
        System.out.println("transform " + Thread.currentThread());

        return data *2;
    }

    private static void printData(Integer data) {

        System.out.println(data + ":"+Thread.currentThread());
    }
}
