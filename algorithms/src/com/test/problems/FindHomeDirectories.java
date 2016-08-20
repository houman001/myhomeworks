package com.test.problems;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.test.algorithms.Trees;

public class FindHomeDirectories {
    private static class Directory {
        final int id;
        final int parentId;
        final Collection<String> users;

        public Directory(int id, int parentId, Collection<String> users) {
            this.id = id;
            this.parentId = parentId;
            this.users = users;
        }

        @Override
        public String toString() {
            return "Directory{" +
                   "id=" + id +
                   ", parentId=" + parentId +
                   ", users=" + users +
                   '}';
        }
    }

    private Collection<Directory> parseFolders(String[] folders) {
        return IntStream.range(0, folders.length).mapToObj(i -> {
            String s = folders[i];
            int parentId = Integer.parseInt(s.split(" ")[0]);
            Collection<String> users = Arrays.asList(s.split(" ")[1].split(","));
            return new Directory(i, parentId, users);
        }).collect(Collectors.toList());
    }

    private Trees.Node parseFolders(Collection<Directory> folders) {
        Map<Integer, Trees.Node> nodes = new HashMap<>();
        folders.stream().forEachOrdered(folder -> {
            nodes.put(folder.id, new Trees.TreeNode(folder));
        });
        folders.stream().forEachOrdered(folder -> {
            if (folder.id != folder.parentId) {
                Trees.TreeNode<Directory> node = (Trees.TreeNode<Directory>) nodes.get(folder.id);
                Trees.TreeNode<Directory> parentNode = (Trees.TreeNode<Directory>) nodes.get(folder.parentId);
                System.out.println("Folder " + folder.parentId + " is parent of " + folder.id);
                parentNode.addChild(node);
            }
        });
        return nodes.get(0);
    }

    private int[] findHomeDirectories(Trees.Node<Directory> tree, String[] users) {
        int[] result = new int[users.length];
        Map<String, Directory> homeDirectories = new HashMap<>();
        Trees.traverseBFS(tree, node -> {
            Directory directory = node.getValue();
            directory.users.stream().forEach(
                    user -> {
                        if (!homeDirectories.containsKey(user)) {
                            homeDirectories.put(user, directory);
                        } else {
                            Directory userDirectory = homeDirectories.get(user);
                            Directory newUserDirectory = Trees.findLowestCommonAncestor(tree, userDirectory, directory);
                            homeDirectories.put(user, newUserDirectory);
                        }
                    });
        });
        for (int i = 0; i < users.length; i++) {
            result[i] = homeDirectories.containsKey(users[i]) ? homeDirectories.get(users[i]).id : -1;
        }
        return result;
    }

    private void processInput() {
//
//        String[] folders = new String[] { "0 Admin", "0 Joe,Phil", "0 Joe" };
//        String[] users = new String[] { "Admin", "Joe", "Phil" };
//
//        String[] folders = new String[] { "0 Admin", "2 John", "0 Peter,John", "0 Tim", "1 John" };
//        String[] users = new String[] { "Admin", "John", "Peter", "Tim", "Joe" };
//
        String[] folders = new String[] { "0 Admin",
                                          "0 Jeff", "1 Mark,Tim", "1 Tim,Jeff",
                                          "0 Dan", "4 Ed", "4 Tom", "4 Kyle,Ed",
                                          "0 Ben", "8 Rich", "8 Sam", "8 Tim" };
        String[] users = new String[] { "Jeff", "Ed", "Tim", "Steve" };
        Collection<Directory> directories = parseFolders(folders);
        directories.stream().forEachOrdered(d -> System.out.println("Directory: " + d));
        Trees.Node<Directory> tree = parseFolders(directories);
        int[] homeDirectories = findHomeDirectories(tree, users);
        Arrays.stream(homeDirectories).forEachOrdered(i -> System.out.print("" + i + " "));
        System.out.println();
    }

    public static void main(String[] args) {
        FindHomeDirectories findHomeDirectories = new FindHomeDirectories();
        findHomeDirectories.processInput();
    }
}
