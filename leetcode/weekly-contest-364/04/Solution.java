import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    private List<Integer>[] graph;
    public long countPaths(int n, int[][] edges) {
        this.graph = new ArrayList[n];
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        Set<Integer> primes = eratosthenes(n);

        return 0;
    }

    public Set<Integer> eratosthenes(int n) {
        boolean[] isPrime = new boolean[n + 1];
        for (int i = 2; i <= n; i++) {
            isPrime[i] = true;
        }
        for (int i = 2; i * i <= n; i++) {
            if (!isPrime[i]) {
                continue;
            }
            if ((long)i * i > n) {
                break;
            }
            for (int j = i * i; j <= n; j += i) {
                // 因为从 2 到 i - 1 的倍数我们之前筛过了，这里直接从 i 的倍数开始，提高了运行速度
                // 是i的倍数的均不是素数
                isPrime[j] = false;
            }
        }
        // 收集所有标记为素数的数
        Set<Integer> primes = new HashSet<>();
        for (int i = 0; i <= n; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }
        return primes;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 0;
        int[][] edges = {};
        System.out.println("test: " + sol.countPaths(n, edges));
    }
}