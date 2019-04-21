import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MultiSetTest {
    // keep this INITIAL_SIZE an even integer less than 100
    private static final int INITIAL_SIZE = 50;
    private MultiSet<Integer> mi;

    @Before
    public void setup() {
        mi = new MultiSet<>();
        for (int i = 0; i < INITIAL_SIZE; i++) {
            mi.add(i);
            mi.add(i);
        }
    }

    @Test
    public void sizeTest() {
        Assert.assertEquals(2 * INITIAL_SIZE, mi.size());
        Assert.assertFalse(mi.isEmpty());

        for (int i = 0; i < INITIAL_SIZE; i++) {
            mi.remove(i);
            mi.remove(i + 100);
        }
        Assert.assertEquals(INITIAL_SIZE, mi.size());
        Assert.assertFalse(mi.isEmpty());

        for (int i = 100; i < 200; i++) {
            mi.remove(i);
        }
        Assert.assertEquals(INITIAL_SIZE, mi.size());
        Assert.assertFalse(mi.isEmpty());

        mi.clear();
        Assert.assertEquals(0, mi.size());
        Assert.assertTrue(mi.isEmpty());
    }

    @Test
    public void containsTest() {
        for (int i = 0; i < INITIAL_SIZE; i++) {
            Assert.assertTrue(mi.contains(i));
        }
    }

    @Test
    public void removeTest() {
        for (int i = 0; i < INITIAL_SIZE; i++) {
            mi.remove(i);
            Assert.assertTrue(mi.contains(i));
        }
        Assert.assertEquals(INITIAL_SIZE, mi.size());
    }

    @Test
    public void addAllTest() {
        mi.clear();
        List<Integer> list = IntStream.range(0, 100)
                .boxed()
                .collect(Collectors.toList());
        mi.addAll(list);
        Assert.assertEquals(100, mi.size());

        Assert.assertTrue(mi.containsAll(list));
        Assert.assertTrue(IntStream.range(0, 100)
                .boxed()
                .map(ele -> mi.contains(ele))
                .allMatch(a -> a)
        );

    }

    @Test
    public void retainAllTest() {
        List<Integer> list = IntStream.range(0, INITIAL_SIZE)
                .filter(a -> a % 2 == 0)
                .boxed()
                .collect(Collectors.toList());
        mi.retainAll(list);
        for (int i = 0; i < INITIAL_SIZE; i++)
            if (i % 2 == 0)
                Assert.assertTrue(mi.contains(i));

        Assert.assertEquals(INITIAL_SIZE, mi.size());
    }

    @Test
    public void removeAllTest() {
        List<Integer> list = IntStream.range(0, INITIAL_SIZE)
                .filter(a -> a % 2 == 0)
                .boxed()
                .collect(Collectors.toList());
        mi.removeAll(list);
        Assert.assertEquals(3 * INITIAL_SIZE / 2, mi.size());
        Assert.assertTrue(IntStream.range(0, INITIAL_SIZE)
                .filter(e -> e % 2 != 0)
                .boxed()
                .map(e -> mi.getCount(e) == 2)
                .allMatch(a -> a));

        Assert.assertTrue(IntStream.range(0, INITIAL_SIZE)
                .filter(e -> e % 2 == 0)
                .boxed()
                .map(e -> mi.getCount(e) == 1)
                .allMatch(a -> a));
    }

    @Test
    public void getUniqueElementsTest() {
        Assert.assertEquals(INITIAL_SIZE, mi.getUniqueElements().size());
    }

}