import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    static FastScanner in = new FastScanner();  // 快读参数
    static PrintWriter out = new PrintWriter(System.out);   // 输出结果参数
    private static final char LETTER_A = 'A';
    private static final char QUESTION_MARK = '?';
    private static final char SPACE = ' ';
    private static final char LARGE_THAN = '>';
    private static final char LESS_THAN = '<';

    /**
     * 主逻辑
     * **/
    public static void solve() {
        int n = in.nextInt(), q = in.nextInt();
        char[] letters = new char[n];
        for (int i = 0; i < n; i++) letters[i] = (char)(LETTER_A + i);
        // Comparator<Character> compare = (o1, o2) -> {
        //     // question
        //     out.printf("? %s %s", o1, o2);
        //     out.flush();
        //     String ans = in.next();
        //     if (ans.charAt(0) == LARGE_THAN) {
        //         return 1;
        //     } else {
        //         return -1;
        //     }
        // };
        // Arrays.sort(letters, compare);
        QuickOriginal.sort(letters);
        // StringBuilder sb = new StringBuilder();
        // for (char cur : letters) {
        //     sb.append(cur);
        // }
        out.printf("! %s", new String(letters));
        out.flush();
    }

    /**
     * QuickSort
     */
    static class QuickOriginal {
        public static void sort(char[] a) {
            // sort
            sort(a, 0, a.length-1);
        }

        public static void sort(char[] a, int lo, int hi) {
            if (lo >= hi) {
                return;
            }
            int pivot = partition(a, lo, hi);
            // sort pivot left
            sort(a, lo, pivot-1);
            // sort pivot right
            sort(a, pivot+1, hi);
        }

        private static int partition(char[] a, int lo, int hi) {
            char pivot = a[lo];
            int i = lo;
            int j = hi + 1;
            while(true) {
                // move to right
                while(less(a[++i], pivot)) if (i >= hi) break;
                // move to left
                while(less(pivot, a[--j]));
                // if i metting j
                if (i >= j) break;
                // exchange
                // when i great or equals the pivot
                // and j less or equals the pivot
                exch(a, i, j);
            }
            exch(a, lo, j);
            // return the pivot index
            return j;
        }

        private static boolean less(char v, char w) {
            if (v == w) return false;
            // question
            out.printf("? %s %s", v, w);
            out.flush();
            String ans = in.next();
            if (ans.charAt(0) == LESS_THAN) {
                return true;
            } else {
                return false;
            }
        }

        private static void exch(char[] a, int i, int j) {
            char t = a[i];
            a[i] = a[j];
            a[j] = t;
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