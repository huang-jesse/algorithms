class SolutionOptimization {
    public int alternateDigitSum(int n) {
        int ans = 0;
        int sign = 1;
        while (n > 0) {
            ans += (n % 10) * sign;
            n /= 10;
            sign = -sign;
        }
        return -sign * ans;
    }

    public static void main(String[] args) {
        SolutionOptimization sol = new SolutionOptimization();
        int n = 521;
        System.out.println("test: " + sol.alternateDigitSum(n));
    }
}