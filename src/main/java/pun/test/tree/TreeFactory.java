package pun.test.tree;

/**
 * DO WHAT YOU WANT POLICY
 */
public class TreeFactory {

    public static class BTreeNode {
        int value;
        BTreeNode left;
        BTreeNode right;

        public BTreeNode(int v) {
            value = v;
        }
    }

    //普通插入二叉树，不采用任何平衡算法
    public static BTreeNode getBTree(int[] array) {
        BTreeNode root = null;
        BTreeNode iter = null;
        for (int a : array) {
            if (root == null) {
                root = new BTreeNode(array[0]);
                continue;
            }
            iter = root;
            while (true) {
                if (a <= iter.value) {
                    if (iter.left == null) {
                        iter.left = new BTreeNode(a);
                        break;
                    } else {
                        iter = iter.left;
                    }
                } else {
                    if (iter.right == null) {
                        iter.right = new BTreeNode(a);
                        break;
                    } else {
                        iter = iter.right;
                    }
                }
            }
        }

        return root;
    }

}
