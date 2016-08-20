package com.test.algorithms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

public class Trees {
    public interface Node<T> {
        T getValue();

        Collection<Node<T>> getChildren();
    }

    public interface NodeVisitor<T> {
        void visit(Node<T> node);
    }

    public static class TreeNode<T> implements Node<T> {
        T value;
        List<Node<T>> children = new ArrayList<>();

        public TreeNode(T value) {
            this.value = value;
        }

        TreeNode<T> addChild(T value) {
            TreeNode<T> child = new TreeNode<>(value);
            return addChild(child);
        }

        public TreeNode<T> addChild(TreeNode<T> child) {
            children.add(child);
            return child;
        }

        public T getValue() {
            return value;
        }

        public List<Node<T>> getChildren() {
            return children;
        }

        @Override
        public int hashCode() {
            return value.hashCode();
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || !(o instanceof TreeNode)) {
                return false;
            }
            return value.equals(((TreeNode) o).getValue());
        }

        @Override
        public String toString() {
            return "" + value;
        }
    }

    static class BinaryTreeNode<T> implements Node<T> {
        public T value;
        BinaryTreeNode<T> left;
        BinaryTreeNode<T> right;

        public BinaryTreeNode(T value) {
            this.value = value;
        }

        public BinaryTreeNode<T> setLeft(T value) {
            left = new BinaryTreeNode<>(value);
            return left;
        }

        public BinaryTreeNode<T> setRight(T value) {
            right = new BinaryTreeNode<>(value);
            return right;
        }

        public T getValue() {
            return value;
        }

        public List<Node<T>> getChildren() {
            List<Node<T>> children = new ArrayList<>();
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
            return value.hashCode();
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || !(o instanceof BinaryTreeNode)) {
                return false;
            }
            return value.equals(((BinaryTreeNode) o).getValue());
        }

        @Override
        public String toString() {
            return "" + value;
        }
    }

    private static Node<Integer> generateBinaryTree() {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(10);
        BinaryTreeNode<Integer> node5 = root.setLeft(5);
        BinaryTreeNode<Integer> node14 = root.setRight(14);
        BinaryTreeNode<Integer> node2 = node5.setLeft(2);
        BinaryTreeNode<Integer> node8 = node5.setRight(8);
        BinaryTreeNode<Integer> node11 = node14.setLeft(11);
        BinaryTreeNode<Integer> node16 = node14.setRight(16);
        BinaryTreeNode<Integer> node4 = node2.setRight(4);
        BinaryTreeNode<Integer> node6 = node8.setLeft(6);
        BinaryTreeNode<Integer> node9 = node8.setRight(9);
        BinaryTreeNode<Integer> node15 = node16.setLeft(15);
        BinaryTreeNode<Integer> node19 = node16.setRight(19);
        BinaryTreeNode<Integer> node7 = node6.setRight(7);
        return root;
    }

    private static Node<Integer> generateTree() {
        TreeNode<Integer> root = new TreeNode<>(5);
        TreeNode<Integer> node7 = root.addChild(7);
        TreeNode<Integer> node4 = root.addChild(4);
        TreeNode<Integer> node3 = root.addChild(3);
        TreeNode<Integer> node8 = node7.addChild(8);
        TreeNode<Integer> node2 = node4.addChild(2);
        TreeNode<Integer> node1 = node4.addChild(1);
        TreeNode<Integer> node9 = node1.addChild(9);
        TreeNode<Integer> node6 = node3.addChild(6);
        return root;
    }

    private static enum TraverseOrder {
        PRE_ORDER, POST_ORDER
    }

    public static <T> void traverseDFS(Node<T> root, TraverseOrder order) {
        System.out.print("DFS(" + order + "): ");
        Set<Node<T>> visitedSet = new HashSet<>();
        Set<Node<T>> exploredSet = new HashSet<>();
        Stack<Node<T>> stack = new Stack<>();
        stack.push(root);
        addNode(visitedSet, root, order == TraverseOrder.PRE_ORDER);
        while (!stack.isEmpty()) {
            Node<T> node = stack.peek();
            boolean anyNodeAdded = false;
            for (Node<T> child : node.getChildren()) {
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

    public static <T> void traverseBFS(Node<T> root, NodeVisitor<T> visitor) {
        System.out.println("BFS");
        Queue<Node<T>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<T> node = queue.poll();
            for (Node<T> child : node.getChildren()) {
                queue.offer(child);
            }
            visitor.visit(node);
        }
    }

    public static <T> T findLowestCommonAncestor(Node<T> root, T val1, T val2) {
        // System.out.println("Finding LCA of " + val1 + " and " + val2);
        Set<Node<T>> visitedSet = new HashSet<>();
        Stack<Node<T>> stack = new Stack<>();
        List<Node<T>> path1 = Collections.singletonList(root);
        List<Node<T>> path2 = Collections.singletonList(root);
        stack.push(root);
        while (!stack.isEmpty()) {
            Node<T> node = stack.peek();
            if (visitedSet.contains(node)) {
                // We have seen this node before. It's time to process it and get it out of the stack.
                stack.pop();
            } else {
                visitedSet.add(node);
                for (Node<T> child : node.getChildren()) {
                    stack.push(child);
                    if (child.getValue().equals(val1)) {
                        path1 = stack.stream().filter(visitedSet::contains).collect(Collectors.toList());
                        path1.add(child);
                    } else if (child.getValue().equals(val2)) {
                        path2 = stack.stream().filter(visitedSet::contains).collect(Collectors.toList());
                        path2.add(child);
                    }
                }
            }
        }
        // System.out.println("Path to " + val1 + ": " + path1.toString());
        // System.out.println("Path to " + val2 + ": " + path2.toString());
        T lca = root.getValue();
        for (int i = 0; i < Math.min(path1.size(), path2.size()); i++) {
            if (!path1.get(i).equals(path2.get(i))) {
                break;
            }
            lca = path1.get(i).getValue();
        }
        System.out.println("LCA of " + val1 + " and " + val2 + " is: " + lca);
        return lca;
    }

    private static <T> void addNode(Set<Node<T>> set, Node<T> node, boolean print) {
        set.add(node);
        if (print) {
            System.out.print("" + node + " ");
        }
    }

    private static NodeVisitor<Integer> PRINT_NODE_VISITOR = node -> System.out.print(node + " ");

    public static void main(String[] args) {
        Node<Integer> binaryTree = generateBinaryTree();
        traverseDFS(binaryTree, TraverseOrder.PRE_ORDER);
        traverseDFS(binaryTree, TraverseOrder.POST_ORDER);
        traverseBFS(binaryTree, PRINT_NODE_VISITOR);
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
        traverseBFS(tree, PRINT_NODE_VISITOR);
    }
}
