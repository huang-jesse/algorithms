import java.util.Arrays;

public class LinearInverse {
    private final int a;
    private final int m;
    private final int[] inverseArr;
    public LinearInverse(int a, int m) {
        this.a = a;
        this.m = m;
        this.inverseArr = new int[a + 1];
        Arrays.fill(this.inverseArr, -1);
    }

    private static int mod(int a, int m) {
        return (a % m + m) % m;
    }

    private static int longMod(long a, int m) {
        return (int)(a % m);
    }

    /**
     * 线性求逆元，计算 [1,2,...,a] 中所有数的逆元；
     * <p> 递归实现，用 inverseArr[] 进行记忆化，因此总复杂度为 O(n)
     * @param a
     * @param m
     * @return
     */
    public int[] inverseAll() {
        for (int i = 1; i <= a; i++) {
            if (inverseArr[i] != -1) continue;
            inverse(i, m);
        }
        return this.inverseArr;
    }

    /**
     * 递归计算单个逆元
     * @param a
     * @param m
     * @return
     */
    private int inverse(int a, int m) {
        if (a <= 1) {
            inverseArr[a] = a;
            return a;
        }
        inverseArr[a] = mod(longMod((long)(m - m / a) * (inverse(mod(m, a), m)), m), m);
        return inverseArr[a];
    }

    public static void main(String[] args) {
        int a = 10;
        int m = 13;
        LinearInverse linearInverse = new LinearInverse(a, m);
        linearInverse.inverseAll();
        System.out.println(Arrays.toString(linearInverse.inverseArr));
    }
}