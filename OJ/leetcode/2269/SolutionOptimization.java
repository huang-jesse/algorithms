class SolutionOptimization {
    public int divisorSubstrings(int num, int k) {
        int ans = 0;
        long m = (long)Math.pow(10, k);
        for (int cur = num; cur >= m / 10; cur /= 10) {
            int sub = (int)(cur % m);
            if (sub > 0 && num % sub == 0) {
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        SolutionOptimization sol = new SolutionOptimization();
        int num = 430043;
        int k = 2;
        System.out.println("test: " + sol.divisorSubstrings(num, k));
    }
}