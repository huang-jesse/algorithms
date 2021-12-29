class Solution {
    public int countQuadruplets(int[] nums) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n-3; i++) {
            int num1 = nums[i];
            for (int j = i+1; j < n-2; j++) {
                int num2 = nums[j];
                for (int k = j+1; k < n-1; k++) {
                    int num3 = nums[k];
                    for (int l = k+1; l < n; l++) {
                        int num4 = nums[l];
                        if (num1 + num2 + num3  == num4) {
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
        int[] nums = {1,1,1,3,5};
        // int[] nums = {28,8,49,85,37,90,20,8};
        System.out.println("test: " + sol.countQuadruplets(nums));
    }
}