package com.test.problems.someoldtest.findcommonancestor;

import java.util.HashSet;
import java.util.Set;

public class MyFindCommonAncestor implements FindCommonAncestor
{
    public String findCommmonAncestor(String[] commitHashes, String[][] parentHashes, String commitHash1,
            String commitHash2)
    {
        boolean[] matchingIndexesCommit1 = flagMatchingIndexes(commitHash1, commitHashes, parentHashes);
        boolean[] matchingIndexesCommit2 = flagMatchingIndexes(commitHash2, commitHashes, parentHashes);
        for (int i = 0; i < commitHashes.length; i++)
            if (matchingIndexesCommit1[i] && matchingIndexesCommit2[i])
                return commitHashes[i];
        return null;
    }

    private void addToCommitPath(Set<String> commitPathSet, int startingIndex, String commitHash,
            String[] commitHashes, String[][] parentHashes)
    {
        int index = startingIndex;
        for (; index < commitHashes.length; index++)
            if (commitHashes[index].equals(commitHash))
            {
                commitPathSet.add(commitHashes[index]);
                break;
            }
        if (parentHashes[index] != null)
            for (String parent : parentHashes[index])
                if (!commitPathSet.contains(parent))
                    addToCommitPath(commitPathSet, index, parent, commitHashes, parentHashes);
    }

    private boolean[] flagMatchingIndexes(String commitHash, String[] commitHashes, String[][] parentHashes)
    {
        Set<String> commitPathSet = new HashSet<String>();
        addToCommitPath(commitPathSet, 0, commitHash, commitHashes, parentHashes);
        boolean[] matchingIndexesCommit1 = new boolean[commitHashes.length];
        for (int i = 0; i < commitHashes.length; i++)
            matchingIndexesCommit1[i] = commitPathSet.contains(commitHashes[i]);
        return matchingIndexesCommit1;
    }
}
