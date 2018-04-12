package com.vinay.learning.zipfunctionpart17.notinterestedincertainvaluespart13;

import rx.Observable;
import rx.Subscriber;


public class Sample {

    public static void main(String[] args) {
        Observable<Integer> source = createSource()
                                      //.skip(5);
                                      .skipWhile(value-> value<5);





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
