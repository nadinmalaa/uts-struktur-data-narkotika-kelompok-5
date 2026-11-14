package com.kelompok5.structure;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {

    @Test
    void testPushAndSize() {
        Stack<String> stack = new Stack<>();
        stack.push("Data 1");
        stack.push("Data 2");

        assertEquals(2, stack.size());
        assertFalse(stack.isEmpty());
    }

    @Test
    void testPopUsesLifoOrder() {
        Stack<Integer> stack = new Stack<>();
        stack.push(10);
        stack.push(20);

        assertEquals(20, stack.pop());
        assertEquals(10, stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    void testPeekDoesNotRemoveElement() {
        Stack<String> stack = new Stack<>();
        stack.push("Data");

        assertEquals("Data", stack.peek());
        assertEquals(1, stack.size());
    }

    @Test
    void testPopOnEmptyStack() {
        Stack<String> stack = new Stack<>();

        assertThrows(IllegalStateException.class, stack::pop);
    }

    @Test
    void testPeekOnEmptyStack() {
        Stack<String> stack = new Stack<>();

        assertThrows(IllegalStateException.class, stack::peek);
    }

    @Test
    void testRejectNull() {
        Stack<String> stack = new Stack<>();

        assertThrows(IllegalArgumentException.class,
                () -> stack.push(null));
        assertTrue(stack.isEmpty());
    }

    @Test
    void testClear() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.clear();

        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @Test
    void testResizeCapacity() {
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < 11; i++) {
            stack.push(i);
        }

        assertEquals(11, stack.size());
        assertEquals(20, stack.capacity());
        assertEquals(10, stack.peek());
    }

    @Test
    void testInitialState() {
        Stack<String> stack = new Stack<>();

        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
        assertEquals(10, stack.capacity());
    }
}
