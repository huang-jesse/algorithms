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
        long target = in.nextLong();
        long mid = target / 2;
        int numOfDigit = digitsCount(mid);
        long lowBound = createDigitsNum(5, numOfDigit);
        // long highBound = Math.min(createDigitsNum(9, numOfDigit), target);
        if (target <= lowBound) {
            // no
            out.println("NO");
            return;
        }

        boolean[][] dp = new boolean[numOfDigit + 1][2];
        dp[0][0] = true;
        dp[0][1] = false;
        long num = 1;
        for (int i = 1; i <= numOfDigit; i++) {
            // int targetTwoDigit = (int)((target / num) % 100);
            // int currentDigit = targetTwoDigit % 10;
            // int nextDigit = targetTwoDigit / 10;
            int currentDigit = (int)((target / num) % 10);
            int targetTwoDigit = 10 + currentDigit;
            for (int digit = 5; digit < 10; digit++) {
                if (currentDigit - digit >= 5) {
                    dp[i][0] |= dp[i - 1][0];
                }
                if (targetTwoDigit >= 10 && targetTwoDigit - digit >= 5 && targetTwoDigit - digit <= 9) {
                    dp[i][1] |= dp[i - 1][0];
                }

                if (currentDigit - 1 - digit >= 5) {
                    dp[i][0] |= dp[i - 1][1];
                }
                if (targetTwoDigit - 1 >= 10 && targetTwoDigit - 1 - digit >= 5 && targetTwoDigit - 1 - digit <= 9) {
                    dp[i][1] |= dp[i - 1][1];
                }
            }
            if (!dp[i][0] && !dp[i][1]) {
                // NO
                break;
            }
            num *= 10;
        }
        int targetDigitCount = digitsCount(target);
        if ((targetDigitCount == numOfDigit && dp[numOfDigit][0]) || (targetDigitCount > numOfDigit && dp[numOfDigit][1])) {
            // YES
            out.println("YES");
        } else {
            out.println("NO");
        }
    }

    private static long createDigitsNum(int digit, int numOfDigit) {
        long num = 0;
        for (int i = 0; i < numOfDigit; i++) {
            num = num * 10 + digit;
        }
        return num;
    }

    private static int digitsCount(long num) {
        int numOfDigit = 0;
        while (num > 0) {
            num /= 10;
            numOfDigit++;
        }
        return numOfDigit;
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