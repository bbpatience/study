package com.walle.linklist;

/**
 * @author: bbpatience
 * @date: 2019/7/22
 * @description: StringReverse
 **/
public class StringReverse {

    public static String reverse(String str) {
        int i = 0, j = str.length() - 1;
        char[] array = new char[str.length()];
        while (i <= j) {
            char chI = str.charAt(i);
            char chJ = str.charAt(j);
            if (!isValid(chI)) {
                array[i++] = chI;
            } else if (!isValid(chJ)) {
                array[j--] = chJ;
            } else {
                //reverse
                array[j--] = chI;
                array[i++] = chJ;
            }
        }
        return new String(array);
    }

    public static boolean isValid(char ch) {
        return ((ch <= 'Z' && ch >= 'A') || (ch >= 'a' && ch <= 'z'));
    }

    public static String process(String parag) {
        String[] array = parag.split(" ");
        String[] array2 = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            array2[i] = reverse(array[i]);
        }
        return String.join(" ", array2);
    }

    public static void main(String[] args) {
        String test = "I am Bing Bai.";
        System.out.println(process(test));
    }
}
