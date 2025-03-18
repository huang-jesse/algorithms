class Solution {
    public int diagonalPrime(int[][] nums) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int diagonalNum = nums[i][i];
            if (diagonalNum > ans && isPrime(diagonalNum)) {
                ans = diagonalNum;
            }
            diagonalNum = nums[i][n - i - 1];
            if (diagonalNum > ans && isPrime(diagonalNum)) {
                ans = diagonalNum;
            }
        }
        return ans;
    }

    private boolean isPrime(int num) {
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return num >= 2; // 1不是质数
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] nums = {{1,2,3},{5,6,7},{9,10,11}}; // ans = 11
        System.out.println("test: " + sol.diagonalPrime(nums));
    }
}