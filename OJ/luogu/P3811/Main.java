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
        int n = in.nextInt(), m = in.nextInt();
        linearInverse(n, m);
    }

    /**
     * 线性求逆元，计算 [1,2,...,a] 中所有数的逆元；
     * <p> 迭代实现，总复杂度为 O(n)
     * @param a
     * @param m
     * @return
     */
    public static void linearInverse(int a, int m) {
        int[] inverseArr = new int[a + 1];
        inverseArr[1] = 1;
        for (int i = 2; i <= a; i++) {
            inverseArr[i] = mod(longMod((long)(m - (m / i)) * inverseArr[m % i], m), m);
        }
        for (int i = 0; i < a; i++) {
            out.println(inverseArr[i + 1]);
        }
    }

    // private static int mod(int a, int m) {
    //     return (a % m + m) % m;
    // }
    private static int mod(int a, int m) {
        return a % m;
    }

    private static int longMod(long a, int m) {
        return (int)(a % m);
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