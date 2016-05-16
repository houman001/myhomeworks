package com.test.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class BinarySearchTree {
    private Node root = null;

    boolean isEmpty() {
        return root == null;
    }

    Node find(int value) {
        Node currentNode = root;
        while (currentNode != null) {
            if (currentNode.value == value) {
                return currentNode;
            } else if (value < currentNode.value) {
                currentNode = currentNode.left;
            } else {
                currentNode = currentNode.right;
            }
        }
        return null;
    }

    void insert(int value) {
        Node newNode = new Node(value);
        Node parent = root;
        Node currentNode = root;
        while (currentNode != null) {
            parent = currentNode;
            if (value < currentNode.value) {
                currentNode = currentNode.left;
            } else {
                currentNode = currentNode.right;
            }
        }
        newNode.parent = parent;
        if (parent == null) {
            root = newNode;
        } else if (value < parent.value) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
    }

    void replace(Node existingNode, Node newNode) {
        if (existingNode.parent == null) {
            root = newNode;
        } else if (existingNode == existingNode.parent.left) {
            existingNode.parent.left = newNode;
        } else if (existingNode == existingNode.parent.right) {
            existingNode.parent.right = newNode;
        }
        if (newNode != null) {
            newNode.parent = existingNode.parent;
        }
    }

    Node getMinimum(final Node startingNode) {
        Node x = startingNode;
        while (x.left != null) {
            x = x.left;
        }
        return x;
    }

    Node getMaximum(final Node startingNode) {
        Node x = startingNode;
        while (x.right != null) {
            x = x.right;
        }
        return x;
    }

    Node getSuccessor(final Node startingNode) {
        if (startingNode.right != null) {
            return getMinimum(startingNode.right);
        }
        Node x = startingNode;
        Node p = x.parent;
        while (p != null && x == p.right) {
            x = p;
            p = p.parent;
        }
        return p;
    }

    void remove(Node node) {
        if (node.left == null) {
            replace(node, node.right);
        } else if (node.right == null) {
            replace(node, node.left);
        } else {
            Node temp = getMinimum(node.right);
            if (temp.parent != node) {
                replace(temp, temp.right);
                temp.right = node.right;
                temp.right.parent = temp;
            }
            replace(node, temp);
            temp.left = node.left;
            temp.parent = node.parent;
        }
    }

    private List<Integer> traverseInorder() {
        List<Integer> result = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        Node p = root;
        while (!stack.isEmpty() || p != null) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                Node topNode = stack.pop();
                result.add(topNode.value);
                p = topNode.right;
            }
        }
        return result;
    }

    static final class Node {
        private int value;
        private Node parent;
        private Node left;
        private Node right;

        Node(int value) {
            this.value = value;
            parent = null;
            left = null;
            right = null;
        }
    }

    private static void verifyAndPrintTree(BinarySearchTree tree) {
        List<Integer> orderedNodes = tree.traverseInorder();
        System.out.println("Ordered Nodes: " + orderedNodes);
        for (int i = 0; i < orderedNodes.size() - 1; i++) {
            if (orderedNodes.get(i) > orderedNodes.get(i + 1)) {
                System.err.println("List is not ordered!");
            }
        }
    }

    public static void main(String[] args) {
        int treeSize = 20;
        BinarySearchTree tree = new BinarySearchTree();
        List<Integer> values = new ArrayList<>();
        for (int i = 0; i < treeSize; i++) {
            int value = new Random().nextInt(99);
            values.add(value);
        }
        System.out.println("Nodes: " + values);
        for (int nodeValue : values) {
            tree.insert(nodeValue);
        }
        verifyAndPrintTree(tree);
        int index = new Random().nextInt(treeSize);
        int numberToDelete = values.get(index);
        System.out.println("Deleting: " + numberToDelete);
        Node nodeToDelete = tree.find(numberToDelete);
        if (nodeToDelete == null) {
            System.err.println("Couldn't find value: " + numberToDelete);
        }
        tree.remove(nodeToDelete);
        verifyAndPrintTree(tree);
    }
}
