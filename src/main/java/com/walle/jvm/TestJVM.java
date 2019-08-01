package com.walle.jvm;

/**
 * @author: bbpatience
 * @date: 2019/8/1
 * @description: TestJVM
 **/
public class TestJVM {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
//        testAllocation1();
//        testAllocation2();
//        testAllocation3();
        testAllocation4();
    }

    /**
     *   新内存分配在Eden， Eden放不下触发Minor GC.   Survivor 放不下，  分配担保到 老年代。
     *  -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC
     */
    public static void testAllocation1() {
        byte[] allocation1, allocation2, allocation3 , allocation4;

        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[4 * _1MB];
    }

    /**
     * 大对象直接进入老年代
     * -verbose:gc -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC -XX:PretenureSizeThreshold=3145728
     */
    public static void testAllocation2() {
        byte[] allocation;

        allocation = new byte[4 * _1MB];
    }

    /**
     * 长期存活对象进入老年代
     * -verbose:gc -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC  -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution
     */
    public static void testAllocation3() {
        byte[] allocation1, allocation2, allocation3;

        allocation1 = new byte[_1MB / 4];
        allocation2 = new byte[4 * _1MB];
        allocation3 = new byte[4 * _1MB];
        allocation3 = null;
        allocation3 = new byte[4 * _1MB];
    }

    /**
     * 相同年龄对象占Survivor一半， 年龄大于等于的直接进老年代
     * -verbose:gc -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC  -XX:MaxTenuringThreshold=15 -XX:+PrintTenuringDistribution
     */
    public static void testAllocation4() {
        byte[] allocation1, allocation2, allocation3, allocation4;

        allocation1 = new byte[_1MB / 4];
        allocation2 = new byte[_1MB / 4];
        allocation3 = new byte[4 * _1MB];
        allocation4 = new byte[4 * _1MB];
        allocation4 = null;
        allocation4 = new byte[4 * _1MB];
    }
}
