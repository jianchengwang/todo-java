package todo.core.source.java.util.linkedlist;

public class MyLinkedList<E> {

    transient int size;
    Node<E> first;
    Node<E> last;

    public MyLinkedList() {
    }

    public int size() {
        return size;
    }

    public E get(int index) {
        checkElementIndex(index);
        return node(index).item;
    }

    public boolean add(E e) {
        linkLast(e);
        return true;
    }

    public void add(E e, int index) {
        checkPositionIndex(index);
        if(index == size) {
            linkLast(e);
        } else {
            linkBefore(e, node(index));
        }
    }

    public E remove(int index) {
        checkElementIndex(index);
        return unlink(node(index));
    }

    public boolean remove(Object o) {
        if(o == null) {
            for(Node<E> x = first; x!=null; x = x.next) {
                if(x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for(Node<E> x = first; x!=null; x = x.next) {
                if(o.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    Node<E> node(int index) {
        Node<E> x;
        if (index < (size >> 1)) {
            x = first;
            for(int i=0; i<index; i++) {
                x = x.next;
            }
        } else {
            x = last;
            for(int i=size-1; i>index; i--) {
                x = last.prev;
            }
        }
        return x;
    }

    private void checkElementIndex(int index) {
        if(index > size - 1 || index < 0) {
            throw new RuntimeException("index outbound");
        }
    }

    private void checkPositionIndex(int index) {
        if(index > size || index < 0) {
            throw new RuntimeException("index outbound");
        }
    }

    private void linkLast(E e) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if(l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    private void linkBefore(E e, Node<E> succ) {
        final Node<E> pred = succ.prev;
        final Node<E> newNode = new Node<>(pred, e, succ);
        succ.prev = newNode;
        if(pred == null) {
            first = newNode;
        } else {
            pred.next = newNode;
        }
        size++;
    }

    private E unlink(Node<E> x) {
        E element = x.item;
        Node<E> prev = x.prev;
        Node<E> next = x.next;

        if(prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }
        if(next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }
        x.item = null;
        size--;
        return element;
    }

    private static class Node<E> {
        E item;
        Node<E> prev;
        Node<E> next;
        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}
