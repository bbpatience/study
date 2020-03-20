package com.walle.leetcode;

public class BinarySearch {

    public static int search(int[] a, int value) {
        return search(a, 0, a.length - 1, value);
    }

    /**
     * normal search.
     *
     * @param a
     * @param start
     * @param end
     * @param value
     * @return
     */
    private static int search(int[] a, int start, int end, int value) {
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (a[mid] == value) {
                return mid;
            } else if (a[mid] > value) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }

    /**
     * recursive search.
     *
     * @param a
     * @param start
     * @param end
     * @param value
     * @return
     */
    private static int search2(int[] a, int start, int end, int value) {
        if (start > end)
            return -1;
        int mid = start + (end - start) / 2;
        if (a[mid] == value) {
            return mid;
        } else if (a[mid] > value) {
            return search2(a, start, mid - 1, value);
        } else {
            return search2(a, mid + 1, end, value);
        }
    }

    public static int searchFirstMatch(int[] a, int value) {
        return searchFirstMatch(a, 0, a.length - 1, value);
    }

    /**
     * normal search.
     *
     * @param a
     * @param start
     * @param end
     * @param value
     * @return
     */
    private static int searchFirstMatch(int[] a, int start, int end, int value) {
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (a[mid] > value) {
                end = mid - 1;
            } else if (a[mid] < value) {
                start = mid + 1;
            } else {
                if (mid == 0 || a[mid - 1] != value)
                    return mid;
                end = mid - 1;
            }
        }
        return -1;
    }

    public static int searchLastMatch(int[] a, int value) {
        return searchLastMatch(a, 0, a.length - 1, value);
    }

    /**
     * normal search.
     *
     * @param a
     * @param start
     * @param end
     * @param value
     * @return
     */
    private static int searchLastMatch(int[] a, int start, int end, int value) {
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (a[mid] > value) {
                end = mid - 1;
            } else if (a[mid] < value) {
                start = mid + 1;
            } else {
                if (mid == end || a[mid + 1] != value)
                    return mid;
                start = mid + 1;
            }
        }
        return -1;
    }

    public static int searchMoreThanOrEqual(int[] a, int value) {
        return searchMoreThanOrEqual(a, 0, a.length - 1, value);
    }

    /**
     * normal search.
     *
     * @param a
     * @param start
     * @param end
     * @param value
     * @return
     */
    private static int searchMoreThanOrEqual(int[] a, int start, int end, int value) {
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (a[mid] >= value) {
                if (mid == 0 || a[mid - 1] < value)
                    return mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }

    public static int searchLessThanOrEqual(int[] a, int value) {
        return searchLessThanOrEqual(a, 0, a.length - 1, value);
    }

    /**
     * normal search.
     *
     * @param a
     * @param start
     * @param end
     * @param value
     * @return
     */
    private static int searchLessThanOrEqual(int[] a, int start, int end, int value) {
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (a[mid] > value) {
                end = mid - 1;
            } else {
                if (mid == end || a[mid + 1] > value)
                    return mid;
                start = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int idx;
        // normal case.
//        int[] array = new int[]{1, 23, 56, 89, 128, 256, 496, 1024, 4096, 12345};
//        if ((idx = search(array, 88)) > 0) {
//            System.out.println("find, index:" + idx);
//        } else {
//            System.out.println("not found");
//        }

        // duplicated case.
        int[] array = new int[]{1, 23, 56, 89, 128, 256, 256, 256, 256, 496, 1024, 4096, 12345};
//        if ((idx = searchFirstMatch(array, 256)) > 0) {
//            System.out.println("find first match, index:" + idx);
//        } else {
//            System.out.println("not found");
//        }

//        if ((idx = searchLastMatch(array, 256)) > 0) {
//            System.out.println("find last match, index:" + idx);
//        } else {
//            System.out.println("not found");
//        }

//        if ((idx = searchMoreThanOrEqual(array, 257)) > 0) {
//            System.out.println("find more than or equal , index:" + idx + " value=" + array[idx]);
//        } else {
//            System.out.println("not found");
//        }

        if ((idx = searchLessThanOrEqual(array, 130)) > 0) {
            System.out.println("find less than or equal, index:" + idx + " value=" + array[idx]);
        } else {
            System.out.println("not found");
        }
    }
}
