import java.util.Arrays;

/**
 * 倍增构造后缀数组 O(nlog(n)) 时间复杂度
 *
 * <p> 1-indexed
 *
 * <p> sa[i] rk[i] 的值均从 1 开始，即 sa[i], rk[i] 的取值范围为 [1,n]
 */
public class SuffixArrayBinaryLifting {
    // letters and numbers
    private static final int R = 128;
    private final int n;
    private final int[] sa;
    private final int[] oldSa;
    private final int[] rk;
    private final int[] oldRk;
    private final int[] counter;

    public SuffixArrayBinaryLifting(String s) {
        this.n = s.length();
        this.sa = new int[n * 2 + 1];
        this.oldSa = new int[n + 1];
        this.rk = new int[n * 2 + 1];
        this.oldRk = new int[n * 2 + 1];
        this.counter = new int[Math.max(n + 1, R + 1)];
        // 计数排序（单字符）
        for (int i = 1; i <= n; i++) {
            counter[s.charAt(i - 1)]++;
            rk[i] = s.charAt(i - 1);
        }
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
    }

    // length of s
    public int length() {
        return this.n;
    }

    /**
     * returns index of ith sorted suffix
     * <p> 0-indexed
     * @param i
     * @return
     */
    public int index(int i) {
        return this.sa[i] - 1;
    }

    /**
     * returns rank of ith origin suffix
     * <p> 0-indexed
     * @param i
     * @return
     */
    public int rank(int i) {
        return this.rk[i] - 1;
    }
}