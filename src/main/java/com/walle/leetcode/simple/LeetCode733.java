package com.walle.leetcode.simple;

/*
有一幅以二维整数数组表示的图画，每一个整数表示该图画的像素值大小，数值在 0 到 65535 之间。

给你一个坐标 (sr, sc) 表示图像渲染开始的像素值（行 ，列）和一个新的颜色值 newColor，让你重新上色这幅图像。

为了完成上色工作，从初始坐标开始，记录初始坐标的上下左右四个方向上像素值与初始坐标相同的相连像素点，接着再记录这四个方向上符合条件的像素点与他们对应四个方向上像素值与初始坐标相同的相连像素点，……，重复该过程。将所有有记录的像素点的颜色值改为新的颜色值。

最后返回经过上色渲染后的图像。
 */
public class LeetCode733 {
    private int m;
    private int n;
    private int color;
    private int newColor;

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image == null || image.length == 0)
            return image;
        m = image.length;
        n = image[0].length;
        this.color = image[sr][sc];
        this.newColor = newColor;
        dfs(image, sr, sc);
        return image;
    }

    private void dfs(int[][] image, int i, int j) {
        if (!isValid(i, j, image))
            return;
        image[i][j] = newColor;
        dfs(image, i - 1, j);
        dfs(image, i + 1, j);
        dfs(image, i, j + 1);
        dfs(image, i, j - 1);
    }

    private boolean isValid(int i, int j, int[][] image) {
        return i < m && i >= 0 && j < n && j >= 0 && image[i][j] == color && image[i][j] != newColor;
    }

    public static void main(String[] args) {
        int[][] list = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        System.out.println("PRE:");
        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list[0].length; j++) {
                System.out.print(list[i][j] + " ");
            }
            System.out.println();
        }

        LeetCode733 array = new LeetCode733();
        int[][] image = array.floodFill(list, 1, 1, 2);
        System.out.println("AFTER:");
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                System.out.print(image[i][j] + " ");
            }
            System.out.println();
        }

    }
}
