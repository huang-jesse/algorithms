import java.util.ArrayList;
import java.util.List;

class Solution {
    public int nonSpecialCount(int l, int r) {
        int sqrtR = (int)Math.sqrt(r) + 1;
        boolean[] notPrime = eulerSieve(sqrtR);
        int rCount = specialCount(r, notPrime);
        int lCount = specialCount(l - 1, notPrime);
        int rangeCount = rCount - lCount;
        int res = r - l + 1 - rangeCount;
        return res;
    }

    private int specialCount(int num, boolean[] notPrime) {
        int count = 0;
        for (int i = 2; i * i <= num; i++) {
            if (notPrime[i]) continue;
            count++;
        }
        return count;
    }

    public boolean[] eulerSieve(int n) {
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
        return notPrime;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int l = 4;
        int r = 16; // 11
        System.out.println("test: " + sol.nonSpecialCount(l, r));
    }
}