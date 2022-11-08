package com.example.juc;

import sun.misc.VM;
import sun.misc.VMSupport;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ComputerFuture {
    public static void main(String[] args) throws InterruptedException {
        LongAdder longAdder = new LongAdder();
        CountDownLatch countDownLatch = new CountDownLatch(100);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                for (int j = 0; j < 501000; j++) {
                    longAdder.increment();
                }
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        long end = System.currentTimeMillis();
        System.out.println(end-start);
        System.out.println(longAdder.sum()  );


    }
}
