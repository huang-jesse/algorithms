import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    static FastScanner in = new FastScanner();  // 快读参数
    static PrintWriter out = new PrintWriter(System.out);   // 输出结果参数
    static final String YES = "YES";
    static final String NO = "NO";
    static final int MAXNUM = (int)(Math.sqrt(1e9)) + 5;
    static final List<Integer> primes = eulerSieve(MAXNUM);

    /**
     * 主逻辑
     * **/
    public static void solve() {
        int n = in.nextInt();
        int[] a = in.readIntArray(n);
        int m = primes.size();
        Set<Integer> factors = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m && primes.get(j) <= a[i]; j++) {
                int p = primes.get(j);
                if (a[i] % p != 0) continue;
                while (a[i] % p == 0) a[i] /= p;
                if (!factors.add(p)) {
                    out.println(YES);
                    return;
                }
            }
            if (a[i] != 1 && !factors.add(a[i])) {
                out.println(YES);
                return;
            }
        }
        out.println(NO);
    }

    private static List<Integer> eulerSieve(int n) {
        List<Integer> primes = new ArrayList<>();
        boolean[] notPrime = new boolean[n + 1];
        for (int i = 2; i <= n; ++i) {
            if (!notPrime[i]) {
                primes.add(i);
            }
            for (int p : primes) {
                if (i * p > n) break;
                notPrime[i * p] = true;
                if (i % p == 0) {
                    // i % p == 0
                    // 换言之，i 之前被 p 筛过了
                    // 由于 primes 里面质数是从小到大的，所以 i 乘上其他的质数的结果一定会被
                    // p 的倍数筛掉，就不需要在这里先筛一次，所以这里直接 break 掉就好了
                    break;
                }
            }
        }
        return primes;
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