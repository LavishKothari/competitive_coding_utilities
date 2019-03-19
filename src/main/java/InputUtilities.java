import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputUtilities {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private InputUtilities() {
    }

    public static List<Integer> readListOfInteger() throws IOException {
        return Arrays.stream(br.readLine().split(" "))
                .map(i -> Integer.parseInt(i))
                .collect(Collectors.toList());
    }

    public static List<Long> readListOfLong()  throws IOException {
        return Arrays.stream(br.readLine().split(" "))
                .map(i -> Long.parseLong(i))
                .collect(Collectors.toList());
    }

    public static List<Double> readListOfDouble()  throws IOException {
        return Arrays.stream(br.readLine().split(" "))
                .map(i -> Double.parseDouble(i))
                .collect(Collectors.toList());
    }

    public static List<String> readListOfString() throws IOException {
        return Arrays.stream(br.readLine().split(" "))
                .collect(Collectors.toList());
    }

    public static int readInt() throws IOException {
        return Integer.parseInt(br.readLine());
    }

}
