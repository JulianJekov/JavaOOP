package P01_Database;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class DatabaseTest {
    private static final Integer[] NUMBERS = {7, 3, 2, 1};
    private Database database;

    @Before
    public void prepare() throws OperationNotSupportedException {
        database = new Database(NUMBERS);
    }

    @Test
    public void testConstructorCreatesValidDB() {
        Integer[] dbElements = database.getElements();
        Assert.assertArrayEquals(NUMBERS, dbElements);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testDatabaseCreatedWithMoreThan16ElementsShouldThrow() throws OperationNotSupportedException {
        Integer[] overSize = new Integer[17];
        new Database(overSize);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testDatabaseCreatedWithLessThan1ElementShouldThrow() throws OperationNotSupportedException {
        Integer[] underSize = new Integer[0];
        new Database(underSize);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddElementEqualsNullShouldThrow() throws OperationNotSupportedException {
        database.add(null);
    }

    @Test

    public void testShouldAddElement() throws OperationNotSupportedException {
        database.add(42);
        Integer[] dbElements = database.getElements();
        Assert.assertEquals(dbElements[dbElements.length - 1], Integer.valueOf(42));
        Assert.assertEquals(dbElements.length, NUMBERS.length + 1);
    }

    @Test(expected = OperationNotSupportedException.class)

    public void testRemoveNullElementShouldThrow() throws OperationNotSupportedException {
        for (int i = 0; i < NUMBERS.length; i++) {
            database.remove();
        }
        database.remove();
    }
    @Test
    public void removeShouldRemove() throws OperationNotSupportedException {
        Integer[] elementsBeforeRemove = database.getElements();
        database.remove();
        Integer[] elementsAfterRemove = database.getElements();
        Assert.assertEquals(elementsBeforeRemove.length - 1, elementsAfterRemove.length);
        Assert.assertEquals(elementsBeforeRemove[elementsBeforeRemove.length -2],
                elementsAfterRemove[elementsAfterRemove.length -1]);
    }
}