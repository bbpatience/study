package com.walle.leetcode.simple;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 机器人在一个无限大小的网格上行走，从点 (0, 0) 处开始出发，面向北方。该机器人可以接收以下三种类型的命令：
 * <p>
 * -2：向左转 90 度
 * -1：向右转 90 度
 * 1 <= x <= 9：向前移动 x 个单位长度
 * 在网格上有一些格子被视为障碍物。
 * <p>
 * 第 i 个障碍物位于网格点  (obstacles[i][0], obstacles[i][1])
 * <p>
 * 如果机器人试图走到障碍物上方，那么它将停留在障碍物的前一个网格方块上，但仍然可以继续该路线的其余部分。
 * <p>
 * 返回从原点到机器人的最大欧式距离的平方。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入: commands = [4,-1,3], obstacles = []
 * 输出: 25
 * 解释: 机器人将会到达 (3, 4)
 * 示例 2：
 * <p>
 * 输入: commands = [4,-1,4,-2,4], obstacles = [[2,4]]
 * 输出: 65
 * 解释: 机器人在左转走到 (1, 8) 之前将被困在 (1, 4) 处
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= commands.length <= 10000
 * 0 <= obstacles.length <= 10000
 * -30000 <= obstacle[i][0] <= 30000
 * -30000 <= obstacle[i][1] <= 30000
 * 答案保证小于 2 ^ 31
 */
public class Leetcode874 {
    public class Coordinates {
        public int x;
        public int y;

        public Coordinates(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public boolean equals(Object obj) {
            Coordinates cor = (Coordinates) obj;
            return cor.x == this.x && cor.y == this.y;
        }
    }

    public int robotSim(int[] commands, int[][] obstacles) {
        int max = 0;
        Set<Coordinates> set = new HashSet<>();
        for (int i = 0; i < obstacles.length; ++i) {
            set.add(new Coordinates(obstacles[i][0], obstacles[i][1]));
        }
        int direction = 0;
        Coordinates cur = new Coordinates(0, 0);
        for (int i : commands) {
            if (i <= 0) {
                //direction
                direction = direction(direction, i);
            } else {
                // step.
                for (int k = 0; k < i; ++k) {
                    Coordinates next = tryStepOne(cur, direction);
                    //obstacle
                    if (set.contains(next)) {
                        break;
                    }
                    cur = next;
                    max = Math.max(max, cur.x * cur.x + cur.y * cur.y);
                }
            }
        }
        return max;
    }

    private int direction(int now, int rotate) {
        if (rotate == -1) {
            return (4 + now + 1) % 4;
        }
        if (rotate == -2) {
            return (4 + now - 1) % 4;
        }
        return now;
    }

    private Coordinates tryStepOne(Coordinates cor, int direction) {
        switch (direction) {
            case 0:
                return new Coordinates(cor.x, cor.y + 1);
            case 1:
                return new Coordinates(cor.x + 1, cor.y);
            case 2:
                return new Coordinates(cor.x, cor.y - 1);
            case 3:
                return new Coordinates(cor.x - 1, cor.y);
            default:
                return cor;
        }
    }

    public static void main(String[] args) {
        Leetcode874 lc = new Leetcode874();
//        int[] commands = new int[]{4, -1, 4, -2, 4};
//        int[][] obstacles = {{2, 4}};
        int[] commands = new int[]{-2, 8, 3, 7, -1};
        int[][] obstacles = {{-4, -1}, {1, -1}, {1, 4}, {5, 0}, {4, 5}, {-2, -1}, {2, -5}, {5, 1}, {-3, -1}, {5, -3}};
        System.out.println(lc.robotSim(commands, obstacles));
    }
}
