package pun.test.list;

import pun.test.Util;

import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

/**
 * DO WHAT YOU WANT POLICY
 */
public class ListFactory {

    public static class Node {
        int value = Util.randomInt(100);
        Node next;
    }

    //创建单向链表
    public static Node singlyLinkedList(int size) {
        Node root = new Node();
        Node tmp = root;

        for (int i = size - 1; i > 0; --i) {
            tmp.next = new Node();
            tmp = tmp.next;
        }

        return root;
    }

    public static String listToString(Node node) {
        StringBuilder sb = new StringBuilder("List [ ");
        while (node != null) {
            sb.append(node.value).append(" ");
            node = node.next;
        }

        sb.append("]");
        return sb.toString();
    }

}
