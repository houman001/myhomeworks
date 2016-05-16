package com.test.problems.someoldtest.flatten;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyFlattenTree<T> implements FlattenTree<T>
{
    public List<T> flattenInOrder(Tree<T> tree)
    {
        if (tree == null)
            throw new IllegalArgumentException("Tree cannot be null.");
        if (tree.get().isLeft())
            return Arrays.asList(tree.get().ifLeft(myLeftFunction));
        else
            return tree.get().ifRight(myRightFunction);
    }

    private final Function<T, T> myLeftFunction = new Function<T, T>()
    {
        @Override
        public T apply(T p)
        {
            return p;
        }
    };

    private final Function<Triple<Tree<T>>, List<T>> myRightFunction = new Function<Triple<Tree<T>>, List<T>>()
    {
        @Override
        public List<T> apply(Triple<Tree<T>> p)
        {
            List<T> all = new ArrayList<T>();
            all.addAll(flattenInOrder(p.left()));
            all.addAll(flattenInOrder(p.middle()));
            all.addAll(flattenInOrder(p.right()));
            return all;
        }
    };
}
