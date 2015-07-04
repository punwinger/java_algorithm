package pun.test.list;

import pun.test.Algorithm;
import pun.test.Util;
import pun.test.list.ListFactory.Node;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * DO WHAT YOU WANT POLICY
 */
public class ReserveList {

    //运用两种算法反转单向列表
    @Algorithm
    private static void statckReserveList() {
        Node root = ListFactory.singlyLinkedList(10);
        Util.print("before statckReserveList: " + ListFactory.listToString(root));

        //use LinkedList as Stack as <<Thinking in Java>> suggest
        LinkedList<Node> stack = new LinkedList<Node>();
        Node iter = root;
        while (iter != null) {
            stack.push(iter);
            iter = iter.next;
        }

        while (!stack.isEmpty()) {
            if (iter != null) {
                iter.next = stack.pop();
                iter = iter.next;
            } else {
                iter = stack.pop();
                root = iter;
            }
        }

        iter.next = null;

        Util.print("after statckReserveList: " + ListFactory.listToString(root));
    }

    @Algorithm
    private static void plainReserveList() {
        Node root = ListFactory.singlyLinkedList(10);
        Util.print("before plainReserveList: " + ListFactory.listToString(root));

        Node newRoot = root;
        Node iter = null, tmp = null;

        if (root != null) {
            iter = root.next;
            newRoot.next = null;
        }

        while (iter != null) {
            tmp = iter;
            iter = iter.next;
            tmp.next = newRoot;
            newRoot = tmp;
        }
        root = newRoot;

        Util.print("after plainReserveList: " + ListFactory.listToString(root));
    }
}
