package todo.core.source.java.util.hashmap;

import java.util.Map;
import java.util.Objects;

public class MyHashMap<K, V> {

    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    static class Node<K, V> implements Map.Entry<K,V> {
        final int hash;
        final K key;
        V value;
        Node<K, V> next;

        Node(int hash, K key, V value, Node<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value;
        }

        @Override
        public V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }
    }
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    transient Node<K,V>[] table;
    transient int size;
    int threshold;

    final int initialCapacity;
    final float loadFactor;
    public MyHashMap() {
        this.initialCapacity = DEFAULT_INITIAL_CAPACITY;
        this.loadFactor = DEFAULT_LOAD_FACTOR;
    }
    public MyHashMap(int initialCapacity, float loadFactor) {
        this.initialCapacity = initialCapacity;
        this.loadFactor = loadFactor;
    }

    public V get(K k) {
        Node<K,V> e;
        return (e=getNode(hash(k), k)) == null? null: e.value;
    }

    public Node<K, V> getNode(int hash, K k) {
        if(table!=null && table.length>0) {
            Node<K, V> first = table[table.length-1 & hash];
            while (first != null) {
                if(first.hash == hash && (Objects.equals(first.key, k))) {
                    return first;
                } else {
                    first = first.next;
                }
            }
        }
        return null;
    }

    public V put(K k, V v) {
        V oldValue = null;
        Node<K,V>[] tab; Node<K,V> p; int n, i;
        int hash = hash(k);
        if((tab=table) == null || (n=table.length) == 0) {
            n = (tab=resize()).length;
        }
        if((p=tab[i = (n-1) & hash]) == null) {
            tab[i] = newNode(hash, k, v, null);
            size++;
        } else {
            do {
                if(p.hash == hash && Objects.equals(p.key, k)) {
                    oldValue = p.value;
                    p.value = v;
                    break;
                }

                if(p.next == null) {
                    p.next = newNode(hash, k, v, null);
                    break;
                }
            } while ((p=p.next) != null);
        }

        if(size == threshold) {
            resize();
        }
        return oldValue;
    }

    Node<K, V> newNode(int hash, K key, V value, Node<K, V> next) {
        return new Node<>(hash, key, value, next);
    }

    final Node<K,V>[] resize() {
        Node<K, V>[] oldTab = table;
        int oldCap = oldTab==null? 0: oldTab.length;
        int oldThr = threshold;

        int newCap, newThr = 0;
        if(oldCap > 0) {
            newCap = oldCap << 1;
            newThr = (int) (newCap * loadFactor);
        } else {
            newCap = initialCapacity;
            newThr = (int) (initialCapacity * loadFactor);
        }
        threshold = newThr;
        Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
        table = newTab;

        if(oldTab != null) {
            for(int j=0; j<oldTab.length; j++) {
                Node<K,V> e;
                if((e=oldTab[j]) != null) {
                    oldTab[j] = null;
                    if(e.next == null) {
                        newTab[e.hash & (newCap - 1)] = e;
                    } else {
                        Node<K,V> loHead = null, loTail = null;
                        Node<K,V> hiHead = null, hiTail = null;
                        Node<K,V> next;

                        do {
                            next = e.next;
                            if((e.hash & oldCap) == 0) {
                                if(loTail == null) {
                                    loHead = e;
                                } else {
                                    loTail.next = e;
                                }
                                loTail = e;
                            } else {
                                if(hiTail == null) {
                                    hiHead = e;
                                } else {
                                    hiTail.next = e;
                                }
                                hiTail = e;
                            }
                        } while ((e=next) != null);

                        if(loTail != null) {
                            loTail.next = null;
                            newTab[j] = loHead;
                        }
                        if(hiTail != null) {
                            hiTail.next = null;
                            newTab[j + oldCap] = hiHead;
                        }

                    }
                }
            }
        }
        return newTab;
    }

}
