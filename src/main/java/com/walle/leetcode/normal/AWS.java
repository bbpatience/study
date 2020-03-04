package com.walle.leetcode.normal;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AWS {

    public List<Integer> search(int[] entryTime, int[] leaveTime) {
        //calculate
        int max = 0;
        int idx = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < entryTime.length; ++i) {
            for (int j = entryTime[i]; j <= leaveTime[i]; j++) {
                map.put(j, map.getOrDefault(j, 0) + 1);
                if (map.get(j) > max) {
                    max = map.get(j);
                    idx = j;
                }
            }
        }
        // output
//
//        for (int i : map.keySet()) {
//            if (map.get(i) > max) {
//                max = map.get(i);
//                idx = i;
//            }
//        }
        return Arrays.asList(max, idx);
    }

    public static void main(String[] args) {
        int[] entryTime = {1, 2, 10, 5, 5};
        int[] leaveTime = {4, 5, 12, 9, 12};
        AWS aws = new AWS();
        aws.search(entryTime, leaveTime).forEach(item -> System.out.println(item));
    }
}
