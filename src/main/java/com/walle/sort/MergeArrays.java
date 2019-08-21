package com.walle.sort;

/**
 * @author: bbpatience
 * @date: 2019/8/8
 * @description: MergeArrays
 **/
public class MergeArrays {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (nums1[m - 1] <= nums2[0]) {
            for (int i = 0; i<n;i++) {
                nums1[m+i] = nums2[i];
            }
            return;
        }
        for (int i = 0; i < n; i++) {
            insert(nums1, m + i, nums2[i]);
        }
    }

    public void insert(int[] nums1, int m, int value) {
        int idx = findIdx(nums1, m , value);
        if (idx >= m) {
            nums1[idx] = value;
        } else {
            for (int k=m; k > idx;k--) {
                nums1[k] = nums1[k-1];
            }
            nums1[idx] = value;
        }
    }

    public int findIdx(int[] nums1, int m, int value) {
        for (int i = 0; i<m;i++) {
            if (nums1[i] > value) {
                return i;
            }
        }
        return m;
    }

    public static void main(String[] args) {
        int[] nums1 = {1,2,3,0,0,0};
        int m = 3;
        int[] nums2 = {2,5,6};
        int n = 3;
        MergeArrays arrays = new MergeArrays();
        arrays.merge(nums1, 3,nums2, 3);
        for(int i=0; i< nums1.length;i++) {
            System.out.print(nums1[i] + " ");
        }
    }
}
