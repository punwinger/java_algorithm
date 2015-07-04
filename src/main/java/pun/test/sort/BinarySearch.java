package pun.test.sort;

import pun.test.Algorithm;
import pun.test.Util;

import java.io.*;
import java.util.Arrays;

/**
 * DO WHAT YOU WANT POLICY
 */
public class BinarySearch {

    //在含有正负数的排序数组中查找绝对值最小的数
    @Algorithm
    public static void sortArraySearch() {
        for (int i = 0; i < 10; i ++) {
            int array[] = Util.sortedArrayEx(10 + i, 15 + i);
            int min = findMinAbs(array);
            int min2 = findMinAbs2(array);
            if (min != min2) {
                throw new RuntimeException("Algorithm is wrong! min: "
                        + min + " min2: " + min2);
            }
            Util.print("array : " + Arrays.toString(array) + "  min abs: " + min);
        }
    }

    private static int findMinAbs2(int array[]) {
        int zero = 0;
        int leftx = 0, rightx = array.length - 1;
        int midx = 0;

        while (leftx <= rightx) {
            midx = ((rightx - leftx) >>> 1) + leftx;
            if (array[midx] == zero) {
                break;
            } else if (array[midx] > zero) {
                rightx = midx - 1;
            } else {
                leftx = midx + 1;
            }
        }

        //find 0 in array
        if (leftx <= rightx) {
            return array[midx];
        }

        //array[leftx - 1] < 0 < array[leftx],  0 <= leftx <= array.length
        if (leftx == array.length) {
            return array[array.length - 1];
        } else if (leftx == 0) {
            return array[0];
        } else {
            int leftv = Math.abs(array[leftx - 1]);
            int rightv = Math.abs(array[leftx]);
            return leftv > rightv ? rightv : leftv;
        }

    }

    //同样符号 方向不变，不同符号，方向改变
    private static int findMinAbs(int array[]) {
        int midx = array.length / 2;
        int mid = array[midx];
        boolean toLeft = true;
        int leftx = 0, rightx = array.length - 1;

        if (0 == mid) {
            return 0;
        }

        if (mid < 0) {
            toLeft = false;
        }

        do {
            if (toLeft) {
                rightx = midx;
            } else {
                leftx = midx;
            }

            if (rightx == leftx + 1) {
                int lx = Math.abs(array[leftx]);
                int rx = Math.abs(array[rightx]);
                return lx > rx ? rx : lx;
            }

            mid = array[midx];
            midx = (rightx - leftx) / 2 + leftx;
            if (array[midx] ==0) {
                return 0;
            }

            if ((mid < 0 && array[midx] >0) ||
                    (mid >0 && array[midx] <0)) {
                toLeft = !toLeft;
            }

        } while (true);

    }

}
