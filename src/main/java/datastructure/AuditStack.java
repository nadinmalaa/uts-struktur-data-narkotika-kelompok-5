package datastructure;

import java.util.Stack;

public class AuditStack<T> {
    private Stack<T> stack;

    public AuditStack() {
        this.stack = new Stack<>();
    }

    public void push(T data) {
        stack.push(data);
    }

    public T pop() {
        if (stack.isEmpty()) {
            return null;
        }
        return stack.pop();
    }

    public int size() {
        return stack.size();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public boolean validateInvariant() {
        return stack != null;
    }

    public boolean searchPreserve(T data) {
        return stack.contains(data);
    }
}