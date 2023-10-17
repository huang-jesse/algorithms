class Solution {
    public int sumOfMultiples(int n) {
        // 容斥原理
        return f(n, 3) + f(n, 5) + f(n, 7) - f(n, 3 * 5) - f(n, 3 * 7) - f(n, 5 * 7) + f(n, 3 * 5 * 7);
    }

    private int f(int n, int m) {
        return (m + n / m * m) * (n / m) / 2;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 7;
        System.out.println("test: " + sol.sumOfMultiples(n));
    }
}