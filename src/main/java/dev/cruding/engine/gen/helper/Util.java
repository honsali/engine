package dev.cruding.engine.gen.helper;

public class Util {
    public static int findThirdCapitalIndex(String word) {
        int j = 0;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (Character.isUpperCase(c) && ++j == 3) {
                return i;
            }

        }
        return -1;
    }
}
