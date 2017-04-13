package week8;

import java.util.*;

/**
 * Created by ksyashka on 08.04.2017.
 */
public class MyHashMap<K, V> implements Map<K, V> {

    //REHASHING
    public static final int DEFAULT_TABLE_SIZE = 16;

    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    final float loadFactor = DEFAULT_LOAD_FACTOR;

    MyNode<K, V>[] table = new MyNode[DEFAULT_TABLE_SIZE];
    int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public V get(Object key) {
        return null;
    }

    @Override
    public V put(K key, V value) {
        int hash = Math.abs(key.hashCode());
        int position = hash % table.length;

        if (table[position] == null)
            table[position] = new MyNode<K, V>(key, value, null);
        else {
            MyNode<K, V> iter = table[position];
            do {
                if (iter.key.equals(key)) {
                    V oldValue = iter.value;
                    iter.value = value;
                    return oldValue;
                }
            } while (iter.next != null);
            iter.next = new MyNode<K, V>(key, value, null);
        }
        size++;
        return null;
    }

    @Override
    public V remove(Object key) {
        return null;

    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<K>();
        Iterator<MyNode<K, V>> iterator = new MyHashMapIterator();
        while (iterator.hasNext())
            keys.add(iterator.next().key);
        return keys;
    }

    @Override
    public Collection<V> values() {
        Collection<V> values = new ArrayList<V>();
        Iterator<MyNode<K, V>> iterator = new MyHashMapIterator();
        while (iterator.hasNext())
            values.add(iterator.next().value);
        return values;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> entrySet = new HashSet<Entry<K, V>>();
        Iterator<MyNode<K, V>> iterator = new MyHashMapIterator();
        while (iterator.hasNext())
            entrySet.add(iterator.next());
        return entrySet;
    }

    private class MyHashMapIterator implements Iterator<MyNode<K, V>> {
        int curIndex;

        public MyNode<K, V> getCurNode() {
            return curNode;
        }

        public void setCurNode(MyNode<K, V> curNode) {
            this.curNode = curNode;
        }

        MyNode<K, V> curNode;

        private void findFirstNonNull() {
            for (; curIndex < table.length && table[curIndex] == null; curIndex++) {
            }
            curNode = curIndex < table.length ? table[curIndex] : null;
        }

        public MyHashMapIterator() {
            findFirstNonNull();
        }

        @Override
        public boolean hasNext() {
            return curNode != null;
        }

        @Override
        public MyNode<K, V> next() {
            MyNode<K, V> result = curNode;
            if (curNode.next != null)
                curNode = curNode.next;
            else findFirstNonNull();
            return result;
        }
    }

    private static class MyNode<NK, NV> implements Entry<NK, NV> {
        NK key;
        NV value;
        MyNode<NK, NV> next;

        public MyNode(NK key, NV value, MyNode<NK, NV> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public NK getKey() {
            return key;
        }

        @Override
        public NV getValue() {
            return value;
        }

        @Override
        public NV setValue(NV value) {
            NV old = this.value;
            this.value = value;
            return old;
        }
    }
}
