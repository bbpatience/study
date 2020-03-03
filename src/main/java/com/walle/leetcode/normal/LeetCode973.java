package com.walle.leetcode.normal;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/*
我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。

（这里，平面上两点之间的距离是欧几里德距离。）

你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。
 */
public class LeetCode973 {

    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<List<Integer>> queue = new PriorityQueue<>((n1, n2) -> getSq(n2) - getSq(n1));

        for (int i = 0; i < points.length; i++) {
            if (queue.size() < K) {
                queue.offer(Arrays.asList(points[i][0], points[i][1]));
            } else {
                int val = points[i][0] * points[i][0] + points[i][1] * points[i][1];
                int now = getSq(queue.peek());
                if (val < now) {
                    queue.poll();
                    queue.offer(Arrays.asList(points[i][0], points[i][1]));
                }
            }
        }
        int[][] resultList = new int[K][2];
        int i = 0;
        while (!queue.isEmpty()) {
            List<Integer> list = queue.poll();
            resultList[i][0] = list.get(0);
            resultList[i][1] = list.get(1);
            i++;
        }
        return resultList;
    }

    private int getSq(List<Integer> list) {
        return list.get(0) * list.get(0) + list.get(1) * list.get(1);
    }

    public static void main(String[] args) {
        LeetCode973 lc = new LeetCode973();
        int[][] nums = {{3, 3}, {5, -1}, {-2, 4}};
        int[][] list = lc.kClosest(nums, 2);

        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list[0].length; j++) {
                System.out.print(list[i][j] + " ");
            }
            System.out.println();
        }
    }
}
