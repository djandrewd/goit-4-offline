package ua.goit.offline4.algs;

/**
 * Created by andreymi on 1/31/2017.
 */
public class LinkedList<T> {
    private static class Node<T> {
        T value;
        Node<T> next;
    }

    private Node<T> head;

    public void addTail(T value) {
        if (head == null) {
            head = new Node<>();
        }
        Node<T> node = head;
        while (node.next != null) {
            node = node.next;
        }
        Node<T> newNode = new Node<>();
        newNode.value = value;
        node.next = newNode;
    }

    public void addHead(T value) {
        Node<T> newNode = new Node<>();
        newNode.value = value;
        newNode.next = head;
        head = newNode;
    }

    public T popHead() {
        if (head == null) {
            return null;
        }
        Node<T> old = head;
        head = head.next;
        return old.value;
    }

    public T popTail() {
        if (head == null) {
            return null;
        }
        Node<T> old;
        if (head.next == null) {
            old = head;
            head = null;
        } else {
            Node<T> node = head;
            while (node.next.next != null) {
                node = node.next;
            }
            old = node.next;
            node.next = null;
        }
        return old.value;
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.addHead(i);
        }
        Integer value;
        while ((value = list.popHead()) != null) {
            System.out.println(value);
        }
        for (int i = 0; i < 10; i++) {
            list.addHead(i);
        }
        while ((value = list.popTail()) != null) {
            System.out.println(value);
        }
    }

}
