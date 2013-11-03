package com.test.algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Trees
{
    static interface Node
    {
        int getValue();

        List<Node> getChildren();
    }

    static class TreeNode implements Node
    {
        public int value;
        public List<Node> children = new ArrayList<Node>();

        public TreeNode(int value)
        {
            this.value = value;
        }

        public TreeNode addChild(int value)
        {
            TreeNode child = new TreeNode(value);
            children.add(child);
            return child;
        }

        public int getValue()
        {
            return value;
        }

        public List<Node> getChildren()
        {
            return children;
        }

        @Override
        public int hashCode()
        {
            return Integer.valueOf(value).hashCode();
        }

        @Override
        public boolean equals(Object o)
        {
            if (o == null || !(o instanceof TreeNode))
                return false;
            return Integer.valueOf(value).equals(((TreeNode) o).getValue());
        }

        @Override
        public String toString()
        {
            return "" + value;
        }
    }

    static class BinaryTreeNode implements Node
    {
        public int value;
        BinaryTreeNode left;
        BinaryTreeNode right;

        public BinaryTreeNode(int value)
        {
            this.value = value;
        }

        public BinaryTreeNode setLeft(int value)
        {
            left = new BinaryTreeNode(value);
            return left;
        }

        public BinaryTreeNode setRight(int value)
        {
            right = new BinaryTreeNode(value);
            return right;
        }

        public int getValue()
        {
            return value;
        }

        public List<Node> getChildren()
        {
            List<Node> children = new ArrayList<Trees.Node>();
            if (left != null)
                children.add(left);
            if (right != null)
                children.add(right);
            return children;
        }

        @Override
        public int hashCode()
        {
            return Integer.valueOf(value).hashCode();
        }

        @Override
        public boolean equals(Object o)
        {
            if (o == null || !(o instanceof BinaryTreeNode))
                return false;
            return Integer.valueOf(value).equals(((BinaryTreeNode) o).getValue());
        }

        @Override
        public String toString()
        {
            return "" + value;
        }
    }

    private static Node generateBinaryTree()
    {
        BinaryTreeNode root = new BinaryTreeNode(5);
        BinaryTreeNode node2 = root.setLeft(2);
        BinaryTreeNode node8 = root.setRight(8);
        BinaryTreeNode node4 = node2.setRight(4);
        BinaryTreeNode node6 = node8.setLeft(6);
        BinaryTreeNode node9 = node8.setRight(9);
        BinaryTreeNode node7 = node6.setRight(7);
        return root;
    }

    private static Node generateTree()
    {
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

    private static enum TraverseOrder
    {
        PRE_ORDER, POST_ORDER
    };

    private static void traverseDFS(Node root, TraverseOrder order)
    {
        Set<Node> visitedSet = new HashSet<Trees.Node>();
        Set<Node> exploredSet = new HashSet<Trees.Node>();
        Stack<Node> stack = new Stack<Node>();
        stack.push(root);
        addNode(visitedSet, root, order == TraverseOrder.PRE_ORDER);
        while (!stack.isEmpty())
        {
            Node node = stack.peek();
            boolean anyNodeAdded = false;
            for (Node child : node.getChildren())
                if (!visitedSet.contains(child))
                {
                    addNode(visitedSet, child, order == TraverseOrder.PRE_ORDER);
                    stack.push(child);
                    anyNodeAdded = true;
                    break;
                }
            if (!anyNodeAdded)
            {
                stack.pop();
                addNode(exploredSet, node, order == TraverseOrder.POST_ORDER);
            }
        }
        System.out.println("");
    }

    private static void addNode(Set<Node> set, Node node, boolean print)
    {
        set.add(node);
        if (print)
            System.out.print("" + node + " ");
    }

    public static void main(String[] args)
    {
        Node binaryTree = generateBinaryTree();
        traverseDFS(binaryTree, TraverseOrder.PRE_ORDER);
        traverseDFS(binaryTree, TraverseOrder.POST_ORDER);
        Node tree = generateTree();
        traverseDFS(tree, TraverseOrder.PRE_ORDER);
        traverseDFS(tree, TraverseOrder.POST_ORDER);
    }
}
