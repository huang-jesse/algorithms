class SolutionOptimization {
    public int punishmentNumber(int n) {
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            int pow = i * i;
            if (check(pow, i)) {
                ans += pow;
            }
        }
        return ans;
    }

    private boolean check(int pow, int num) {
        if (pow == num) {
            return true;
        }
        int d = 10;
        while (pow >= d && pow % d <= num) {
            if (check(pow / d, num - (pow % d))) {
                return true;
            }
            d *= 10;
        }
        return false;
    }

    public static void main(String[] args) {
        SolutionOptimization sol = new SolutionOptimization();
        int n = 37; // 1478
        // int n = 10; // 182
        System.out.println("test: " + sol.punishmentNumber(n));
    }
}