package priorityQueue;

import org.junit.Assert;
import org.junit.Test;

public class BinaryHeapTest {

    @Test
    public void testEmptyCheck() {
        Integer[] integers = {};
        BinaryHeap<Integer> binaryHeap = new BinaryHeap<>(integers);

        Assert.assertTrue(binaryHeap.isEmpty());

        binaryHeap.add(1);

        Assert.assertFalse(binaryHeap.isEmpty());

        binaryHeap.pop();

        Assert.assertTrue(binaryHeap.isEmpty());
    }

    @Test
    public void testAddingElements() {
        Integer[] integers = {};
        BinaryHeap<Integer> binaryHeap = new BinaryHeap<>(integers);

        binaryHeap.add(7);

        Assert.assertEquals(Integer.valueOf(7), binaryHeap.getTopPriority().orElse(0));

        binaryHeap.add(2);

        Assert.assertEquals(Integer.valueOf(2), binaryHeap.getTopPriority().orElse(0));

        binaryHeap.add(4);

        Assert.assertEquals(Integer.valueOf(2), binaryHeap.pop().orElse(0));

        Assert.assertEquals(Integer.valueOf(4), binaryHeap.getTopPriority().orElse(0));
    }

    @Test
    public void testPop() {
        Integer[] integers = {7, 9, 12, 3, 4, 7, 2, 2, 1};
        BinaryHeap<Integer> binaryHeap = new BinaryHeap<>(integers);

        Assert.assertEquals(Integer.valueOf(1), binaryHeap.pop().orElse(0));
        Assert.assertEquals(Integer.valueOf(2), binaryHeap.pop().orElse(0));
        Assert.assertEquals(Integer.valueOf(2), binaryHeap.pop().orElse(0));
        Assert.assertEquals(Integer.valueOf(3), binaryHeap.pop().orElse(0));
        Assert.assertEquals(Integer.valueOf(4), binaryHeap.pop().orElse(0));
        Assert.assertEquals(Integer.valueOf(7), binaryHeap.pop().orElse(0));
        Assert.assertEquals(Integer.valueOf(7), binaryHeap.pop().orElse(0));
        Assert.assertEquals(Integer.valueOf(9), binaryHeap.pop().orElse(0));
        Assert.assertEquals(Integer.valueOf(12), binaryHeap.pop().orElse(0));

        // Empty Value
        Assert.assertEquals(Integer.valueOf(0), binaryHeap.pop().orElse(0));
    }
}
