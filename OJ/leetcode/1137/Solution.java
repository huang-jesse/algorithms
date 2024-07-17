class Solution {
    int[] triArr;
    public int tribonacci(int n) {
        if (n == 0)
            return 0;
        if (n == 1 || n == 2)
            return 1;
        triArr = new int[n+1];
        triArr[0] = 0;
        triArr[1] = 1;
        triArr[2] = 1;
        return recursiveTri(n);
    }

    private int recursiveTri(int n) {
        if (n < 3 || triArr[n] != 0)
            return triArr[n];
        int ans = recursiveTri(n-3) + recursiveTri(n-2) + recursiveTri(n-1);
        triArr[n] = ans;
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 4;
        System.out.println("test: " + sol.tribonacci(n));
    }
}