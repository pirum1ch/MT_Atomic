package org.example;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    static AtomicInteger three = new AtomicInteger();
    static AtomicInteger four = new AtomicInteger();
    static AtomicInteger five = new AtomicInteger();
    static final int[] data = {3, 4, 5};

    static Future<String[]> texts;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Checks checks = new Checks();

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        for (int i = 0; i < data.length - 1; i++) {
            TextGenerator textGenerator = new TextGenerator(data[i]);
            texts = executorService.submit(textGenerator);
        }
        executorService.shutdown();

        String[] textsNew = texts.get();

        //Проверка полиндрома
        Thread polindrome = new Thread(() -> {
            for (String word : textsNew) {
                if (checks.polindrome(word))
                    counter(word.length());
            }
        });
        polindrome.start();

        // Проверка что состоит из одной и той же буквы
        Thread increase = new Thread(() -> {
            for (String word : textsNew) {
                if (checks.polindrome(word) && checks.increase(word))
                    counter(word.length());
            }
        });
        increase.start();

        // Проверка что буквы идут по возрастанию
        Thread inline = new Thread(() -> {
            for (String word : textsNew) {
                if (checks.increase(word) && checks.inline(word))
                    counter(word.length());
            }
        });
        inline.start();

        polindrome.join();
        inline.join();
        increase.join();

        print();
    }

    public static void counter(int x) {
        if (x == 3) {
            three.incrementAndGet();
        } else if (x == 4) {
            four.incrementAndGet();
        } else if (x == 5) {
            five.incrementAndGet();
        }
    }

    public static void print() {
        System.out.printf("Красивых слов с длиной 3: %d шт %n" +
                        "Красивых слов с длиной 4: %d шт%n" +
                        "Красивых слов с длиной 5: %d шт",
                three.get(), four.get(), five.get());
    }
}