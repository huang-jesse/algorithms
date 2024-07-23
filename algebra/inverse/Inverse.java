import java.util.Arrays;

/**
 * 乘法逆元
 */
public class Inverse {

    /**
     * exgcd 求逆元，当且仅当 gcd(a,m) = 1 时才有解
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
     * 快速幂（费马小定理） 求逆元，当且仅当 m 为质数时才有解。
     * @param a
     * @param m
     * @return
     */
    public static int inverseQpow(int a, int m) {
        return qpow(a, m - 2, m);
    }

    public static int qpow(int a, int p, int m) {
        int ans = 1;
        while (p > 0) {
            if ((p & 1) == 1) {
                ans = (int)(((long)ans * a) % m);
            }
            a = (int)(((long)a * a) % m);
            p >>= 1;
        }
        return ans;
    }

    /**
     * 线性求逆元，计算 [1,2,...,a] 中所有数的逆元；
     * <p> 迭代实现，总复杂度为 O(n)
     * @param a
     * @param m
     * @return
     */
    public static int[] linearInverse(int a, int m) {
        int[] inverseArr = new int[a + 1];
        inverseArr[1] = 1;
        for (int i = 2; i <= a; i++) {
            inverseArr[i] = mod(longMod((long)(m - (m / i)) * inverseArr[m % i], m), m);
        }
        return inverseArr;
    }

    private static int mod(int a, int m) {
        return (a % m + m) % m;
    }

    /**
     * 线性求逆元，计算数组 a[i] 中所有数的逆元；
     * <p> 总复杂度为 O(n)
     * @param a
     * @param m
     * @return
     */
    public static int[] linearInverseArray(int[] a, int m) {
        int n = a.length;
        int[] res = new int[n];
        int[] prefix = new int[n + 1];
        prefix[0] = 1;
        for (int i = 1; i <= n; i++) {
            prefix[i] = longMod((long)prefix[i - 1] * a[i - 1], m);
        }
        int[] invInfo = exgcd(prefix[n], m);
        int invAll = (invInfo[1] % m + m) % m; // x
        int current = invAll;
        for (int i = n - 1; i >= 0; i--) {
            res[i] = longMod((long)prefix[i] * current, m);
            current = longMod((long)current * a[i], m);
        }
        return res;
    }

    private static int longMod(long a, int m) {
        return (int)(a % m);
    }

    public static void main(String[] args) {
        // System.out.println(inverse(3, 7)); // 5
        System.out.println(inverseQpow(3, 7)); // 5
        int[] inverseArr = linearInverse(10, 13);
        // int[] arr = {1,2,3,4,5,6,7,8,9,10};
        // int[] inverseArr = linearInverseArray(arr, 13);
        System.out.println(Arrays.toString(inverseArr));
    }
}