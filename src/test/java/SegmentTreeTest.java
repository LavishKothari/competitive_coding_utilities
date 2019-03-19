import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class SegmentTreeTest {

    @Test
    public void testForIntegerSum() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        SegmentTree<Integer> segTree = new SegmentTree<>(list,
                (a, b) -> a + b,
                0);
        Assert.assertEquals(30, (int) segTree.query(3, 8));
        segTree.modify(5, 10);
        Assert.assertEquals(34, (int) segTree.query(3, 8));
    }

    @Test
    public void testForLongMax() {
        List<Long> list = Arrays.asList(5l, 3l, 6l, 1l, 7l, 2l, 9l, 3l);
        SegmentTree<Long> segTree = new SegmentTree<>(list,
                (a, b) -> Math.max(a, b),
                Long.MIN_VALUE);
        Assert.assertEquals(6l, (long) segTree.query(1, 4));
        Assert.assertEquals(7l, (long) segTree.query(1, 5));
        segTree.modify(4, 2l);
        Assert.assertEquals(6l, (long) segTree.query(1, 5));
    }
}
