import InStcok.Instock;
import InStcok.Product;
import InStcok.ProductStock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class InstockTest {

    private ProductStock instock;
    private Product product;

    @Before
    public void setUp() {
        this.instock = new Instock();
        this.product = new Product("test_product", 13.00, 1);
    }

    @Test
    public void testAddShouldAddNewProductToInStock() {
        instock.add(product);
        Assert.assertTrue(instock.contains(product));
    }

    @Test
    public void testContainsShouldReturnFalseWhenProductIsMissing() {
        assertFalse(instock.contains(product));
    }

    @Test
    public void testCountShouldReturnTheCorrectNumbersOfProducts() {
        Assert.assertEquals(0, instock.getCount());
        instock.add(product);
        Assert.assertEquals(1, instock.getCount());
    }

    @Test
    public void testFindShouldReturnTheCorrectNthProduct() {
        List<Product> products = addMultipleProducts();
        int expectedIndex = 3;
        Product expected = products.get(expectedIndex);
        Product actual = instock.find(expectedIndex);
        Assert.assertNotNull(actual);
        Assert.assertEquals(expected.getLabel(), actual.getLabel());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testFindShouldThrowWithIndexOutOfRange() {
        List<Product> products = addMultipleProducts();
        instock.find(products.size());
    }

    @Test
    public void testChangeQuantityShouldUpdateTheProductQuantity() {
        instock.add(product);
        int expectedQuantity = product.getQuantity() + 13;
        instock.changeQuantity(product.getLabel(), expectedQuantity);
        Assert.assertEquals(expectedQuantity, product.getQuantity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testChangeQuantityShouldThrowIfProductWithLabelIsMissing() {
        instock.changeQuantity("missing_label", 13);
    }

    @Test
    public void testFindByLabelShouldReturnTheProductWithTheSameLabel() {
        addMultipleProducts();
        instock.add(product);

        Product actual = instock.findByLabel(product.getLabel());
        Assert.assertNotNull(actual);
        Assert.assertEquals(product.getLabel(), actual.getLabel());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindByLabelShouldThrowIfProductWithLabelIsMissing() {
        instock.findByLabel("missing_label");
    }

    @Test
    public void testFindFirstByAlphabeticalOrderShouldReturnNProducts() {
        addMultipleProducts();
        int expectedCount = 3;
        List<Product> actual = iterableToList(instock.findFirstByAlphabeticalOrder(expectedCount));
        Assert.assertEquals(expectedCount, actual.size());
    }

    @Test
    public void testFindFirstByAlphabeticalOrderShouldReturnTheProductsOrderedByLabel() {
        List<Product> products = addMultipleProducts()
                .stream()
                .sorted(Comparator.comparing(Product::getLabel))
                .collect(Collectors.toList());

        int expectedCount = products.size();
        List<Product> actual = iterableToList(instock.findFirstByAlphabeticalOrder(expectedCount));

        assertEquals(expectedCount, actual.size());

        for (int i = 0; i < products.size(); i++) {
            String expectedLabel = products.get(i).getLabel();
            String actualLabel = actual.get(i).getLabel();
            Assert.assertEquals(expectedLabel, actualLabel);
        }
    }

    @Test
    public void testFindFirstByAlphabeticalOrderShouldReturnEmptyCollectionIfTheArgumentIsOutOfRange() {
        int size = addMultipleProducts().size();
        List<Product> actual = iterableToList(instock.findFirstByAlphabeticalOrder(size + 1));
        Assert.assertEquals(0, actual.size());
    }

    @Test
    public void testFindAllInRangeShouldReturnTheCorrectRange() {
        final int beginRange = 2;
        final int endRange = 13;

        List<Product> expected = addMultipleProducts()
                .stream()
                .filter(p -> p.getPrice() > beginRange && p.getPrice() <= endRange)
                .collect(Collectors.toList());

        List<Product> actual = iterableToList(instock.findAllInRange(beginRange, endRange));

        Assert.assertEquals(expected.size(), actual.size());

        boolean hasNoOutOfRangePrices = actual.stream()
                .map(Product::getPrice)
                .noneMatch(p -> p <= beginRange || p > endRange);

        Assert.assertTrue(hasNoOutOfRangePrices);
    }

    @Test
    public void testFindAllInRangeShouldReturnOrderedProductsByPriceDescending() {
        final int beginRange = 2;
        final int endRange = 13;

        List<Product> expected = addMultipleProducts()
                .stream()
                .filter(p -> p.getPrice() > beginRange && p.getPrice() <= endRange)
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .collect(Collectors.toList());

        List<Product> actual = iterableToList(instock.findAllInRange(beginRange, endRange));

        Assert.assertEquals(expected.size(), actual.size());

        for (int i = 0; i < expected.size(); i++) {
            double expectedPrice = expected.get(i).getPrice();
            double actualPrice = actual.get(i).getPrice();
            Assert.assertEquals(expectedPrice, actualPrice, 0.00);
        }

    }

    @Test
    public void testFindAllByPriceShouldReturnAllTheProductsWithTheGivenPrice() {
        final int expectedPrice = 5;

        List<Product> actual = iterableToList(instock.findAllByPrice(expectedPrice));

        for (Product product : actual) {
            Assert.assertEquals(expectedPrice, product.getPrice(), 0.00);
        }
    }

    @Test
    public void testFindAllByPriceShouldReturnEmptyCollectionWhenNoMatches() {
        addMultipleProducts();
        List<Product> actual = iterableToList(instock.findAllByPrice(-10));
        Assert.assertEquals(0, actual.size());
    }

    @Test
    public void testFindFirstMostExpensiveProductsShouldReturnCorrectMostExpensiveProducts() {
        int productsToTake = 5;
        List<Product> expected = addMultipleProducts()
                .stream()
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .limit(productsToTake)
                .collect(Collectors.toList());

        List<Product> actual = iterableToList(instock.findFirstMostExpensiveProducts(productsToTake));

        Assert.assertEquals(expected.size(), actual.size());

        for (int i = 0; i < expected.size(); i++) {
            double expectedPrice = expected.get(i).getPrice();
            double actualPrice = actual.get(i).getPrice();
            Assert.assertEquals(expectedPrice, actualPrice, 0.00);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindFirstMostExpensiveProductsShouldThrowWithLessProductsInCollection() {
        int size = addMultipleProducts().size();
        instock.findFirstMostExpensiveProducts(size + 1);
    }

    @Test
    public void testFindAllByQuantityShouldReturnAllTheProductsWithTheGivenQuantity() {
        addMultipleProducts();
        final int expectedQuantity = 5;

        List<Product> actual = iterableToList(instock.findAllByQuantity(expectedQuantity));

        for (Product product : actual) {
            Assert.assertEquals(expectedQuantity, product.getQuantity());
        }
    }

    @Test
    public void testFindAllByQuantityShouldReturnEmptyCollectionWhenNoMatches() {
        addMultipleProducts();
        List<Product> actual = iterableToList(instock.findAllByQuantity(-10));
        Assert.assertEquals(0, actual.size());
    }

    @Test
    public void testIteratorShouldReturnAllTheProductsInTheStock() {
        List<Product> expected = addMultipleProducts();

        Iterator<Product> iterator = instock.iterator();

        List<Product> actual = new ArrayList<>();

        iterator.forEachRemaining(actual::add);

        Assert.assertEquals(expected, actual);
    }

    private List<Product> addMultipleProducts() {
        List<Product> products = List.of(
                new Product("label_2", 10, 3),
                new Product("label_1", 3, 2),
                new Product("label_3", 5, 1),
                new Product("label_5", 5, 5),
                new Product("label_4", 2, 5),
                new Product("label_7", 5, 5),
                new Product("label_6", 7, 13),
                new Product("label_8", 13, 8)
        );
        products.forEach(instock::add);

        return products;
    }

    private List<Product> iterableToList(Iterable<Product> iterable) {
        Assert.assertNotNull(iterable);
        List<Product> products = new ArrayList<>();
        iterable.forEach(products::add);
        return products;
    }
}