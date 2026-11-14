package datastructure;

public class AuditStack<T> {

    // Node internal untuk membangun stack secara manual
    private static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<T> top;
    private int size;

    public AuditStack() {
        this.top = null;
        this.size = 0;
    }

    public void push(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.next = top;
        top = newNode;
        size++;
    }

    public T pop() {
        if (isEmpty()) {
            return null;
        }
        T data = top.data;
        top = top.next;
        size--;
        return data;
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return top.data;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.top == null;
    }

    public boolean validateInvariant() {
        // Memastikan konsistensi ukuran tidak negatif dan top valid
        if (size < 0) return false;
        if (size == 0 && top != null) return false;
        if (size > 0 && top == null) return false;
        return true;
    }

    public boolean searchPreserve(T data) {
        Node<T> current = top;
        while (current != null) {
            if (current.data == null) {
                if (data == null) return true;
            } else if (current.data.equals(data)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
}