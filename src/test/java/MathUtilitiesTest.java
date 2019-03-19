import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class MathUtilitiesTest {

    @Test
    public void isPrimeTest() {
        Assert.assertTrue(MathUtilities.isPrime(2));
        Assert.assertTrue(MathUtilities.isPrime(3));
        Assert.assertTrue(MathUtilities.isPrime(5));
        Assert.assertTrue(MathUtilities.isPrime(7));
        Assert.assertTrue(MathUtilities.isPrime(11));
        Assert.assertTrue(MathUtilities.isPrime(13));
        Assert.assertTrue(MathUtilities.isPrime(97));
        Assert.assertTrue(MathUtilities.isPrime(2_038_074_743l));

        Assert.assertFalse(MathUtilities.isPrime(1));
        Assert.assertFalse(MathUtilities.isPrime(0));
        Assert.assertFalse(MathUtilities.isPrime(4));
        Assert.assertFalse(MathUtilities.isPrime(6));
        Assert.assertFalse(MathUtilities.isPrime(100));
        Assert.assertFalse(MathUtilities.isPrime(32));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidArgsToIsPrime() {
        MathUtilities.isPrime(-255);
    }

    @Test
    public void gcdTest() {
        Assert.assertEquals(1, MathUtilities.gcd(1, 5));
        Assert.assertEquals(10, MathUtilities.gcd(10, 50));
        Assert.assertEquals(5, MathUtilities.gcd(15, 20));
        Assert.assertEquals(1, MathUtilities.gcd(10, 17));
        Assert.assertEquals(6, MathUtilities.gcd(12, 18));
        Assert.assertEquals(2, MathUtilities.gcd(10, 123456789123456l));
    }

    @Test
    public void powerTest() {
        Assert.assertEquals(100, MathUtilities.power(10, 2));
        Assert.assertEquals(2147483648l, MathUtilities.power(2, 31));
        Assert.assertEquals(1, MathUtilities.power(5, 0));
        Assert.assertEquals(123456789123l, MathUtilities.power(123456789123l, 1));
    }

    @Test
    public void powerModTest() {
        Assert.assertEquals(100, MathUtilities.power(10, 2, 1000));
        Assert.assertEquals(8l, MathUtilities.power(2, 31, 10));
    }

    @Test
    public void perfectSqrtTest() {
        Assert.assertEquals(5l, (long) MathUtilities.getPerfectSquareRoot(5 * 5).get());
        Assert.assertEquals(12345678l, (long) MathUtilities.getPerfectSquareRoot(12345678l * 12345678l).get());
    }

    @Test
    public void getPrimesTest() {
        Assert.assertEquals(25, MathUtilities.getPrimes(100).size());
        Assert.assertEquals(1, MathUtilities.getPrimes(2).size());
    }

    @Test
    public void isPerfectSquareTest() {
        Assert.assertTrue(MathUtilities.isPerfectSquare(12345678l * 12345678l));
        Assert.assertFalse(MathUtilities.isPerfectSquare(1l + 12345678l * 12345678l));
    }

    @Test
    public void getFactorsTest() {
        List<Integer> primes = MathUtilities.getPrimes(100);
        {
            List<Long> factors = MathUtilities.getFactors(12, primes);
            Assert.assertEquals(3, factors.size());
            Assert.assertEquals(7, factors.stream().mapToLong(i -> i).sum());
        }
        {
            List<Long> factors = MathUtilities.getFactors(100, primes);
            Assert.assertEquals(4, factors.size());
            Assert.assertEquals(14, factors.stream().mapToLong(i -> i).sum());
        }
    }

}
