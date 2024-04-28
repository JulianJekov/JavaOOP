package P02_ExtendedDatabase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;


public class DatabaseTest {
    private static final Person pesho = new Person(1, "Pesho");
    private static final Person gosho = new Person(2, "Gosho");
    private static final Person tosho = new Person(3, "Tosho");
    private static final Person[] people = {pesho, gosho, tosho};
    private Database database;

    @Before
    public void prepare() throws OperationNotSupportedException {
        database = new Database(people);
    }

    @Test
    public void testConstructorCreateValidDB() {
        Person[] people2 = database.getElements();
        Assert.assertArrayEquals(people, people2);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testDatabaseCreatedWithMoreThan16PeopleShouldThrow() throws OperationNotSupportedException {
        Person[] overSizeArray = new Person[17];
        new Database(overSizeArray);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testDatabaseCreatedWithLessThan1PersonShouldThrow() throws OperationNotSupportedException {
        Person[] underSizeArr = new Person[0];
        new Database(underSizeArr);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddPersonEqualsNullShouldThrow() throws OperationNotSupportedException {
        database.add(null);
    }

    @Test
    public void testAddShouldAddPerson() throws OperationNotSupportedException {
        Person ivan = new Person(4, "Ivan");
        database.add(ivan);
        Person[] dbElements = database.getElements();
        Assert.assertEquals(dbElements[dbElements.length - 1], ivan);
        Assert.assertEquals(dbElements.length, people.length + 1);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testRemoveFromEmptyArrayShouldThrow() throws OperationNotSupportedException {
        for (int i = 0; i < people.length; i++) {
            database.remove();
        }
        database.remove();
    }

    @Test
    public void testRemoveShouldRemove() throws OperationNotSupportedException {
        Person[] beforeRemove = database.getElements();
        database.remove();
        Person[] afterRemove = database.getElements();
        Assert.assertEquals(beforeRemove[beforeRemove.length - 2], afterRemove[afterRemove.length - 1]);
        Assert.assertEquals(beforeRemove.length - 1, afterRemove.length);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByUserNameShouldThrowWithNull() throws OperationNotSupportedException {
        database.findByUsername(null);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByUsernameShouldThrowIfMissing() throws OperationNotSupportedException {
        database.findByUsername("Shasho");
    }

    @Test
    public void testFindByUsernameShouldReturnUser() throws OperationNotSupportedException {
        Person person = database.findByUsername(pesho.getUsername());
        Assert.assertEquals(person.getId(), pesho.getId());
        Assert.assertEquals(person.getUsername(), pesho.getUsername());
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByIdShouldThrowIfMissing() throws OperationNotSupportedException {
        database.findById(10);
    }

    @Test
    public void testFindByIdShouldReturnUser() throws OperationNotSupportedException {
        Person person = database.findById(pesho.getId());
        Assert.assertEquals(person.getId(), pesho.getId());
        Assert.assertEquals(person.getUsername(), pesho.getUsername());
    }
}