class SolutionOptimization {
    public long minimumCost(String s) {
        int n = s.length();
        long ans = 0;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) != s.charAt(i - 1)) {
                ans += Math.min(i, n - i);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        SolutionOptimization sol = new SolutionOptimization();
        String s = "010101";
        System.out.println("test: " + sol.minimumCost(s));
    }
}