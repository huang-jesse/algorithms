import java.util.Arrays;

/**
 * 拓展欧几里得算法
 */
public class Exgcd {
    /**
     * 递归版本
     * @param a
     * @param b
     * @return
     */
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
     * 迭代版本
     * @param a
     * @param b
     * @return
     */
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

    public static void main(String[] args) {
        // System.out.println(Arrays.toString(exgcdRec(55, 80)));
        System.out.println(Arrays.toString(exgcd(55, 80)));
    }
}