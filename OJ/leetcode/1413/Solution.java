class Solution {
    public int minStartValue(int[] nums) {
        int preSum = 0;
        int minVal = 0;
        for (int num : nums) {
            preSum = preSum + num;
            minVal = Math.min(preSum, minVal);
        }
        return -minVal + 1;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nunms = {-3,2,-3,4,2};
        System.out.println("test: " + sol.minStartValue(nunms));
    }
}