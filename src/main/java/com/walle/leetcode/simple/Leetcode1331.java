package com.walle.leetcode.simple;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个整数数组 arr ，请你将数组中的每个元素替换为它们排序后的序号。
 * <p>
 * 序号代表了一个元素有多大。序号编号的规则如下：
 * <p>
 * 序号从 1 开始编号。
 * 一个元素越大，那么序号越大。如果两个元素相等，那么它们的序号相同。
 * 每个数字的序号都应该尽可能地小。
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [40,10,20,30]
 * 输出：[4,1,2,3]
 * 解释：40 是最大的元素。 10 是最小的元素。 20 是第二小的数字。 30 是第三小的数字。
 * 示例 2：
 * <p>
 * 输入：arr = [100,100,100]
 * 输出：[1,1,1]
 * 解释：所有元素有相同的序号。
 */
public class Leetcode1331 {

    // timeout
    public int[] arrayRankTransform(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>(arr.length);
        for (int i : arr) {
            if (!map.containsKey(i)) {
                map.put(i, 0);
                for (int key : map.keySet()) {
                    if (key > i) {
                        map.put(key, map.get(key) + 1);
                    } else {
                        map.put(i, map.get(i) + 1);
                    }
                }

            }
        }
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; ++i) {
            result[i] = map.get(arr[i]);
        }
        return result;
    }

    public int[] arrayRankTransform2(int[] arr) {
        int[] sortArray = new int[arr.length];
        System.arraycopy(arr, 0, sortArray, 0, arr.length);
        Arrays.sort(sortArray);
        Map<Integer, Integer> map = new HashMap<>(arr.length);
        int count = 0;
        for (int i = 0; i < sortArray.length; ++i) {
            if (!map.containsKey(sortArray[i]))
                map.put(sortArray[i], ++count);
        }
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; ++i) {
            result[i] = map.get(arr[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] input = new int[]{37, 12, 28, 9, 100, 56, 80, 5, 12};
        Leetcode1331 lc = new Leetcode1331();
        for (int i : lc.arrayRankTransform2(input)) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
