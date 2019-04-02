import java.util.*;

public class GeneralUtilities {

    public static Comparator<List<Integer>> listComparator =
            (l1, l2) -> {
                for (int i = 0; i < l1.size() && i < l2.size(); i++) {
                    int x = l1.get(i).compareTo(l2.get(i));
                    if (x != 0) return x;
                }
                return Integer.compare(l1.size(), l2.size());
            };

    private GeneralUtilities() {
    }

    public static Set<Set<Integer>> getPowerSet(Set<Integer> set) {
        int size = set.size();
        if (size > 31)
            throw new IllegalArgumentException("the size of set is too big");
        int x = (1 << size);
        List<Integer> list = new ArrayList<>(set);
        Set<Set<Integer>> result = new HashSet<>();
        for (int i = 0; i < x; i++) {
            Set<Integer> cs = new HashSet<>();
            int ci = i, counter = 0;
            while (ci != 0) {
                if ((ci & 1) == 1)
                    cs.add(list.get(counter));
                ci >>= 1;
                counter++;
            }
            result.add(cs);
        }

        return result;
    }

    private static List<Set<Integer>> getAllPermutations(List<Integer> list, int counter) {
        List<Set<Integer>> result = new ArrayList<>();
        if (counter >= list.size()) {
            result.add(new HashSet<>(list));
            return result;
        }
        for (int i = counter; i < list.size(); i++) {
            swap(list, i, counter);
            result.addAll(getAllPermutations(list, counter + 1));
            swap(list, i, counter);
        }
        return result;
    }

    public static List<Set<Integer>> getAllPermutations(List<Integer> list) {
        return getAllPermutations(list, 0);
    }

    public static List<Set<Integer>> getAllPermutations(Set<Integer> set) {
        return getAllPermutations(new ArrayList<>(set));
    }

    // TODO: implement this method
    public static String previousLexicographicPermutation(String str) {
        return str;
    }

    /**
     * returns next lexicographic permutation,
     * {@code null} if it doesn't exist
     *
     * @param carray
     * @return
     */
    public static char[] nextLexicographicPermutation(char[] carray) {
        int n = carray.length;
        int pivot = -1;
        for (int i = n - 1; i > 0; i--) {
            if (carray[i] > carray[i - 1]) {
                pivot = i - 1;
                break;
            }
        }
        if (pivot == -1)
            return null;
        int newPivot = n - 1;
        for (int i = pivot + 1; i < n; i++) {
            if (carray[pivot] > carray[i])
                newPivot = i - 1;
        }
        swap(carray, pivot, newPivot);
        reverse(carray, pivot + 1, n);
        return carray;
    }

    public static void reverse(char[] carray, int a, int b) {
        int n = carray.length;
        if (a < 0 || a >= carray.length || b < 0 || b > n)
            throw new IllegalArgumentException("invalid index");
        for (int i = a, j = b - 1; i < j; i++, j--) {
            swap(carray, i, j);
        }
    }

    public static String nextLexicographicPermutation(String str) {
        char[] carray = nextLexicographicPermutation(str.toCharArray());
        if (carray == null) return null;
        return new String(carray);
    }

    /*
        It will be interesting to read Item-31 of Effective Java
        3rd ed.
     */
    public static <T> void swap(List<T> list, int a, int b) {
        list.set(a, list.set(b, list.get(a)));
    }

    public static <T> void swap(T[] arr, int a, int b) {
        T temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void swap(char[] arr, int a, int b) {
        if (a < 0 || a >= arr.length || b < 0 || b >= arr.length)
            throw new IllegalArgumentException("invalid index");
        char temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    private static List<String> getAllPermuataions(char[] carray, int counter) {
        List<String> result = new ArrayList<>();
        if (counter >= carray.length) {
            result.add(new String(carray));
            return result;
        }
        for (int i = counter; i < carray.length; i++) {
            swap(carray, i, counter);
            result.addAll(getAllPermuataions(carray, counter + 1));
            swap(carray, i, counter);
        }
        return result;
    }

    public static List<String> getAllPermutations(char[] carray) {
        return getAllPermuataions(carray, 0);
    }

    public static List<String> getAllPermutations(String str) {
        return getAllPermutations(str.toCharArray());
    }
}
