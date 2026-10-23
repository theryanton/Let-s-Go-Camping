package edu.usc.csci310.project.UnitTests.ModelUnitTests;

import edu.usc.csci310.project.models.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArrayListHeapUnitTests {

    //--------------------------------------------
    //UNIT TESTS FOR ARRAYLISTHEAP using INTEGER |
    //--------------------------------------------

    private ArrayListHeap<Integer> heap;

    @BeforeEach
    void setUp() {
        heap = new ArrayListHeap<>();
    }

    @Test
    void testInsert() {
        heap.insert(10);
        heap.insert(20);
        heap.insert(5);

        assertEquals(3, heap.size());
        assertTrue(heap.get(0) >= heap.get(1));
        assertTrue(heap.get(0) >= heap.get(2));
    }

    @Test
    void testHeapRemove() {
        heap.insert(10);
        heap.insert(20);
        heap.insert(5);
        heap.remove(1);
        assertEquals(2, heap.size());
        assertEquals(-1,heap.find(10)); //check that 10 has been successfully removed
    }

    @Test
    void testMaxHeapify() {
        heap.insert(30);
        heap.insert(20);
        heap.insert(10);
        heap.insert(40); // This should trigger maxHeapify when inserted at the end and then swapped to the root

        assertEquals(4, heap.size());
        assertEquals(40, heap.get(0));
        assertEquals(30, heap.get(1));
//        assertTrue(heap.get(1) >= heap.get(heap.getLeftChild(1)));
//        assertTrue(heap.get(1) >= heap.get(heap.rightChild(1)));
    }

    @Test
    void testHeapFind() {
        heap.insert(10);
        heap.insert(20);
        heap.insert(30);

        assertEquals(2,heap.find(20));
        assertEquals(-1,heap.find(40)); // Element not in the heap
    }

    @Test
    void testRemoveRoot() {
        heap.insert(10);
        heap.insert(20);
        heap.insert(30);
        assertEquals(30, heap.remove(0)); // Remove the root
        assertEquals(2, heap.size());
        assertEquals(20, heap.get(0)); // New root should be the next maximum
    }

    @Test
    void testRemoveWithEmptyHeap() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            heap.remove(0); // Attempt to remove from an empty heap
        });
    }

    @Test
    void testRemoveTriggerCompareValGreater() {
        // initialize heap to be
        //          50,
        //       20,   49,
        //     15,10, 45,47
        // then remove 15
        //root
        heap.insert(50);

        //layer 1
        heap.insert(20);
        heap.insert(49);

        //layer 2
        heap.insert(15);
        heap.insert(10);
        heap.insert(45);
        heap.insert(47);

        //then remove 10 and replace with 47
        heap.remove(4);

        assertEquals(45,heap.get(heap.size()-1));
    }

    @Test
    void testRemoveOnNearEmptyTree() {
        // initialize heap to be
        //          50,
        //       20,   49,
        // then remove 50
        //root
        heap.insert(50);

        //layer 1
        heap.insert(20);

        heap.remove(0);
        assertEquals(20,heap.get(heap.size()-1));
    }

    @Test
    void testInsertAndRemoveSingleElement() {
        heap.insert(50);
        assertEquals(50, heap.remove(0));
        assertEquals(0, heap.size());
    }

    @Test
    void testHeapifyA() {
        heap.insert(50);
        heap.insert(40);
        heap.insert(17);
        heap.insert(41);
        heap.insert(39);
        heap.insert(15);
        heap.insert(14);
        heap.insert(1);
        heap.remove(1);
        assertEquals(14,heap.get(heap.size()-1));
    }

    @Test
    void testHeapifyB() {
        heap.insert(50);
        heap.insert(17);
        heap.insert(40);
        heap.insert(15);
        heap.insert(14);
        heap.insert(39);
        heap.insert(41);
        heap.insert(1);
        heap.remove(2);
        assertEquals(1,heap.get(heap.size()-1));
    }

}
