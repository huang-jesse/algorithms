class Solution {
    public int getMinDistance(int[] nums, int target, int start) {
        int res = (int)Math.pow(10, 4);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                res = Math.min(res, Math.abs(i-start));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = new int[]{};
        int target = 0;
        int start = 0;
        System.out.println("test: " + sol.getMinDistance(nums, target, start));
    }
}