class SolutionSieve {
    private final static int MAX = 4_000_000;
    private final static boolean[] isPrime = new boolean[MAX + 1];
    static {
        // 素数筛法
        for (int i = 2; i <= MAX; i++) {
            isPrime[i] = true;
        }
        for (int i = 2; i * i <= MAX; i++) {
            if (!isPrime[i]) {
                continue;
            }
            if (i * i > MAX) {
                break;
            }
            for (int j = i * i; j <= MAX; j += i) {
                // 因为从 2 到 i - 1 的倍数我们之前筛过了，这里直接从 i 的倍数开始，提高了运行速度
                // 是i的倍数的均不是素数
                isPrime[j] = false;
            }
        }
    }
    public int diagonalPrime(int[][] nums) {

        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (isPrime[nums[i][i]]) {
                ans = Math.max(ans, nums[i][i]);
            }
            if (isPrime[nums[i][n - i - 1]]) {
                ans = Math.max(ans, nums[i][n - i - 1]);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        SolutionSieve sol = new SolutionSieve();
        int[][] nums = {{1,2,3},{5,6,7},{9,10,11}}; // ans = 11
        System.out.println("test: " + sol.diagonalPrime(nums));
    }
}