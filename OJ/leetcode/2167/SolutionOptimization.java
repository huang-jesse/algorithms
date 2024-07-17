class SolutionOptimization {
    public int minimumTime(String s) {
        int n = s.length();
        int pre = 0;
        int ans = n;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                // opteration 1 or operation 3
                pre = Math.min(i + 1, pre + 2);
            }
            ans = Math.min(ans, pre + (n - 1 - i));
        }
        return ans;
    }

    public static void main(String[] args) {
        SolutionOptimization sol = new SolutionOptimization();
        String s = "1100101"; // 5
        System.out.println("test: " + sol.minimumTime(s));
    }
}