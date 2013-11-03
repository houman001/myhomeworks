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

        public Node addChild(int value)
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

    private static void traverseDFS(Node root)
    {
        Set<Node> exploredSet = new HashSet<Trees.Node>();
        Stack<Node> stack = new Stack<Node>();
        stack.push(root);
        while (!stack.isEmpty())
        {
            Node node = stack.peek();
            for (Node child : node.getChildren())
                if (!exploredSet.contains(child))
                {
                    stack.push(child);
                    break;
                }
            if (node.equals(stack.peek()))
            {
                stack.pop();
                System.out.println("" + node.getValue());
                exploredSet.add(node);
            }
        }
    }

    public static void main(String[] args)
    {
        Node root = generateBinaryTree();
        traverseDFS(root);
    }
}
