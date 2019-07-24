package com.walle.linklist;

/**
 * @author: bbpatience
 * @date: 2019/7/22
 * @description: StringReverse
 **/
public class StringReverse {

    public static String reverse(String str) {
        if (str.equals("")) {
            return str;
        } else {
            String substr = str.substring(1);
            String subSolution = reverse(substr);
            return subSolution + str.charAt(0);
        }
    }
    public static void main(String[] args) {
        String test = "abcdef";
        System.out.println(reverse(test));
    }
}
