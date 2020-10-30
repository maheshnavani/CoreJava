package com.sai.graphalgo;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class SerDeTree {
/*
    public static String serialize(TreeNode root) {
    }
    public static TreeNode deserialize(String data) {
    }
    "1,2,3,null,null,4,5,6,7");
*/

    public static String serializePreOrder(TreeNode root) {
        if ( root == null)
            return "#";
        return root.data + "," + serializePreOrder(root.left) + "," + serializePreOrder(root.right);
    }

    public static String serializeInOrder(TreeNode root) {
        if ( root == null)
            return "#";
        return  serializeInOrder(root.left) + "," + root.data + "," + serializePreOrder(root.right);
    }

    public static TreeNode deserializePreOrder(String tree) {
        String[] nodes = tree.split(",");
        int[] count = new int[1];
        return buildTreePreOrder(nodes,count);
    }

    public static TreeNode buildTreePreOrder(String[] nodes, int[] count) {
        if (count[0] < nodes.length) {
            String node = nodes[count[0]++];
            if ( !node.equals("#")) {
                TreeNode treeNode = new TreeNode(Integer.valueOf(node));
                treeNode.left  = buildTreePreOrder(nodes,count);
                treeNode.right = buildTreePreOrder(nodes,count);
                return treeNode;
            }
        }
        return null;
    }

    public static TreeNode deserializeInOrder(String tree) {
        String[] nodes = tree.split(",");
        int[] count = new int[1];
        return buildTreeInOrder(nodes,count);
    }

    public static TreeNode buildTreeInOrder(String[] nodes, int[] count) {
        if (count[0] < nodes.length) {
            String node = nodes[count[0]++];
            if ( !node.equals("#")) {
                TreeNode treeNode = new TreeNode(Integer.valueOf(node));
                treeNode.left  = buildTreePreOrder(nodes,count);
                treeNode.right = buildTreePreOrder(nodes,count);
                return treeNode;
            }
        }
        return null;
    }


    public static void main(String[] args) {
        System.out.println("Hello World");
        TreeNode root = deserializePreOrder("1,2,3,#,#,4,5,6,7");
        String serTree = serializePreOrder(root);
        System.out.println("Serialize=" + serTree);



    }
}
