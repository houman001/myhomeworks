package com.test.algorithms;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

public class HashTable<K, V> {
    private final int capacity;
    private double loadFactor = 0;
    private final LinkedList<Entry<K, V>>[] values;

    private HashTable(int capacity) {
        this.capacity = capacity;
        values = new LinkedList[capacity];
        for (int i = 0; i < values.length; i++)
            values[i] = new LinkedList<>();
    }

    private void put(K key, V value) {
        int index = generateIndex(key);
        LinkedList<Entry<K, V>> linkedList = values[index];
        for (Entry<K, V> entry : linkedList) {
            if (entry.key.equals(key)) {
                entry.value = value;
                // System.out.println("An existing record was updated.");
                return;
            }
        }
        linkedList.add(new Entry(key, value));
        // System.out.println("A new record is inserted, new length: " + linkedList.size());
        loadFactor = (loadFactor * capacity + 1) / capacity;
        // System.out.println("Load factor: " + loadFactor);
    }

    private boolean contains(K key) {
        return get(key) != null;
    }

    private V get(K key) {
        int index = generateIndex(key);
        LinkedList<Entry<K, V>> linkedList = values[index];
        for (Entry<K, V> entry : linkedList) {
            if (entry.key.equals(key))
                return entry.value;
        }
        return null;
    }

    private void remove(K key) {
        int index = generateIndex(key);
        LinkedList<Entry<K, V>> linkedList = values[index];
        Entry<K, V> entryToRemove = null;
        for (Entry<K, V> entry : linkedList) {
            if (entry.key.equals(key)) {
                entryToRemove = entry;
                break;
            }
        }
        linkedList.remove(entryToRemove);
    }

    private int generateIndex(K key) {
        // We know keys are integers, we do this so we can expect the index.
        int index = Integer.parseInt(key.toString()) % capacity;
        // System.out.println("Key: " + key + ", Index: " + index);
        return index;
    }

    private static class Entry<K, V> {
        K key;
        V value;

        private Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        HashTable<Integer, String> simpleHashTable = new HashTable<>(10);
        Map<Integer, String> testHashMap = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            int key = new Random().nextInt(100);
            String value = "Number " + key;
            simpleHashTable.put(key, value);
            testHashMap.put(key, value);
        }
        for (int i = 0; i < 100; i++) {
            int key = new Random().nextInt(100);
            simpleHashTable.remove(key);
            testHashMap.remove(key);
        }
        for (Map.Entry<Integer, String> entry : testHashMap.entrySet()) {
            if (!simpleHashTable.contains(entry.getKey())) {
                System.err.println("Key not found: " + entry.getKey());
                continue;
            }
            if (!entry.getValue().equals(simpleHashTable.get(entry.getKey())))
                System.err.println("Values not equal for: " + entry.getKey());
        }
    }
}
