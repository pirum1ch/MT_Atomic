package org.example;

public class Checks {

    public boolean polindrome(String word) {
        String newString = new StringBuffer().append(word).reverse().toString();
        return newString.equalsIgnoreCase(word);
    }

    public boolean inline(String word) {
        for (int i = 0; i < word.length() - 1; i++) {
            if (word.charAt(i) != word.charAt(i + 1))
                return false;
        }
        return true;
    }

    public boolean increase(String word) {
        for (int i = 0; i < word.length() - 1; i++) {
            if (word.charAt(i) != word.charAt(i + 1))
                return false;
        }
        return true;
    }
}
