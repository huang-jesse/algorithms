class SolutionMath {
    public int trailingZeroes(int n) {
        int ans = 0;
        while (n != 0) {
            ans = ans + n / 5;
            n /= 5;
        }
        return ans;
    }

    public static void main(String[] args) {
        SolutionMath sol = new SolutionMath();
        int n = 10;
        System.out.println("test: " + sol.trailingZeroes(n));
    }
}