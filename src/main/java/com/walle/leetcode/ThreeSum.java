package com.walle.leetcode;

import java.util.*;

public class ThreeSum {
    public static List<List<Integer>> threeSum(int[] nums) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i) {
            map.put(nums[i], i);
        }

        Set<List<Integer>> resultSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            int sum = 0 - nums[i];
            List<Integer> rsp = findSum(sum, i, nums, map);
            if (rsp.size() > 0) {
                rsp.sort(new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o1 - o2;
                    }
                });
                resultSet.add(rsp);
            }
        }
        return new ArrayList<>(resultSet);
    }

    private static List<Integer> findSum(int sum, int exceptIndex, int[] nums, Map<Integer, Integer> map) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (exceptIndex != i && map.containsKey(sum - nums[i]) && map.get(sum - nums[i]) != i && map.get(sum - nums[i]) != exceptIndex) {
                result.add(nums[exceptIndex]);
                result.add(nums[i]);
                result.add(sum - nums[i]);
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6};
        List<List<Integer>> result = threeSum(nums);
        for (List<Integer> value : result) {
            value.forEach((item) -> System.out.print(item + " "));
            System.out.println();
        }
    }
}
