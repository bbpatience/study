package com.walle.linklist;

/**
 * @author: bbpatience
 * @date: 2019/7/22
 * @description: ZConvert
 **/
public class ZConvert {
    public static String convert(String s, int numRows) {
        String result = "";
        if (numRows == 1) {
            return s;
        }
        String[] array = new String[numRows];
        for (int k=0; k<numRows;k++) {
            array[k] = "";
        }
        int i = 0;
        int column = 0;
        boolean down = true;
        while (i < s.length()) {
            array[column] += s.charAt(i);
            if (down) {
                column++;
            } else {
                column--;
            }
            if (column >= numRows) {
                down = false;
                column -= 2;
            }
            else if (column < 0) {
                down = true;
                column += 2;
            }
            i++;
        }
        for (int j=0; j<numRows;j++) {
            result += array[j];
        }
        return result;

    }

    public static void main(String[] args) {
//        String test = "LEETCODEISHIRING";
//        int count = 3;
//        System.out.println("pre:" + test);
//        System.out.println("after:" + convert(test, count));
        System.out.println(" " + isPalindrome(12321));
    }

    public static int reverse(int x) {
        int ans = 0;
        while (x != 0) {
            int pop = x % 10;
            ans = ans * 10 + pop;
            x /= 10;
        }
        return ans;
    }

    public static boolean isPalindrome(int x) {
        //边界判断
        if (x < 0) return false;
        int div = 1;
        //
        while (x / div >= 10) {
            div *= 10;
        }
        while (x > 0) {
            int left = x / div;
            int right = x % 10;
            if (left != right) return false;
            x = (x % div) / 10;
            div /= 100;
        }
        return true;
    }

}
