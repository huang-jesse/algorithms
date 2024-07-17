class Solution {
    public int countQuadruplets(int[] nums) {
        int n = nums.length;
        int ans = 0;
        for (int d = 3; d < n; d++) {
            int dVal = nums[d];
            for (int a = 0; a <= d-3; a++) {
                int aVal = nums[a];
                for (int b = a+1; b <= d-2; b++) {
                    int bVal = nums[b];
                    for (int c = b+1; c <= d-1; c++) {
                        int cVal = nums[c];
                        if (aVal+bVal+cVal == dVal) {
                            ans++;
                        }
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {28,8,49,85,37,90,20,8};
        System.out.println("test: " + sol.countQuadruplets(nums));
    }
}