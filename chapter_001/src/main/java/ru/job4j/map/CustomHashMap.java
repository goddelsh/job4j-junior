package ru.job4j.map;



import ru.job4j.list.utils.Node;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class CustomHashMap<K, V> {

    private int modCount, expectedModCount;

    private Node<K, V>[] table;

    private final int defaultTableSize = 16;

    private final float loadFactor = 0.75f;

    int elementCount = 0;

    public CustomHashMap() {
        table = new Node[defaultTableSize];
    }

    public int getElementCount() {
        return elementCount;
    }

    @Override
    public String toString() {
        String result = "";
        var it = this.iterator();
        while (it.hasNext()) {
            var el = it.next();
            result += el.key + "=" + el.value + System.lineSeparator();
        }
        return result;
    }

    boolean insert(K key, V value) {
        boolean result = false;
        int index = getIndex(getKeyHash(key));
        if (table[index] == null) {
            table[index] = new Node<>(key, value);
            result = true;
            elementCount++;
            modCount++;
            if ((float) elementCount / table.length > loadFactor) {
                this.resize();
            }
        }
        return result;
    }

    V get(K key) {
        Node<K, V> result = table[getIndex(getKeyHash(key))];
        return result == null ? null : result.value;
    }

    boolean delete(K key) {
        boolean result = false;
        var index = getIndex(getKeyHash(key));
        if (table[index] != null) {
            table[index] = null;
            result = true;
            elementCount--;
        }
        modCount++;

        return result;
    }

    private int getKeyHash(K key) {
        return (key == null) ? 0 : key.hashCode();
    }

    private int getIndex(int hash) {
        return (table.length - 1) & hash;
    }


    private void resize() {
        table = Arrays.copyOf(table, table.length << 1);
    }


    public Iterator<Node<K, V>> iterator() {
        expectedModCount = modCount;
        return new Iterator<Node<K, V>>() {
            int index = 0;

            private void checkMod() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
            }


            public boolean hasNext() {
                var result = false;
                checkMod();
                while (index < table.length) {
                    if (table[index] != null) {
                        result = true;
                        break;
                    } else {
                        index++;
                    }
                }
                return result;
            }

            public Node next() {
                checkMod();
                if (!hasNext()) {
                    throw new IndexOutOfBoundsException();
                }
                return table[index++];
            }
        };
    }

    public class Node<K, V> {
        K key;
        V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}
