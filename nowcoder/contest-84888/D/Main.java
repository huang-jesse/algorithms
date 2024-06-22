import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    static Scanner in = new Scanner(System.in); // 输入结果
    static PrintWriter out = new PrintWriter(System.out);   // 输出结果参数

    /**
     * 主逻辑
     * **/
    public static void solve() {
    }

    /**
     * 运行主函数
     * */
    public static void main(String[] args) {
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNext()) { // 注意 while 处理多个 case
            solve();
        }
        in.close();
        out.close();
    }

    public static String[] nextNLines(int n) {
        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextLine();
        }
        return arr;
    }

    public static int nextInt() {
        return Integer.parseInt(in.nextLine());
    }

    public static int[] nextIntArray() {
        return getIntArray(in.nextLine());
    }

    private static int[] getIntArray(String line) {
        StringTokenizer st = new StringTokenizer(line);
        int n = st.countTokens();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        return a;
    }
}