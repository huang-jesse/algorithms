import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    static FastScanner in = new FastScanner();  // 快读参数
    static PrintWriter out = new PrintWriter(System.out);   // 输出结果参数
    private static final int MAX_DIVIDE_TIMES = 32;

    /**
     * 主逻辑
     * **/
    public static void solve() {
        int n = in.nextInt();
        int[] a = in.readIntArray(n);
        int ans = MAX_DIVIDE_TIMES;
        for (int num : a) {
            int count = getPerformTimes(num);
            ans = Math.min(ans, count);
        }
        out.println(ans);
    }

    private static int getPerformTimes(int num) {
        int count = 0;
        while (isEven(num)) {
            num = num >> 1;
            count++;
        }
        return count;
    }

    private static boolean isEven(int num) {
        return (num & 1) == 0;
    }

    /**
     * 运行主函数
     * T表示测试案例数量 多用于CodeForces 其他OJ需要根据情况进行修改
     * */
    public static void main(String[] args) {
        solve();
        out.close();
    }

    /**
     * 自定义类
     * 需要重写hashCode方法和equals方法
     * 这样才能正确使用Set<T>和Map<K, V>等集合元素（多用于哈希表）
     * */
    static class PII {
        int x;
        int y;
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PII p = (PII) o;
            return Objects.equals(x, p.x) && Objects.equals(y, p.y);
        }

        PII(int x, int y) {
            this.x = x;
            this.y = y;
        }
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