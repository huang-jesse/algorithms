import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    static Scanner in = new Scanner(System.in); // 输入结果
    static PrintWriter out = new PrintWriter(System.out);   // 输出结果参数

    /**
     * 主逻辑
     * **/
    public static void solve(int[] a) {
        Map<Integer, Integer> counter = new HashMap<>();
        for (int num : a) {
            counter.put(num, counter.getOrDefault(num, 0) + 1);
        }
        if (counter.size() == 2 && (counter.get(a[0]) == 2 || counter.get(a[0]) == 3)) {
            out.println("YES");
        } else {
            out.println("NO");
        }
    }

    /**
     * 运行主函数
     * */
    public static void main(String[] args) {
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNext()) { // 注意 while 处理多个 case
            int[] a = getIntArray(in.nextLine());
            solve(a);
        }
        in.close();
        out.close();
    }

    public static int[] getIntArray(String line) {
        StringTokenizer st = new StringTokenizer(line);
        int n = st.countTokens();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        return a;
    }
}