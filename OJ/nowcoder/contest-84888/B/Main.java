import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    static final int INF = (int)1e9 * 2 + 1;
    static Scanner in = new Scanner(System.in); // 输入结果
    static PrintWriter out = new PrintWriter(System.out);   // 输出结果参数

    /**
     * 主逻辑
     * **/
    public static long solve(int[][] points1, int[][] points2) {
        // (x + y)
        long max1 = 0;
        long min1 = INF;
        // (x - y)
        long max2 = 0;
        long min2 = INF;
        for (int[] point : points1) {
            max1 = Math.max(max1, point[0] + point[1]);
            min1 = Math.min(min1, point[0] + point[1]);
            max2 = Math.max(max2, point[0] - point[1]);
            min2 = Math.min(min2, point[0] - point[1]);
        }

        long ans = 0;
        for (int[] point : points2) {
            long xy1 = point[0] + point[1];
            long xy2 = point[0] - point[1];
            long xyDs1 = Math.max(Math.abs(xy1 - max1), Math.abs(xy1 - min1));
            long xyDs2 = Math.max(Math.abs(xy2 - max2), Math.abs(xy2 - min2));
            ans = Math.max(ans, Math.max(xyDs1, xyDs2));
        }
        return ans;
    }

    /**
     * 运行主函数
     * */
    public static void main(String[] args) {
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNext()) { // 注意 while 处理多个 case
            int[] arr = nextIntArray();
            int n = arr[0];
            int m = arr[1];
            int[][] points1 = new int[n][2];
            for (int i = 0; i < n; i++) {
                points1[i] = nextIntArray();
            }
            int[][] points2 = new int[m][2];
            for (int i = 0; i < m; i++) {
                points2[i] = nextIntArray();
            }
            long distance = solve(points1, points2);
            out.println(distance);
        }
        in.close();
        out.close();
    }

    public static String[] nextNLines(int n) {
        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextLine();
        }
        return arr;
    }

    public static int nextInt() {
        return Integer.parseInt(in.nextLine());
    }

    public static int[] nextIntArray() {
        return getIntArray(in.nextLine());
    }

    private static int[] getIntArray(String line) {
        StringTokenizer st = new StringTokenizer(line);
        int n = st.countTokens();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        return a;
    }
}