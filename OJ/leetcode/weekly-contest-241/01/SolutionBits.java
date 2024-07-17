class SolutionBits {
    public int subsetXORSum(int[] nums) {
        int ans = 0;
        int n = nums.length;
        int sizeOfAllSubsets = 1 << n; // 2 ^ n
        // iteration all of subsets
        for (int i = 1; i < sizeOfAllSubsets; i++) {
            // each subset
            int sumOfEachSubset = 0;
            for (int j = 0; j < n; j++) {
                boolean isThisSubset = ((i >> j) & 1) == 1; // chech if bit of j is '1'
                if (isThisSubset) {
                    sumOfEachSubset ^= nums[j];
                }
            }
            ans += sumOfEachSubset;
        }
        return ans;
    }

    public static void main(String[] args) {
        SolutionBits sol = new SolutionBits();
        int[] nums = new int[]{3,4,5,6,7,8};
        System.out.println("test: " + sol.subsetXORSum(nums));
    }
}