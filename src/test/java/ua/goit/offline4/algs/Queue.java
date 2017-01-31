package ua.goit.offline4.algs;

/**
 * Created by andreymi on 1/31/2017.
 */
public class Queue<T> {
    private static class Node<T> {
        T value;
        Node<T> prev;
        Node<T> next;
    }

    private Node<T> head;
    private Node<T> tail;

    public void offer(T value) {
        Node<T> newNode = new Node<>();
        newNode.value = value;
        if (head == null || tail == null) {
            head = newNode;
            tail = newNode;
            return;
        }
        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
    }

    public T  poll() {
        if (head == null) {
            return null;
        }
        Node<T> old = head;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        return old.value;
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>();
        for (int i = 0 ; i < 10; i++) {
            queue.offer(i);
        }
        Integer value;
        while ((value = queue.poll()) != null) {
            System.out.println(value);
        }
        queue.poll();
    }


}
