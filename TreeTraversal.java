package com.sai.graphalgo;


class Node {
    int key;
    Node left, right;

    Node(int key) {
        this.key = key;
        left  =right  = null;
    }
}
class  Tree {
    Node root;
    Tree() {
        root= null;
    }
    void setRoot(Node node) {
        root = node;
    }
}

class Traversal {
    public void postOrder(Node node ){
        if (node == null)  return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.key + " ");
    }
    public void inOrder(Node node ){
        if (node == null)  return;
        inOrder(node.left);
        System.out.print(node.key + " ");
        inOrder(node.right);

    }

    public void preOrder(Node node ){
        if (node == null)  return;
        System.out.print(node.key + " ");
        preOrder(node.left);
        preOrder(node.right);

    }

}
public class TreeTraversal {


    public static void main(String[] args) {
        System.out.println("Hello World");
        Tree tree = new Tree();
        tree.setRoot(new Node(1));
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);

        Traversal traversal = new Traversal();
        System.out.println("PostOrder ");
        traversal.postOrder(tree.root);
        System.out.println("");
        System.out.println("PreOrder ");
        traversal.preOrder(tree.root);
        System.out.println("");
        System.out.println("InOrder ");
        traversal.inOrder(tree.root);

    }
}

