package com.company;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        final int numberOfOperators = 5;
        Const.get().setConst("numberOfCalls", 60);
        Const.get().setConst("timeOfDelay", 1_000);
        Const.get().setConst("timeOfAnswer", 3_000);


        Calls cc = new Calls();

        Runnable operator = cc::answerCall;
        Runnable station = cc::addCalls;

        Thread thread[] = new Thread[numberOfOperators+1];
        thread[0] = new Thread(null, station, "АТС");

        for (int i=1; i<=numberOfOperators; i++) {
            thread[i] =  new Thread(null, operator, "Оператор "+i);
        }

        for (int i=0; i<=numberOfOperators; i++) {
            thread[i].start();
        }

        thread[0].join();

        while (!cc.calls.isEmpty()) {}
        cc.stop = true;

    }
}

