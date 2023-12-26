package org.example;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

public class TextGenerator implements Callable {
    int length;


    TextGenerator(int length) {
        this.length = length;
    }

    public synchronized String[] textGenerate() {
        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }
        return texts;
    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

    @Override
    public String[] call() throws Exception {
        return textGenerate();
    }
}
