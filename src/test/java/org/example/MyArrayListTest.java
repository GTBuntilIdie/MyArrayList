package org.example;


import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.assertEquals;

public class MyArrayListTest {

    private MyArrayList<Integer> myList;

    @Before
    public void createMyList() {
        myList = new MyArrayList<>();
    }

    @Test
    public void testAdd() {
        myList.add(1);
        assertEquals(1, myList.size);
    }

    @Test
    public void testAddAtIndex() {
        for (int i = 0; i < 10; i++) {
            myList.add(i, i);
        }
        assertEquals(10, myList.size);
    }

    @Test
    public void testGet() {
        for (int i = 0; i < 10; i++) {
            myList.add(i);
            assertEquals(i, (int) myList.get(i));
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetOutOfBounds() {
        myList.get(0);
    }

    @Test
    public void testRemove() {
        for (int i = 0; i < 10; i++) {
            myList.add(i);
        }
        for (int i = 0; i < 3; i++) {
            myList.remove(i);
        }
        assertEquals(10 - 3, myList.size);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveOutOfBounds() {
        myList.remove(0);
    }

    @Test
    public void testClear() {
        for (int i = 0; i < 10; i++) {
            myList.add(i);
        }
        myList.clear();
        assertEquals(0, myList.size);
    }

    @Test
    public void testReplace() {
        for (int i = 0; i < 10; i++) {
            myList.add(i);
            myList.replace(i, 2);
            assertEquals(2, (int) myList.get(i));
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testReplaceOutOfBounds() {
        myList.replace(0, 10);
    }

    @Test
    public void testSort() {
        for (int i = 0; i < 10; i++) {
            myList.add(i);
        }
        myList.sort(Comparator.reverseOrder());
        assertEquals(0, (int) myList.get(9));
        assertEquals(9, (int) myList.get(0));
    }
}