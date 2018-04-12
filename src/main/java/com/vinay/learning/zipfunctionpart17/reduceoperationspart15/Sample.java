package com.vinay.learning.zipfunctionpart17.reduceoperationspart15;

import rx.Observable;
import rx.Subscriber;


public class Sample {

    public static void main(String[] args) {
        Observable<Integer> source = createSource()
                                      .reduce((total ,e ) -> total +e);





        source.subscribe(System.out::println,
                        System.out::println,
                        ()-> System.out.println("DONE"));
        
    }

    private static Observable<Integer> createSource() {
      return  Observable.create(subscriber -> generateData(subscriber));
    }

    private static void generateData(Subscriber<? super Integer> subscriber) {

        int count = 0;
        while (count++ < 10) {
            sleepnow(100);
//            System.out.println("The count is" +count);
            subscriber.onNext(count);


        }
// It is important to have the completed signal to be sent.
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
