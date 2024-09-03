class SolutionBruteforce {
    public long maxStrength(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        int maskLimit = 1 << n;
        long ans = 0;
        for (int mask = 1; mask < maskLimit; mask++) {
            long strength = 1L;
            for (int i = 0; i < n; i++) {
                if (((mask >> i) & 1) == 0) continue;
                strength *= nums[i];
            }
            ans = Math.max(strength, ans);
        }
        return ans;
    }

    public static void main(String[] args) {
        SolutionBruteforce sol = new SolutionBruteforce();
        int[] nums = {3,-1,-5,2,5,-9};
        System.out.println("test: " + sol.maxStrength(nums));
    }
}