import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class MathUtilities {

    private MathUtilities() {
    }

    public static long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    public static long power(long a, long b) {
        if (a == 0) return 0l;
        if (a == 1 || b == 0) return 1l;
        if (b == 1) return a;
        long t = power(a, b >> 1);
        if (b % 2 == 0) return t * t;
        return t * t * a;
    }

    public static long power(long a, long b, long mod) {
        if (a == 0) return 0l;
        if (a == 1 || b == 0) return 1l;
        if (b == 1) return a % mod;
        long t = power(a, b >> 1, mod);
        if (b % 2 == 0) return (t * t) % mod;
        return ((t * t) % mod * a) % mod;
    }

    /**
     * @param n
     * @return a list of primes less than or equal to n
     */
    public static List<Integer> getPrimes(int n) {
        BitSet isPrime = getPrimeBitSet(n);
        List<Integer> primes = new ArrayList<>();
        for (int i = 0; i <= n; i++)
            if (isPrime.get(i))
                primes.add(i);
        return primes;
    }

    public static boolean isPrime(long n) {
        if (n < 0) throw new IllegalArgumentException("the argument should be non-negative");
        if (n == 0 || n == 1) return false;
        return IntStream.rangeClosed(2, (int) Math.sqrt(n))
                .noneMatch(i -> n % i == 0);
    }

    /**
     * @param n
     * @return a {@link BitSet} of primes less than or equal to n.
     */
    public static BitSet getPrimeBitSet(int n) {
        BitSet b = new BitSet();
        b.set(0, n + 1, true);
        b.set(0, false);
        b.set(1, false);

        for (int i = 2; i * i <= n; i++)
            if (b.get(i))
                for (int j = i * i; j <= n; j += i)
                    b.set(j, false);
        return b;
    }

    public static boolean isPerfectSquare(long n) {
        return getPerfectSquareRoot(n).isPresent();
    }

    public static Optional<Long> getPerfectSquareRoot(long n) {
        long x = (long) Math.sqrt(n);
        if (x * x == n) return Optional.of(x);
        int counter = 1;
        while ((x + counter) * (x + counter) <= n) {
            if ((x + counter) * (x + counter) == n)
                return Optional.of(x + counter);
            counter++;
        }
        return Optional.empty();
    }

    /**
     * @param n
     * @return all the prime factors of {@code n}
     */
    public static List<Long> getFactors(long n, List<Integer> primes) {
        List<Long> result = new ArrayList<>();
        int pc = 0;
        while (n != 1) {
            if (isPrime(n)) {
                result.add(n);
                return result;
            }
            int cp = primes.get(pc);
            while (n != 1 && n % cp == 0) {
                result.add((long) cp);
                n /= cp;
            }
            pc++;
        }
        return result;
    }

}
