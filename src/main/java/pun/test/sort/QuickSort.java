package pun.test.sort;

import pun.test.Algorithm;
import pun.test.Util;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by yongjianpun on 15/12/3.
 */
public class QuickSort {
    private static Random r = new Random();

    @Algorithm
    public static void run() {
        for (int i = 0; i < 10; i ++) {
            int array[] = Util.randomArrayEx(10 + i, 15 + i);
            Util.print("before : " + Arrays.toString(array));
            partitionSortRange(array, 0, array.length - 1, 2, 4);
            Util.print("after : " + Arrays.toString(array));
            Util.print("\n");
        }
    }

    /**
     * 快速排序算法基础实现
     */
    public static void partitionSort(int nums[], int low, int high) {
        if (low >= high) {
            return;
        }

        int l = low;
        int h = high;
        int p = l + r.nextInt(h - l);
        int temp = (l + h) >>> 1;

        //利用随机数作为pivot，可以避免最坏case的出现
        //与中间进行交换，这是为了在下面遍历中防止l++, h--的时候超出数组范围
        int pivot = nums[p];
        nums[p] = nums[temp];
        nums[temp] = pivot;

        while (l <= h) {
            while (nums[l] < pivot) {
                l++;
            }

            while (nums[h] > pivot) {
                h--;
            }

            if (l <= h) {
                temp = nums[l];
                nums[l++] = nums[h];
                nums[h--] = temp;
            }
        }

        partitionSort(nums, low, h);
        partitionSort(nums, l, high);
    }

    /**
     * 找出排序后第pos个大的元素。
     * 利用快速排序分治的思想对数组进行部分排序
     *
     */
    public static void partitionTopN(int[] nums, int low, int high, int pos) {
        if (low > pos || high < pos || low >= high) {
            return;
        }

        //注意下面选取low位置作为pivot，同时从low+1开始迭代
        //每次遍历保证h位置上的元素完成排序。
        int pivot = nums[low];
        int l = low + 1;
        int h = high;

        while (l <= h) {
            //由于不能保证中间元素一定是pivot，因此要更改成以下判断防止索引超出数组范围
            if (nums[h] < pivot && nums[l] > pivot) {
                int t = nums[l];
                nums[l++] = nums[h];
                nums[h--] = t;
            }

            if (nums[l] <= pivot) {
                l++;
            }

            if (nums[h] >= pivot) {
                h--;
            }
        }

        //最后交换回pivot到h，这样就保证第h个元素完成排序。
        int t = nums[h];
        nums[h] = nums[low];
        nums[low] = t;

        if (h == pos) {
            return;
        }

        partitionTopN(nums, low, h, pos);
        partitionTopN(nums, l, high, pos);
    }

    /**
     * 对[start, end]范围的数组进行部分排序，即把位于该范围大小的元素放在该范围中，但不保证该范围元素有序
     * 利用快速排序的分治算法完成。
     *
     * 一般在数据库的limit和offset限制下，对数组进行该排序后，接着利用Arrays.sort()把该范围的数组进行排序
     *
     * 调用时注意范围start >= 0, end <= nums.length - 1
     *
     */
    public static void partitionSortRange(int nums[], int low, int high, int start, int end) {
        if (low > end || high < start || (low >= start && high <= end)) {
            return;
        }

        //如果把以上注释，采用下面的判断，则可保证[start, end]范围内元素有序
//        if (low > end || high < start) {
//            return;
//        }

        //以下和快速排序逻辑一致
        if (low >= high) {
            return;
        }

        int l = low;
        int h = high;
        int p = l + r.nextInt(h - l);
        int temp = (l + h) >>> 1;

        //利用随机数作为pivot，可以避免最坏case的出现
        //与中间进行交换，这是为了在下面遍历中防止l++, h--的时候超出数组范围
        int pivot = nums[p];
        nums[p] = nums[temp];
        nums[temp] = pivot;

        while (l <= h) {
            while (nums[l] < pivot) {
                l++;
            }

            while (nums[h] > pivot) {
                h--;
            }

            if (l <= h) {
                temp = nums[l];
                nums[l++] = nums[h];
                nums[h--] = temp;
            }
        }

        partitionSortRange(nums, low, h, start, end);
        partitionSortRange(nums, l, high, start, end);
    }
}
