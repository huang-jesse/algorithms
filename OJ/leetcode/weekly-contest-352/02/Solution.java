import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
    public List<List<Integer>> findPrimePairs(int n) {
        List<Integer> primeList = findPrimes(n);
        Set<Integer> primeSet = primeList.stream().collect(Collectors.toSet());
        List<List<Integer>> ans = new ArrayList<>();
        for (Integer prime : primeList) {
            if (primeSet.contains(n - prime)) {
                ans.add(Arrays.asList(prime, n - prime));
            }
            primeSet.remove(prime);
        }
        return ans;
    }

    public List<Integer> findPrimes(int n) {
        boolean[] isPrime = new boolean[n + 1];
        List<Integer> primes = new ArrayList<>();

        // 初始化isPrime数组，假设所有数都为素数
        for (int i = 2; i <= n; i++) {
            isPrime[i] = true;
        }

        // 使用埃拉托斯特尼筛选法，从2开始标记所有的合数
        for (int i = 2; i * i <= n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        // 收集所有标记为素数的数
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }

        return primes;
    }

    // private boolean isPrime(int num) {
    //     if (num < 2) {
    //         return false;
    //     }
    //     for (int i = 2; i * i <= num; i++) {
    //         if (num % i == 0) {
    //             return false;
    //         }
    //     }
    //     return true;
    // }

    // /**
    //  * @param num an integer
    //  * @return an integer array
    //  */
    // public List<Integer> primeFactorization(int num) {
    //     List<Integer> factors = new ArrayList<Integer>();
    //     for (int i = 2; i * i <= num; i++) {
    //         while (num % i == 0) {
    //             num = num / i;
    //             factors.add(i);
    //         }
    //     }
    //     //若最后剩余数不为1，则为最后一个质因数
    //     if (num != 1) {
    //         factors.add(num);
    //     }
    //     return factors;
    // }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 10;
        System.out.println("test: " + sol.findPrimePairs(n));
    }
}