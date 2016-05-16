package com.test.problems.someoldtest.findcommonancestor;

public class MyFindCommonAncestorTest
{
    public static void main(String[] args)
    {
        String[] commits = { "G", "F", "E", "D", "C", "B", "A" };
        String[][] parents = { { "F", "D" }, { "E" }, { "B" }, { "C" }, { "B" }, { "A" }, null };
        String commit1 = "G";
        String commit2 = "B";
        MyFindCommonAncestor findAncestor = new MyFindCommonAncestor();
        System.out.println("Result: " + findAncestor.findCommmonAncestor(commits, parents, commit1, commit2));
    }
}
