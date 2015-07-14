package pun.test.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
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

	public class Context {
		int sum;
	}

	
	// version1 - recurse 
	// 284ms
    public int sumNumbers(TreeNode root) {
        if (root == null) {
			return 0;
		}
		
		Context c = new Context();
		recurseWalk(root, c, 0);
		
		return c.sum;
    }
	
	private void recurseWalk(TreeNode n, Context c, int pval) {
		if (n.left == null && n.right == null) {
			c.sum += pval + n.val;
			return;
		}
		
		pval = (n.val + pval) * 10;
		if (n.left != null) {
			recurseWalk(n.left, c, pval);
		}
		
		if (n.right != null) {
			recurseWalk(n.right, c, pval);
		}
	}
	
	// version2 - iterate
	// 300ms
	 public int sumNumbers2(TreeNode root) {
        if (root == null) {
			return 0;
		}
		
		// iterate
		LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
		stack.push(root);
		
		int sum = 0;
		while (!stack.isEmpty()) {
			TreeNode n = stack.pop();
			
			if (n.left == null && n.right == null) {
				sum += n.val;
				continue;
			}
			
			if (n.left != null) {
				n.left.val += n.val * 10;
				stack.push(n.left);
			}
			
			if (n.right != null) {
				n.right.val += n.val * 10;
				stack.push(n.right);
			}
		}
		
		return sum;
    }

    /////////////////////////////////////////////////////////////////////////////////////
    // TODO Divide and conquer Binary Search
    //There are two sorted arrays nums1 and nums2 of size m and n respectively.
    // Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

    //616ms
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int index = (nums1.length + nums2.length) >>> 1;
        boolean needAvg = ((nums1.length + nums2.length) & 0x01) == 0;

        int r1 = 0, r2 = 0, i1 = 0, i2 = 0, n1 = 0, n2 = 0;

        if (nums1.length > 0) {
            n1 = nums1[i1++];
        } else {
            n1 = Integer.MAX_VALUE;
        }

        if (nums2.length > 0) {
            n2 = nums2[i2++];
        } else {
            n2 = Integer.MAX_VALUE;
        }

        // combine two sorted array
        for (int i = 0; i <= index; ++i) {
            if (n1 > n2) {
                r1 = n2;
                if (i2 < nums2.length) {
                    n2 = nums2[i2++];
                } else {
                    n2 = Integer.MAX_VALUE;
                }
            } else {
                r1 = n1;
                if (i1 < nums1.length) {
                    n1 = nums1[i1++];
                } else {
                    n1 = Integer.MAX_VALUE;
                }
            }

            if (needAvg && i == index - 1) {
                r2 = r1;
            }
        }

        return  needAvg ? ((double)(r1 + r2)) / 2 : r1;
    }


    /////////////////////////////////////////////////////////////////////////////////////
    // Gas Station
    // Time Limit Exceeded...
    public int canCompleteCircuit(int[] gas, int[] cost) {
        for(int i = 0; i < gas.length; ++i) {
            int gasCount = 0;
            int pos = i;
            int j = 0;
            for (; j < cost.length; ++j) {
                pos = (i + j) % cost.length;
                gasCount += gas[pos] - cost[pos];
                if (gasCount < 0)
                    break;
            }

            if (j == cost.length) {
                return i;
            }
        }


        return -1;
    }

    //time exceed again...
    public static int canCompleteCircuit2(int[] gas, int[] cost) {
        int[] tmp = new int[gas.length];
        boolean hasPositive = false;
        boolean hasNegative = false;

        for (int i = 0; i < gas.length; ++i) {
            tmp[i] = gas[i] - cost[i];
            if (tmp[i] > 0) {
                hasPositive = true;
            } else if (tmp[i] < 0) {
                hasNegative = true;
            }
        }

        if (!hasPositive || !hasNegative) {
            return -1;
        }


        for(int i = 0; i < gas.length; ++i) {
            int gasCount = 0;
            int pos = i;
            int j = 0;
            if (tmp[i] <= 0) continue;

            for (; j < gas.length; ++j) {
                pos = (i + j) % cost.length;
                gasCount += tmp[pos];
                if (gasCount < 0)
                    break;
            }
            if (j == cost.length) {
                return i;
            }
        }


        return -1;
    }

    public static int canCompleteCircuit3(int[] gas, int[] cost) {
        int len = gas.length;
        int tmp[] = new int[len];

        int last = 0;
        for (int i = len - 1; i >= last ; i++) {
            tmp[i] = gas[i] - cost[i];

            if (tmp[i] >= 0) {
                int j = 0;
                int pos = i + 1;
                while (pos < ) {

                }

            }


        }


    }


}
