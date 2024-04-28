package P03_IteratorTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;


public class ListIteratorTest {

    private ListIterator listIterator;
    private static final String[] NAMES = {"Pesho", "Tosho", "Gosho"};

    @Before
    public void prepare() throws OperationNotSupportedException {
        listIterator = new ListIterator(NAMES);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorShouldThrowWithNull() throws OperationNotSupportedException {
        new ListIterator(null);
    }
    @Test
    public void testConstructorShouldCreate() throws OperationNotSupportedException {
        new ListIterator(NAMES);
    }
    @Test
    public void testHasNext(){
        Assert.assertTrue(listIterator.hasNext()); //true
        listIterator.move(); //Tosho
        Assert.assertTrue(listIterator.hasNext()); //true
        listIterator.move(); //Gosho
        Assert.assertFalse(listIterator.hasNext()); //false
        Assert.assertFalse(listIterator.move()); //false
    }
    @Test(expected = IllegalStateException.class)
    public void testPrintWithEmptyListShouldThrow() throws OperationNotSupportedException {
       ListIterator listIterator = new ListIterator();
       listIterator.print();
    }
    @Test
    public void testPrintShouldReturnIndex() {

        Assert.assertEquals(NAMES[0], listIterator.print());
        listIterator.move();
        Assert.assertEquals(NAMES[1], listIterator.print());
    }
}