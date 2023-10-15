class Solution {
    public long findTheArrayConcVal(int[] nums) {
        long ans = 0;
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        while (left <= right) {
            if (left == right) {
                ans += nums[left];
            } else {
                int rightNum = nums[right];
                int weight = 1;
                while (rightNum > 0) {
                    rightNum /= 10;
                    weight *= 10;
                }
                ans += nums[left] * weight + nums[right];
            }
            left++;
            right--;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {5,14,13,8,12};
        System.out.println("test: " + sol.findTheArrayConcVal(nums));
    }
}