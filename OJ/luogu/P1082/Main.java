import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    static FastScanner in = new FastScanner();  // 快读参数
    static PrintWriter out = new PrintWriter(System.out);   // 输出结果参数

    /**
     * 主逻辑
     * **/
    public static void solve() {
        int[] arr = in.readIntArray(2);
        int a = arr[0];
        int b = arr[1];
        int[] res = exgcdRec(a, b);
        int x = res[1];
        x = (x % b + b) % b;
        out.println(x);
    }

    public static int[] exgcd(int a, int b) {
        // {x, x1}
        int x = 1, x1 = 0;
        // {y, y1}
        int y = 0, y1 = 1;
        // {a1, b1}
        int a1 = a, b1 = b;
        int temp;
        while (b1 != 0) {
            int q = a1 / b1;

            // x, x1
            temp = x1;
            x1 = x - q * x1;
            x = temp;

            // y, y1
            temp = y1;
            y1 = y - q * y1;
            y = temp;

            // a1, b1
            temp = b1;
            b1 = a1 - q * b1;
            a1 = temp;
        }
        // {a1, x, y}
        return new int[]{a1, x, y};
    }

    public static int[] exgcdRec(int a, int b) {
        if (b == 0) {
            return new int[]{a, 1, 0};
        }
        int[] res = exgcdRec(b, a % b);
        int g = res[0];
        int x = res[1];
        int y = res[2];
        return new int[]{g, y, x - y * (a / b)};
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