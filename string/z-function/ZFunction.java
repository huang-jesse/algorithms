import java.util.Arrays;
import java.util.stream.Collectors;

public class ZFunction {
    public static int[] zFunctionTrivial(String s) {
        int n = s.length();
        int[] z = new int[n];
        for (int i = 1; i < n; i++) {
            while (i + z[i] < n && s.charAt(z[i]) == s.charAt(i + z[i])) {
                z[i]++;
            }
        }
        return z;
    }

    public static int[] zFunction(String s) {
        int n = s.length();
        int[] z = new int[n];
        for (int i = 1, l = 0, r = 0; i < n; i++) {
            if (i <= r && z[i - l] < r - i + 1) {
                z[i] = z[i - l];
            } else {
                // i <= r && z[i - l] >= r - i + 1 || i > r
                z[i] = Math.max(0, r - i + 1);
                while (i + z[i] < n && s.charAt(z[i]) == s.charAt(i + z[i])) {
                    z[i]++;
                }
            }
            l = i;
            r = i + z[i] - 1;
        }
        return z;
    }

    public static void main(String[] args) {
        // int[] arr = zFunctionTrivial("abacaba");// 0010301
        // int[] arr = zFunctionTrivial("aaaaa");// 04321
        // int[] arr = zFunction("abacaba");// 0010301
        int[] arr = zFunction("aaaaa");// 04321
        System.out.println(Arrays.stream(arr).boxed().collect(Collectors.toList()));
    }
}