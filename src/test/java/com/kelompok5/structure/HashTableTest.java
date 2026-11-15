package com.kelompok5.structure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {

    private HashTable<String, Integer> hashTable;

    @BeforeEach
    void setUp() {
        hashTable = new HashTable<>();
    }

    @Test
    void testPutAndGet() {
        hashTable.put("P001", 100);
        hashTable.put("P002", 200);

        assertEquals(100, hashTable.get("P001"));
        assertEquals(200, hashTable.get("P002"));
        assertEquals(2, hashTable.size());
    }

    @Test
    void testPutExistingKeyUpdatesValue() {
        hashTable.put("P001", 100);

        Integer oldValue = hashTable.put("P001", 200);

        assertEquals(100, oldValue);
        assertEquals(200, hashTable.get("P001"));
        assertEquals(1, hashTable.size());
    }

    @Test
    void testContainsKey() {
        hashTable.put("P001", 100);

        assertTrue(hashTable.containsKey("P001"));
        assertFalse(hashTable.containsKey("P002"));
    }

    @Test
    void testRemove() {
        hashTable.put("P001", 100);
        hashTable.put("P002", 200);

        Integer removed = hashTable.remove("P001");

        assertEquals(100, removed);
        assertNull(hashTable.get("P001"));
        assertEquals(1, hashTable.size());
    }

    @Test
    void testRemoveNonExistingKey() {
        assertNull(hashTable.remove("P999"));
        assertEquals(0, hashTable.size());
    }

    @Test
    void testCollisionHandling() {
        // "Aa" dan "BB" memiliki hashCode Java yang sama.
        hashTable.put("Aa", 100);
        hashTable.put("BB", 200);

        assertEquals(100, hashTable.get("Aa"));
        assertEquals(200, hashTable.get("BB"));
        assertEquals(2, hashTable.size());
        assertTrue(hashTable.validateInvariant());
    }

    @Test
    void testResize() {
        HashTable<Integer, String> table =
                new HashTable<>(2);

        table.put(1, "Satu");
        table.put(2, "Dua");

        assertTrue(table.capacity() > 2);

        assertEquals("Satu", table.get(1));
        assertEquals("Dua", table.get(2));
        assertEquals(2, table.size());
    }

    @Test
    void testClear() {
        hashTable.put("P001", 100);
        hashTable.put("P002", 200);

        hashTable.clear();

        assertTrue(hashTable.isEmpty());
        assertEquals(0, hashTable.size());
        assertNull(hashTable.get("P001"));
        assertTrue(hashTable.validateInvariant());
    }

    @Test
    void testNullKeyRejected() {
        assertThrows(
                IllegalArgumentException.class,
                () -> hashTable.put(null, 100)
        );
    }

    @Test
    void testNullValueRejected() {
        assertThrows(
                IllegalArgumentException.class,
                () -> hashTable.put("P001", null)
        );
    }

    @Test
    void testConstructorWithInvalidCapacity() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new HashTable<String, Integer>(0)
        );
    }

    @Test
    void testValidateInvariant() {
        hashTable.put("P001", 100);
        hashTable.put("P002", 200);
        hashTable.put("P003", 300);

        assertTrue(hashTable.validateInvariant());
    }

    @Test
    void testIsEmpty() {
        assertTrue(hashTable.isEmpty());

        hashTable.put("P001", 100);

        assertFalse(hashTable.isEmpty());
    }

    @Test
    void testRemoveNodeInCollisionChain() {
        hashTable.put("Aa", 100);
        hashTable.put("BB", 200);

        assertEquals(200, hashTable.remove("BB"));
        assertEquals(100, hashTable.get("Aa"));
        assertNull(hashTable.get("BB"));
        assertEquals(1, hashTable.size());

        assertTrue(hashTable.validateInvariant());
    }

    @Test
    void testRemoveTailNodeInCollisionChain() {
        hashTable.put("Aa", 100);
        hashTable.put("BB", 200);

        assertEquals(100, hashTable.remove("Aa"));
        assertEquals(200, hashTable.get("BB"));
        assertNull(hashTable.get("Aa"));
        assertEquals(1, hashTable.size());

        assertTrue(hashTable.validateInvariant());
    }
}
