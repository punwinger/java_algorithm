package pun.test.tree;

import pun.test.Algorithm;
import pun.test.Util;

import java.util.Arrays;

import static pun.test.tree.TreeFactory.BTreeNode;

/**
 * DO WHAT YOU WANT POLICY
 */
public class BTreeOperation {

    @Algorithm
    public static void testFindXthMin() {
        for(int i = 0; i < 10000; ++i) {
            int size = Util.randomInt(100) + 1;
            int max = Util.randomInt( i > 500 ? i : 500);
            int x = Util.randomInt(size) + 1;
            int array[] = null;

            if (i <= 3) {
                //sort
                array = Util.sortedArrayEx(size, max);
            } else if (i <= 5) {
                //reserve order sort
                array = Util.sortedArrayEx(size, max, true);
            } else {
                //random
                array = Util.randomArrayEx(size, max);
            }

            BTreeNode root = TreeFactory.getBTree(array);
            int min = findXthMin(root, x);
            Arrays.sort(array);
            if (min != array[x - 1]) {
                throw new RuntimeException("findXthMin error, min: " + min +
                        " xth min: " + array[x - 1] + " array:" + Arrays.toString(array));
            }
        }
    }

    //find the Xth min element in B Tree. x >= 1
    static int findXthMin(BTreeNode root, int x) {
        FindContext context = new FindContext();
        context.count = x;
        context = recurseFind(root, context);
        if (context.count != 0) {
            throw new RuntimeException("can not find the " + x +
                    " min element. Tree element is " + (x - context.count));
        }

        return context.element;
    }

    private static class FindContext {
        int element;
        int count;
    }

    private static FindContext recurseFind(BTreeNode node, FindContext context) {
        if (node == null || context.count == 0) {
            return context;
        }

        context = recurseFind(node.left, context);
        if (context.count == 0) {
            return context;
        }

        if (--context.count == 0) {
            context.element = node.value;
            return context;
        } else {
            return recurseFind(node.right, context);
        }
    }


}
