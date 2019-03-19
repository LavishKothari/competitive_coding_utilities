import java.util.List;

public class OutputUtilities {
    public static void print(List<?> list, char separatingChar) {
        StringBuilder sb = new StringBuilder();
        for (Object o : list) {
            sb.append(o);
            sb.append(separatingChar);
        }
        System.out.println(sb.toString());
    }
}
