package com.walle.leetcode;

public class Sqrt {
    public int mySqrt(int x) {
        if (x == 0 || x == 1)
            return x;
        int i = 1, j = x;
        while (i <= j) {
            int mid = i + (j - i) / 2;
           if (mid > x / mid) {
                j = mid - 1;
            } else {
                if ((mid + 1) > x / (mid + 1)) {
                    return mid;
                }
                i = mid + 1;
            }
        }
        return 0;
    }

    public double mySqrt2(int x) {
        if (x == 0 || x == 1)
            return x;
        double i = 1, j = x;

        while (Math.abs(j - i) > 0.0001) {
            double mid = i + (j - i) / 2;
            if (mid > x / mid) {
                j = mid;
            } else {
                i = mid;
            }
        }
        return i;
    }

    public static void main(String[] args) {
        Sqrt s = new Sqrt();
        System.out.println(s.mySqrt2(5));
    }
}
