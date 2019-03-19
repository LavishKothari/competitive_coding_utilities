import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class GeneralUtilitiesTest {
    @Test
    public void powerSetTest() {
        Assert.assertEquals(16,
                GeneralUtilities.getPowerSet(
                        new HashSet<>(Arrays.asList(1, 2, 3, 4)))
                        .size());
    }

    @Test
    public void getAllPermutationsTest() {
        Assert.assertEquals(120,
                GeneralUtilities.getAllPermutations("abcde").size());
        Assert.assertEquals(1,
                GeneralUtilities.getAllPermutations("a").size());

        Set<Integer> set = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        Assert.assertEquals(120, GeneralUtilities.getAllPermutations(set).size());
    }

    @Test
    public void nextLexicographicPermutationTest() {
        Set<String> set = new TreeSet<>();
        String str = "abc";
        set.add(str);
        String cstr = str;
        while (true) {
            String next = GeneralUtilities.nextLexicographicPermutation(cstr);
            if (next == null) break;
            set.add(next);
            cstr = next;
        }
        List<String> allPermList = GeneralUtilities.getAllPermutations(str);
        Collections.sort(allPermList);

        Assert.assertEquals(allPermList.size(), set.size());
        int counter = 0;
        for (String s : set) {
            Assert.assertEquals(s, allPermList.get(counter++));
        }
    }

}
