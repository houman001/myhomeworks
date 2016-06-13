package com.test.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

public class Trees {
    interface Node {
        int getValue();

        List<Node> getChildren();
    }

    static class TreeNode implements Node {
        public int value;
        public List<Node> children = new ArrayList<Node>();

        public TreeNode(int value) {
            this.value = value;
        }

        public TreeNode addChild(int value) {
            TreeNode child = new TreeNode(value);
            children.add(child);
            return child;
        }

        public int getValue() {
            return value;
        }

        public List<Node> getChildren() {
            return children;
        }

        @Override
        public int hashCode() {
            return Integer.valueOf(value).hashCode();
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || !(o instanceof TreeNode)) {
                return false;
            }
            return Integer.valueOf(value).equals(((TreeNode) o).getValue());
        }

        @Override
        public String toString() {
            return "" + value;
        }
    }

    static class BinaryTreeNode implements Node {
        public int value;
        BinaryTreeNode left;
        BinaryTreeNode right;

        public BinaryTreeNode(int value) {
            this.value = value;
        }

        public BinaryTreeNode setLeft(int value) {
            left = new BinaryTreeNode(value);
            return left;
        }

        public BinaryTreeNode setRight(int value) {
            right = new BinaryTreeNode(value);
            return right;
        }

        public int getValue() {
            return value;
        }

        public List<Node> getChildren() {
            List<Node> children = new ArrayList<Node>();
            if (left != null) {
                children.add(left);
            }
            if (right != null) {
                children.add(right);
            }
            return children;
        }

        @Override
        public int hashCode() {
            return Integer.valueOf(value).hashCode();
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || !(o instanceof BinaryTreeNode)) {
                return false;
            }
            return Integer.valueOf(value).equals(((BinaryTreeNode) o).getValue());
        }

        @Override
        public String toString() {
            return "" + value;
        }
    }

    private static Node generateBinaryTree() {
        BinaryTreeNode root = new BinaryTreeNode(10);
        BinaryTreeNode node5 = root.setLeft(5);
        BinaryTreeNode node14 = root.setRight(14);
        BinaryTreeNode node2 = node5.setLeft(2);
        BinaryTreeNode node8 = node5.setRight(8);
        BinaryTreeNode node11 = node14.setLeft(11);
        BinaryTreeNode node16 = node14.setRight(16);
        BinaryTreeNode node4 = node2.setRight(4);
        BinaryTreeNode node6 = node8.setLeft(6);
        BinaryTreeNode node9 = node8.setRight(9);
        BinaryTreeNode node15 = node16.setLeft(15);
        BinaryTreeNode node19 = node16.setRight(19);
        BinaryTreeNode node7 = node6.setRight(7);
        return root;
    }

    private static Node generateTree() {
        TreeNode root = new TreeNode(5);
        TreeNode node7 = root.addChild(7);
        TreeNode node4 = root.addChild(4);
        TreeNode node3 = root.addChild(3);
        TreeNode node8 = node7.addChild(8);
        TreeNode node2 = node4.addChild(2);
        TreeNode node1 = node4.addChild(1);
        TreeNode node9 = node1.addChild(9);
        TreeNode node6 = node3.addChild(6);
        return root;
    }

    private static enum TraverseOrder {
        PRE_ORDER, POST_ORDER
    }

    private static void traverseDFS(Node root, TraverseOrder order) {
        System.out.print("DFS(" + order + "): ");
        Set<Node> visitedSet = new HashSet<>();
        Set<Node> exploredSet = new HashSet<>();
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        addNode(visitedSet, root, order == TraverseOrder.PRE_ORDER);
        while (!stack.isEmpty()) {
            Node node = stack.peek();
            boolean anyNodeAdded = false;
            for (Node child : node.getChildren()) {
                if (!visitedSet.contains(child)) {
                    addNode(visitedSet, child, order == TraverseOrder.PRE_ORDER);
                    stack.push(child);
                    anyNodeAdded = true;
                    break;
                }
            }
            if (!anyNodeAdded) {
                stack.pop();
                addNode(exploredSet, node, order == TraverseOrder.POST_ORDER);
            }
        }
        System.out.println("");
    }

    private static void traverseBFS(Node root) {
        System.out.print("BFS: ");
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (Node child : node.getChildren()) {
                queue.offer(child);
            }
            System.out.print("" + node + " ");
        }
        System.out.println("");
    }

    private static int findLowestCommonAncestor(Node root, int val1, int val2) {
        // System.out.println("Finding LCA of " + val1 + " and " + val2);
        Set<Node> visitedSet = new HashSet<>();
        Stack<Node> stack = new Stack<>();
        List<Node> path1 = Arrays.asList(root);
        List<Node> path2 = Arrays.asList(root);
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.peek();
            if (visitedSet.contains(node)) {
                // We have seen this node before. It's time to process it and get it out of the stack.
                stack.pop();
            } else {
                visitedSet.add(node);
                for (Node child : node.getChildren()) {
                    stack.push(child);
                    if (child.getValue() == val1) {
                        path1 = stack.stream().filter(visitedSet::contains).collect(Collectors.toList());
                    } else if (child.getValue() == val2) {
                        path2 = stack.stream().filter(visitedSet::contains).collect(Collectors.toList());
                    }
                }
            }
        }
        // System.out.println("Path to " + val1 + ": " + path1.toString());
        // System.out.println("Path to " + val2 + ": " + path2.toString());
        int lca = root.getValue();
        for (int i = 0; i < Math.min(path1.size(), path2.size()); i++) {
            if (!path1.get(i).equals(path2.get(i)))
                break;
            lca = path1.get(i).getValue();
        }
        System.out.println("LCA of " + val1 + " and " + val2 + " is: " + lca);
        return lca;
    }

    private static void addNode(Set<Node> set, Node node, boolean print) {
        set.add(node);
        if (print) {
            System.out.print("" + node + " ");
        }
    }

    public static void main(String[] args) {
        Node binaryTree = generateBinaryTree();
        traverseDFS(binaryTree, TraverseOrder.PRE_ORDER);
        traverseDFS(binaryTree, TraverseOrder.POST_ORDER);
        traverseBFS(binaryTree);
        if (findLowestCommonAncestor(binaryTree, 10, 6) != 10) {
            System.err.println("Invalid lowest common ancestor for 10 and 6.");
        }
        if (findLowestCommonAncestor(binaryTree, 2, 6) != 5) {
            System.err.println("Invalid lowest common ancestor for 2 and 6.");
        }
        if (findLowestCommonAncestor(binaryTree, 6, 11) != 10) {
            System.err.println("Invalid lowest common ancestor for 6 and 11.");
        }
        if (findLowestCommonAncestor(binaryTree, 15, 19) != 16) {
            System.err.println("Invalid lowest common ancestor for 15 and 19.");
        }
        Node tree = generateTree();
        traverseDFS(tree, TraverseOrder.PRE_ORDER);
        traverseDFS(tree, TraverseOrder.POST_ORDER);
        traverseBFS(tree);
    }
}
