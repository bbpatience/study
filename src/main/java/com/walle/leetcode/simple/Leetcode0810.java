package com.walle.leetcode.simple;

/**
 * 颜色填充。编写函数，实现许多图片编辑软件都支持的“颜色填充”功能。给定一个屏幕（以二维数组表示，元素为颜色值）、一个点和一个新的颜色值，将新颜色值填入这个点的周围区域，直到原来的颜色值全都改变。
 * <p>
 * 示例1:
 * <p>
 * 输入：
 * image = [[1,1,1],[1,1,0],[1,0,1]]
 * sr = 1, sc = 1, newColor = 2
 * 输出：[[2,2,2],[2,2,0],[2,0,1]]
 * 解释:
 * 在图像的正中间，(坐标(sr,sc)=(1,1)),
 * 在路径上所有符合条件的像素点的颜色都被更改成2。
 * 注意，右下角的像素没有更改为2，
 * 因为它不是在上下左右四个方向上与初始点相连的像素点。
 */
public class Leetcode0810 {
    private int m;
    private int n;
    private int chosen;

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        m = image.length;
        n = image[0].length;
        chosen = image[sr][sc];
        dfs(sr, sc, image, newColor);
        return image;
    }

    public void dfs(int i, int j, int[][] image, int newColor) {
        if (invalid(i, j, image, newColor))
            return;
        image[i][j] = newColor;
        dfs(i + 1, j, image, newColor);
        dfs(i - 1, j, image, newColor);
        dfs(i, j + 1, image, newColor);
        dfs(i, j - 1, image, newColor);
    }

    public boolean invalid(int x, int y, int[][] image, int newColor) {
        return x < 0 || x >= m || y < 0 || y >= n || image[x][y] != chosen || image[x][y] == newColor;
    }

    public void print(int[][] input) {
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                System.out.print(input[i][j] + ", ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] input = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};

        Leetcode0810 lc = new Leetcode0810();
        int[][] output = lc.floodFill(input, 1, 1, 2);
        lc.print(output);
    }
}
