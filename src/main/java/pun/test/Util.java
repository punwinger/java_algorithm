package pun.test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * DO WHAT YOU WANT POLICY
 */
public class Util {

    private static Random random = new Random();

    public static void print(String msg) {
        System.out.println(msg);
    }

    //[0 .. n)
    public static int randomInt(int n) {
        return random.nextInt(n);
    }

    public int[] randomArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; ++i) {
            array[i] = randomInt(100);
        }

        return array;
    }

    public static int[] randomArrayEx(int size, int max) {
        int[] array = new int[size];
        for (int i = 0; i < size; ++i) {
            array[i] = random.nextInt() % (max + 1);
        }

        return array;
    }

    public static int[] sortedArrayEx(int size, int max) {
        return sortedArrayEx(size, max, false);
    }

    public static int[] sortedArrayEx(int size, int max, boolean reserve) {
        int array[] = randomArrayEx(size, max);
        Arrays.sort(array);
        if (reserve) {
            int left = 0, right = array.length - 1;
            int tmp = 0;
            while (left < right) {
                tmp = array[left];
                array[left] = array[right];
                array[right] = tmp;
                left++;
                right--;
            }
        }
        return array;
    }
}
