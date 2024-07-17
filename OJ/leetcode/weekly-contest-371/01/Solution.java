class Solution {
    public int maximumStrongPairXor(int[] nums) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int firstNum = nums[i];
            for (int j = i + 1; j < n; j++) {
                int secondNum = nums[j];
                if (Math.abs(firstNum - secondNum) <= Math.min(firstNum, secondNum)) {
                    ans = Math.max(ans, firstNum ^ secondNum);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {5,6,25,30};
        System.out.println("test: " + sol.maximumStrongPairXor(nums));
    }
}