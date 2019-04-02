import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class SetOperations {
    private SetOperations() {

    }

    /*
        In the return-type don't use bounded wildcard,
        Use bounded wildcard only in parameters.

        For details, read Item-31 of Effective Java 3rd ed.
     */
    public static <T> Set<T> union(Set<? extends T> s1, Set<? extends T> s2) {
        Set<T> result = new HashSet<>(s1);
        result.addAll(s2);
        return result;
    }

    public static <T> Set<T> difference(Set<? extends T> s1, Set<? extends T> s2) {
        Set<T> result = new HashSet<>(s1);
        result.removeAll(s2);
        return result;
    }

    public static <T> Set<T> intersection(Set<? extends T> s1, Set<? extends T> s2) {
        return s1.stream()
                .filter(e -> s2.contains(e))
                .collect(Collectors.toSet());
    }
}
