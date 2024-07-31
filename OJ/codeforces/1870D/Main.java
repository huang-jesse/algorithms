import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    static FastScanner in = new FastScanner();  // 快读参数
    static PrintWriter out = new PrintWriter(System.out);   // 输出结果参数
    private static final String OUT_ONE_STR = "1 ";
    private static final String OUT_ZERO_STR = "0 ";
    private static final int INF = (int)(1e9);

    /**
     * 主逻辑
     * **/
    public static void solve() {
        int n = in.nextInt();
        int[] c = in.readIntArray(n);
        int k = in.nextInt();
        // indexes
        Deque<Integer> monotonicStack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!monotonicStack.isEmpty() && c[i] <= c[monotonicStack.peekLast()]) {
                monotonicStack.removeLast();
            }
            monotonicStack.addLast(i);
        }

        // purchase
        Iterator<Integer> iterator = monotonicStack.iterator();
        int preIndex = -1;
        int preCoin = 0;
        int minPurchaseNum = INF;
        while (iterator.hasNext()) {
            int index = iterator.next();
            int curCoin = c[index];
            int diff = curCoin - preCoin;
            minPurchaseNum = Math.min(minPurchaseNum, k / diff);
            k -= minPurchaseNum * diff;
            // print ans
            for (int i = preIndex + 1; i <= index; i++) {
                out.printf("%d ", minPurchaseNum);
            }
            preIndex = index;
            preCoin = curCoin;
        }
        out.println();
    }

    /**
     * 运行主函数
     * T表示测试案例数量 多用于CodeForces 其他OJ需要根据情况进行修改
     * */
    public static void main(String[] args) {
        int T = in.nextInt();
        while (T-- > 0) {
            solve();
        }
        out.close();
    }

    /**
     * 快读模板
     * */
    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        // 读取字符串
        String next() {
            while (!st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        // 读取输入的数字
        int nextInt() { return Integer.parseInt(next()); }
        long nextLong() { return Long.parseLong(next()); }

        // 读取整型数组
        int[] readIntArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = nextInt();
            return a;
        }

        long[] readLongArray(int n) {
            long[] a = new long[n];
            for (int i = 0; i < n; i++) a[i] = nextLong();
            return a;
        }
    }

}