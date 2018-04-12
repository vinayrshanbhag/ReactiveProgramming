package com.vinay.learning.hotobservablepart19;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

public class Sample {

    public static void processData(Subscriber<? super Integer> subscriber)  {
        System.out.println("starting subscription");
        int count = 0;

        while(count<100){
            subscriber.onNext(count);
            count++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Observable<Object> observable = Observable.create(subscriber -> processData(subscriber))
                                                   .share();


//Observable chaining..

        /*observable.filter(data-> data%2==0)
                  .subscribe(data-> System.out.println("S1--"+ data));
*/
        Thread.sleep(1000);

        observable.subscribeOn(Schedulers.computation())
                   .subscribe(data-> System.out.println("S1--" + data));

        Thread.sleep(5000);

        observable.subscribeOn(Schedulers.computation())
                .subscribe(data-> System.out.println("S2--" + data));

        Thread.sleep(50000);



    }
}
