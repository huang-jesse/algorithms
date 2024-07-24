import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    static FastScanner in = new FastScanner();  // 快读参数
    static PrintWriter out = new PrintWriter(System.out);   // 输出结果参数
    private static final int MOD = (int)(1e9 + 7);

    /**
     * 主逻辑
     * **/
    public static void solve() {
        int a = in.nextInt(), b = in.nextInt(), n = in.nextInt();
        int[] fact = new int[n + 1];
        fact[0] = 1;
        for (int i = 1; i <= n; i++) {
            fact[i] = longMod((long)fact[i - 1] * i, MOD);
        }
        int res = 0;
        for (int k = 0; k <= n; k++) {
            int num = a * k + b * (n - k);
            if (isExcellentNum(num, a, b)) {
                res += calCombination(fact, n, k);
                res = res % MOD;
            }
        }
        out.println(res);
    }

    private static boolean isExcellentNum(int num, int a, int b) {
        while (num > 0) {
            if (num % 10 == a || num % 10 == b) {
                num /= 10;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 计算组合数 C[n][k]
     * @param fact
     * @param n
     * @param k
     * @return
     */
    private static int calCombination(int[] fact, int n, int k) {
        // combination = fact[n] * inv(fact[n - k] * fact[k])
        return longMod((long)fact[n] * inverse(longMod((long)fact[n - k] * fact[k], MOD), MOD), MOD);
    }

    private static int longMod(long a, int m) {
        return (int)(a % m);
    }

    /**
     * exgcd 求逆元，当且仅当 gcd(a,m) = 1 时才有解。
     * @param a
     * @param m
     * @return
     */
    public static int inverse(int a, int m) {
        int[] res = exgcd(a, m);
        int gcd = res[0];
        int x = res[1];
        if (gcd != 1) {
            // No solution
            return -1;
        } else {
            return (x % m + m) % m;
        }
    }

    public static int[] exgcd(int a, int b) {
        if (b == 0) {
            return new int[]{a, 1, 0};
        }
        int[] res = exgcd(b, a % b);
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