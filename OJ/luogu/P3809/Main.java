import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    static FastScanner in = new FastScanner();  // 快读参数
    static PrintWriter out = new PrintWriter(System.out);   // 输出结果参数
    // letters and numbers
    private static final int R = 128;

    /**
     * 主逻辑
     * **/
    public static void solve() {
        String s = in.next();
        int n = s.length();
        int[] sa = new int[n * 2 + 1];
        int[] oldSa = new int[n + 1];
        int[] rk = new int[n * 2 + 1];
        int[] oldRk = new int[n * 2 + 1];
        int[] counter = new int[Math.max(n + 1, R + 1)];
        // 计数排序（单字符）
        for (int i = 1; i <= n; i++) {
            counter[s.charAt(i - 1)]++;
            rk[i] = s.charAt(i - 1);
        };
        for (int i = 1; i < R; i++) counter[i] += counter[i - 1];
        for (int i = n; i >= 1; i--) sa[counter[rk[i]]--] = i;
        System.arraycopy(rk, 0, oldRk, 0, n + 1);
        // 生成 rk （保证字符相同时 rk[i]=rk[j] ）
        for (int i = 1, rank = 0; i <= n; i++) {
            if (oldRk[sa[i]] == oldRk[sa[i - 1]]) {
                rk[sa[i]] = rank;
            } else {
                rk[sa[i]] = ++rank;
            }
        }
        // 倍增进行第1关键字和第2关键字的计数排序
        for (int w = 1; w <= n; w = w << 1) {
            // 对第2关键字 oldsa[i] + w 排序
            Arrays.fill(counter, 0);
            System.arraycopy(sa, 0, oldSa, 0, n + 1);
            for (int i = 1; i <= n; i++) counter[rk[oldSa[i] + w]]++;
            for (int i = 1; i <= n; i++) counter[i] += counter[i - 1];
            for (int i = n; i >= 1; i--) sa[counter[rk[oldSa[i] + w]]--] = oldSa[i];
            // 对第1关键字 oldsa[i] 排序
            Arrays.fill(counter, 0);
            System.arraycopy(sa, 0, oldSa, 0, n + 1);
            for (int i = 1; i <= n; i++) counter[rk[oldSa[i]]]++;
            for (int i = 1; i <= n; i++) counter[i] += counter[i - 1];
            for (int i = n; i >= 1; i--) sa[counter[rk[oldSa[i]]]--] = oldSa[i];
            // 重新计算 rk
            System.arraycopy(rk, 0, oldRk, 0, n + 1);
            for (int i = 1, rank = 0; i <= n; i++) {
                if (oldRk[sa[i]] == oldRk[sa[i - 1]] && oldRk[sa[i] + w] == oldRk[sa[i - 1] + w]) {
                    rk[sa[i]] = rank;
                } else {
                    rk[sa[i]] = ++rank;
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            out.printf("%d ", sa[i]);
        }
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