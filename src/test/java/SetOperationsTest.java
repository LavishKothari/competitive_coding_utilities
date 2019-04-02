import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

public class SetOperationsTest {
    @Test
    public void testBasicUnion() {
        Set<Integer> s1 = Set.of(1, 2, 3, 4);
        Set<Integer> s2 = Set.of(1, 2, 5, 6);

        Set<Integer> union1 = SetOperations.union(s1, s2);
        Assert.assertEquals(6, union1.size());

        Set<Double> s3 = Set.of(1.1, 2.2, 3.3);
        Set<Double> s4 = Set.of(1.1, 4.4, 5.5);
        Set<Double> union2 = SetOperations.union(s3, s4);
        Assert.assertEquals(5, union2.size());
    }

    @Test
    public void testBasicIntersection() {
        Set<Integer> s1 = Set.of(1, 2, 3, 4);
        Set<Integer> s2 = Set.of(1, 2, 5, 6);

        Set<Integer> intersection1 = SetOperations.intersection(s1, s2);
        Assert.assertEquals(2, intersection1.size());

        Set<Double> s3 = Set.of(1.1, 2.2, 3.3);
        Set<Double> s4 = Set.of(1.1, 4.4, 5.5);
        Set<Double> intersection2 = SetOperations.intersection(s3, s4);
        Assert.assertEquals(1, intersection2.size());
    }

    @Test
    public void testBasicDifference() {
        Set<Integer> s1 = Set.of(1, 2, 3, 4);
        Set<Integer> s2 = Set.of(1, 2, 5, 6);

        Set<Integer> difference1 = SetOperations.difference(s1, s2);
        Assert.assertEquals(2, difference1.size());

        Set<Double> s3 = Set.of(1.1, 2.2);
        Set<Double> s4 = Set.of(1.1, 4.4, 5.5);
        Set<Double> difference2 = SetOperations.difference(s3, s4);
        Assert.assertEquals(1, difference2.size());
    }

    @Test
    public void testForBoundedWildCard() {
        Set<Integer> s1 = Set.of(1, 2, 3, 4);
        Set<Double> s2 = Set.of(1.0, 2.0, 5.0, 6.0);

        Set<Number> union1 = SetOperations.union(s1, s2);
        Assert.assertEquals(8, union1.size());
    }

}
