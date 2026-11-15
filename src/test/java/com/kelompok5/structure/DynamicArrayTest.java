package com.kelompok5.structure;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DynamicArrayTest {

    @Test
    void testAddAndGet() {
        DynamicArray<String> array = new DynamicArray<>();
        array.add("Data 1");
        array.add("Data 2");

        assertEquals(2, array.size());
        assertEquals("Data 1", array.get(0));
        assertEquals("Data 2", array.get(1));
        assertTrue(array.validateInvariant());
    }

    @Test
    void testSet() {
        DynamicArray<String> array = new DynamicArray<>();
        array.add("Lama");
        array.set(0, "Baru");

        assertEquals("Baru", array.get(0));
        assertTrue(array.validateInvariant());
    }

    @Test
    void testRemoveAndShiftElements() {
        DynamicArray<Integer> array = new DynamicArray<>();
        array.add(10);
        array.add(20);
        array.add(30);
        array.remove(1);

        assertEquals(2, array.size());
        assertEquals(10, array.get(0));
        assertEquals(30, array.get(1));
        assertTrue(array.validateInvariant());
    }

    @Test
    void testContains() {
        DynamicArray<String> array = new DynamicArray<>();
        array.add("Narkotika");

        assertTrue(array.contains("Narkotika"));
        assertFalse(array.contains("Psikotropika"));
    }

    @Test
    void testClear() {
        DynamicArray<Integer> array = new DynamicArray<>();
        array.add(1);
        array.add(2);
        array.clear();

        assertTrue(array.isEmpty());
        assertEquals(0, array.size());
        assertTrue(array.validateInvariant());
    }

    @Test
    void testRejectNull() {
        DynamicArray<String> array = new DynamicArray<>();

        assertThrows(IllegalArgumentException.class,
                () -> array.add(null));
        assertTrue(array.isEmpty());
    }

    @Test
    void testRejectInvalidIndex() {
        DynamicArray<String> array = new DynamicArray<>();
        array.add("Data");

        assertThrows(IndexOutOfBoundsException.class,
                () -> array.get(-1));
        assertThrows(IndexOutOfBoundsException.class,
                () -> array.get(1));
        assertThrows(IndexOutOfBoundsException.class,
                () -> array.remove(1));
    }

    @Test
    void testResizeCapacity() {
        DynamicArray<Integer> array = new DynamicArray<>();

        for (int i = 0; i < 11; i++) {
            array.add(i);
        }

        assertEquals(11, array.size());
        assertEquals(20, array.capacity());
        assertEquals(10, array.get(10));
        assertTrue(array.validateInvariant());
    }

    @Test
    void testInitialState() {
        DynamicArray<String> array = new DynamicArray<>();

        assertTrue(array.isEmpty());
        assertEquals(0, array.size());
        assertEquals(10, array.capacity());
        assertTrue(array.validateInvariant());
    }
}
