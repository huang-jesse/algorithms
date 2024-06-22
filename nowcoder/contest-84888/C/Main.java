import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    static Scanner in = new Scanner(System.in); // 输入结果
    static PrintWriter out = new PrintWriter(System.out);   // 输出结果参数
    static final char LEFT = 'L';
    static final char RIGHT = 'R';
    static final char UP = 'U';
    static final char DOWN = 'D';

    /**
     * 主逻辑
     * **/
    public static boolean solve(int[][] points, String s) {
        int n = points.length;
        int m = s.length();
        Map<Integer, List<Integer>> xMap = new HashMap<>();
        Map<Integer, List<Integer>> yMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int x = points[i][0];
            xMap.computeIfAbsent(x, o -> new ArrayList<>()).add(i);
            int y = points[i][1];
            yMap.computeIfAbsent(y, o -> new ArrayList<>()).add(i);
        }
        int[] xArr = xMap.keySet().stream().mapToInt(o -> o).toArray();
        int[] yArr = yMap.keySet().stream().mapToInt(o -> o).toArray();

        boolean[] dp = new boolean[n];
        Arrays.fill(dp, true);
        for (int i = 0; i < m; i++) {
            char cur = s.charAt(i);
            boolean[] temp = new boolean[n];
            if (cur == LEFT || cur == RIGHT) {
                for (int y : yArr) {
                    List<Integer> row = yMap.get(y);
                    int len = row.size();
                    if (len < 2) continue;
                    int step = cur == LEFT ? -1 : 1;
                    int start = cur == LEFT ? len - 2 : 1;
                    int end = cur == LEFT ? -1 : len; // exclusive
                    int j = start;
                    boolean res = false;
                    while (j != end) {
                        int index = row.get(j);
                        int preIndex = row.get(j - step);
                        res = res | dp[preIndex];
                        temp[index] = res;
                        j += step;
                    }
                }
            } else {
                // UP or DOWN
                for (int x : xArr) {
                    List<Integer> cell = xMap.get(x);
                    int len = cell.size();
                    if (len < 2) continue;
                    int step = cur == DOWN ? -1 : 1;
                    int start = cur == DOWN ? len - 2 : 1;
                    int end = cur == DOWN ? -1 : len; // exclusive
                    int j = start;
                    boolean res = false;
                    while (j != end) {
                        int index = cell.get(j);
                        int preIndex = cell.get(j - step);
                        res = res | dp[preIndex];
                        temp[index] = res;
                        j += step;
                    }
                }
            }
            dp = temp;
        }

        for (boolean isYes : dp) {
            if (isYes) return true;
        }
        return false;
    }

    /**
     * 运行主函数
     * */
    public static void main(String[] args) {
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNext()) { // 注意 while 处理多个 case
            int times = nextInt();
            while (times > 0) {
                times--;

                int[] arr = nextIntArray();
                int n = arr[0]; // points count
                int m = arr[1]; // string length
                String s = in.nextLine().trim().substring(0, m);
                int[][] points = new int[n][2];
                for (int i = 0; i < n; i++) {
                    points[i] = nextIntArray();
                }
                Arrays.sort(points, (o1, o2) -> {
                    int first = o1[1] - o2[1];
                    if (first == 0) {
                        return o1[0] - o2[0];
                    }
                    return first;
                });
                boolean isYes = solve(points, s);
                if (isYes) {
                    out.println("YES");
                } else {
                    out.println("NO");
                }
            }
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