package org.example;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author lilong
 * @create 2020-03-06
 */

/**
 * 深度优先遍历 *  *
 */
public class DeepFirstSort {
    public static void main(String[] args) {
        MyTreeNode head = new MyTreeNode(1);
        MyTreeNode second = new MyTreeNode(2);
        MyTreeNode three = new MyTreeNode(3);
        MyTreeNode four = new MyTreeNode(4);
        MyTreeNode five = new MyTreeNode(5);
        MyTreeNode six = new MyTreeNode(6);
        MyTreeNode seven = new MyTreeNode(7);
        head.rightNode = three;
        head.leftNode = second;
        second.rightNode = five;
        second.leftNode = four;
        three.rightNode = seven;
        three.leftNode = six;
        System.out.print("广度优先遍历结果：");
        new DeepFirstSort().BroadFirstSearch(head);
        System.out.println();
        System.out.print("深度优先遍历结果：");
        new DeepFirstSort().depthFirstSearch(head);
    }        //广度优先遍历是使用队列实现的

    public void BroadFirstSearch(MyTreeNode nodeHead) {
        if (nodeHead == null) {
            return;
        }
        Queue<MyTreeNode> myQueue = new LinkedList<>();
        myQueue.add(nodeHead);
        while (!myQueue.isEmpty()) {
            MyTreeNode node = myQueue.poll();
            System.out.print(node.data + " ");
            if (null != node.leftNode) {
                myQueue.add(node.leftNode);
            }
            if (null != node.rightNode) {
                myQueue.add(node.rightNode);
            }
        }
    }


    public void depthFirstSearch(MyTreeNode nodeHead) {
        if (nodeHead == null) {
            return;
        }
        Stack<MyTreeNode> myStack = new Stack<>();
        myStack.add(nodeHead);
        while (!myStack.isEmpty()) {
            MyTreeNode node = myStack.pop();    //弹出栈顶元素
            System.out.print(node.data + " ");
            if (node.rightNode != null) {
                myStack.push(node.rightNode);    //深度优先遍历，先遍历左边，后遍历右边,栈先进后出
            }
            if (node.leftNode != null) {
                myStack.push(node.leftNode);
            }
        }
    }
}

class MyTreeNode {
    int data;
    MyTreeNode leftNode;
    MyTreeNode rightNode;

    public MyTreeNode() {
    }

    public MyTreeNode(int d) {
        data = d;
    }

    public MyTreeNode(MyTreeNode left, MyTreeNode right, int d) {
        leftNode = left;
        rightNode = right;
        data = d;
    }
}

