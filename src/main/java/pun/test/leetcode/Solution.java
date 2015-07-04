package pun.test.leetcode;

import java.util.HashSet;
import java.util.List;

/**
 * Created by yongjianpun on 15/6/30.
 */
public class Solution {

    /////////////////////////////////////////////////////////////////////////////////////
    //Contains Duplicate
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < nums.length; ++i) {
            if (set.contains(nums[i])) {
                return true;
            } else {
                set.add(nums[i]);
            }
        }

        return false;
    }


    /////////////////////////////////////////////////////////////////////////////////////
    // Triangle
    // Given a triangle, find the minimum path sum from top to bottom.
    // Each step you may move to adjacent numbers on the row below.
    // For example, given the following triangle
    //    [
    //            [2],
    //            [3,4],
    //            [6,5,7],
    //            [4,1,8,3]
    //     ]
    // The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

    // Version1
    // from top to bottom
    public int minimumTotal(List<List<Integer>> triangle) {
        //last row size
        int[] lastRowTotal = new int[triangle.get(triangle.size() - 1).size()];
        int tmp = 0;
        int i1, i2;

        for (int i = 0; i < triangle.size(); ++i) {
            List<Integer> array = triangle.get(i);
            for (int j = 0; j < array.size(); ++j) {
                if (j == 0) {
                    tmp = lastRowTotal[0];
                    lastRowTotal[0] = tmp + array.get(0);
                } else if (j == array.size() - 1) {
                    lastRowTotal[j] = tmp + array.get(j);
                } else {
                    i1 = tmp + array.get(j);
                    i2 = lastRowTotal[j] + array.get(j);
                    tmp = lastRowTotal[j];
                    lastRowTotal[j] = i1 > i2 ? i2 : i1;
                }
            }
        }

        return findSmallest(lastRowTotal);
    }

    private int findSmallest(int[] array) {
        int res = array[0];
        for (int i = 1; i < array.length; ++i) {
            int v = array[i];
            if (v < res) {
                res = v;
            }
        }
        return res;
    }

    // Version2. form bottom to top. Not need to create an array.
    // What funny is that Version 2(332 ms) is slower than Version 1(276 ms).
    // Maybe create Integer object spending most of the time.
    public int minimumTotal2(List<List<Integer>> triangle) {
        int i1, i2;
        for (int i = triangle.size() - 2; i >= 0; --i) {
            List<Integer> uparray = triangle.get(i);
            List<Integer> downarray = triangle.get(i + 1);
            for (int j = 0; j < uparray.size(); j++) {
                i1 = downarray.get(j) + uparray.get(j);
                i2 = downarray.get(j + 1) + uparray.get(j);
                uparray.set(j, i1 > i2 ? i2 : i1);
            }
        }

        return triangle.get(0).get(0);
    }

    // Version3. from bottom to top. Create an array to avoid create Integer object.
    // the fastest 264 ms!
    public int minimumTotal3(List<List<Integer>> triangle) {
        int i1, i2;
        int[] arr = new int[triangle.get(triangle.size() - 1).size() + 1];

        for (int i = triangle.size() - 1; i >= 0; --i) {
            List<Integer> array = triangle.get(i);
            for (int j = 0; j < array.size(); j++) {
                i1 = arr[j] + array.get(j);
                i2 = arr[j + 1] + array.get(j);
                arr[j] = i1 > i2 ? i2: i1;
            }
        }

        return arr[0];
    }


    /////////////////////////////////////////////////////////////////////////////////////
    // Sum Root to Leaf Numbers
//    Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
//
//    An example is the root-to-leaf path 1->2->3 which represents the number 123.
//
//    Find the total sum of all root-to-leaf numbers.
//
//    For example,
//
//             1
//            / \
//           2   3
//    The root-to-leaf path 1->2 represents the number 12.
//    The root-to-leaf path 1->3 represents the number 13.
//
//    Return the sum = 12 + 13 = 25.

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int sumNumbers(TreeNode root) {
        TreeNode n = null;



        //return
    }
}
