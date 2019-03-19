import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.IntStream;

public class SegmentTree<T> {
    private final List<T> segTree;
    private final BinaryOperator<T> binaryOperator;
    private final int n;
    private final T identity;

    public SegmentTree(List<T> list, BinaryOperator<T> binaryOperator, T identity) {
        this.binaryOperator = binaryOperator;
        this.identity = identity;
        this.n = list.size();
        segTree = new ArrayList<>(2 * n);
        IntStream.range(0, 2 * n).forEach(i -> segTree.add(null));
        for (int i = n; i < segTree.size(); i++)
            segTree.set(i, list.get(i - n));
        build();
    }

    private void build() {
        for (int i = n - 1; i > 0; i--)
            segTree.set(i, binaryOperator.apply(segTree.get(2 * i), segTree.get(2 * i + 1)));
    }

    public void modify(int p, T value) {
        for (p += n, segTree.set(p, value); p > 1; p >>= 1)
            segTree.set(p >> 1, binaryOperator.apply(segTree.get(p), segTree.get(p ^ 1)));
    }

    public T query(int l, int r) {
        T result = identity;
        for (l += n, r += n; l < r; l >>= 1, r >>= 1) {
            if ((l & 1) == 1) result = binaryOperator.apply(result, segTree.get(l++));
            if ((r & 1) == 1) result = binaryOperator.apply(result, segTree.get(--r));
        }
        return result;
    }

}
