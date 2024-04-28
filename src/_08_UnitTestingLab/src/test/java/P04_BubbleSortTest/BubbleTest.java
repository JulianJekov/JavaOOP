package P04_BubbleSortTest;

import org.junit.Assert;
import org.junit.Test;

public class BubbleTest {

    @Test
    public void testBubbleShouldSort() {
        int[] numbers = {1, 3, 4, 2, 5, 0};
        int[] sortedNumbers = {0, 1, 2, 3, 4, 5};
        Bubble.sort(numbers);
        Assert.assertArrayEquals(sortedNumbers, numbers);
    }

}