package com.company;

import java.util.concurrent.ConcurrentLinkedDeque;

public class Calls {
    public static ConcurrentLinkedDeque<Call> calls;
    public static volatile boolean stop = false;

    public Calls() {
        if (calls == null) calls = new ConcurrentLinkedDeque<>();
    }

    public void addCalls() {
        int numberOfCalls = Const.get().getConst("numberOfCalls");
        int timeOfDelay = Const.get().getConst("timeOfDelay");
        try {
            for (int i = 1; i <= numberOfCalls; i++) {
                Thread.currentThread().sleep(timeOfDelay);
                calls.add(new Call(i));
                System.out.println(Thread.currentThread().getName() + " приняла и поставила в очередь " + i + " звонок");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void answerCall() {
        int timeOfAnswer = Const.get().getConst("timeOfAnswer");
        try {
            while (!Thread.currentThread().interrupted() && !this.stop) {
                if (!calls.isEmpty()) {
                    try {
                        Call call = calls.remove();
                        System.out.println(Thread.currentThread().getName() + " обрабатывает " + call.number + " звонок");
                        Thread.currentThread().sleep(timeOfAnswer);
                    } catch (Exception e) {
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
