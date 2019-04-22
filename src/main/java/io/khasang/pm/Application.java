package io.khasang.pm;

import java.util.concurrent.TimeUnit;

public class Application {
    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Hello from " + Thread.currentThread().getName());
        }).start();
        System.out.println("Hello!");
    }
}
