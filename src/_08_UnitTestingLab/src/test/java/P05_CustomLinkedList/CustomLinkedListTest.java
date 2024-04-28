package P05_CustomLinkedList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomLinkedListTest {
    CustomLinkedList<String> list;

    @Before
    public void prepare() {
        list = new CustomLinkedList<>();
        list.add("Pesho");
        list.add("Gosho");
        list.add("Tosho");
    }

    @Test
    public void testAddShouldAdd() {
        int previousSize = list.getCount();
        list.add("Andrei");
        int currentSize = list.getCount();
        Assert.assertEquals(previousSize + 1, currentSize);
        Assert.assertEquals(list.getCount() - 1, list.indexOf("Andrei"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetShouldThrowWithNegativeIndex() {
        list.get(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetShouldThrowWithBigIndex() {
        list.get(list.getCount() + 1);
    }

    @Test
    public void testGetShouldGet() {
        Assert.assertEquals("Gosho", list.get(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetShouldThrowWithNegativeIndex() {
        list.set(-1, "Toshko");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetShouldThrowWithBigIndex() {
        list.set(list.getCount() + 1, "Pesho");
    }

    @Test
    public void setShouldSet() {
        list.set(1, "Ivan");
        Assert.assertEquals(list.get(1), "Ivan");
    }

    @Test
    public void testIndexOfShouldFindIndex() {
        assertEquals(1, list.indexOf("Gosho"));
    }

    @Test
    public void testIndexOfShouldNotFindIndex() {
        assertEquals(-1, list.indexOf("Ivan"));
    }

    @Test
    public void testContainsShouldReturnTrue() {
        Assert.assertTrue(list.contains("Gosho"));
    }

    @Test
    public void testContainsShouldReturnFalse() {
        Assert.assertFalse(list.contains("Ivan"));
    }

    @Test
    public void testRemoveWithMissingElement() {
        Assert.assertEquals(-1, list.remove("Ivan"));
    }

    @Test
    public void testRemoveShouldRemoveElement() {
        int countBeforeRemove = list.getCount();
        Assert.assertEquals(1, list.remove("Gosho"));
        int countAfterRemove = list.getCount();
        Assert.assertEquals(countBeforeRemove - 1, countAfterRemove);
        Assert.assertEquals(-1, list.indexOf("Gosho"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveAtShouldThrowWithNegativeIndex() {
        list.removeAt(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveAtShouldThrowWithBigIndex() {
        list.removeAt(list.getCount() + 1);
    }

    @Test
    public void testRemoveAtShouldRemoveElement() {
        int countBeforeRemoveAt = list.getCount();
        String removedItem = list.removeAt(1);
        int countAfterRemoveAt = list.getCount();
        Assert.assertEquals("Gosho", removedItem);
        Assert.assertEquals(countBeforeRemoveAt - 1, countAfterRemoveAt);
    }

}