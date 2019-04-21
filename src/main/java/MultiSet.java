import java.util.*;
import java.util.stream.Collectors;

/**
 * backed by a {@link java.util.HashMap}
 *
 * @param <T>
 */
public class MultiSet<T> implements Collection<T> {

    private final Map<T, Integer> map = getBackingStore();
    private int size;

    private Map<T, Integer> getBackingStore() {
        return new HashMap<>();
    }

    /**
     * Returns the size of this multiset
     * in O(1) - constant time
     *
     * @return
     */
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @return a list view of this map
     */
    private List<T> getList() {
        return map.entrySet()
                .stream()
                .map(entry -> Collections.nCopies(entry.getValue(), entry.getKey()))
                .flatMap(list -> list.stream())
                .collect(Collectors.toList());
    }

    @Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    @Override
    public Iterator<T> iterator() {
        return getList().iterator();
    }

    @Override
    public Object[] toArray() {
        return getList().toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (size <= a.length) {
            System.arraycopy(toArray(), 0, a, 0, size);
            return a;
        }
        return (T1[]) toArray();
    }

    @Override
    public boolean add(T t) {
        map.merge(t, 1, Integer::sum);
        size++;
        return true;
    }

    /**
     * If there are more than one occurrence of the element
     * that is to be deleted, then only one of the occurrence
     * is deleted.
     *
     * @param o
     * @return
     */
    @Override
    public boolean remove(Object o) {
        if (map.containsKey(o)) {
            assert map.get(o) > 0;
            map.put((T) o, map.get(o) - 1);
            if (map.get(o) == 0) map.remove(o);
            size--;
            return true;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return map.keySet().containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean retVal = false;
        for (T t : c) {
            retVal = add(t) || retVal;
        }
        return retVal;
    }

    /**
     * removes the elements based on their occurrence in the collection.
     * if c=[a,a,b,c]
     * and this = {a,a,a,b,d}
     * then after calling this method - this will be modified to
     * this = {a,d}
     *
     * @param c
     * @return
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        boolean retValue = false;
        for (Object t : c) {
            retValue = remove(t) || retValue;
        }
        return retValue;
    }

    /**
     * retains all the occurrences
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        List<T> cList = c.stream()
                .filter(ele -> map.containsKey(ele))
                .map(ele -> (T) ele)
                .collect(Collectors.toList());

        List<T> mList = getList();
        boolean retValue = mList.retainAll(cList);
        clear();
        addAll(mList);
        return retValue;
    }

    @Override
    public void clear() {
        map.clear();
        size = 0;
    }

    @Override
    public String toString() {
        return map.toString();
    }

    public Set<T> getUniqueElements() {
        return map.keySet();
    }

    public int getCount(T t) {
        return map.get(t);
    }
}
