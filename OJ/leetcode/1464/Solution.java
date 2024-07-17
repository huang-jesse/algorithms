class Solution {
    public int maxProduct(int[] nums) {
        int first = 0;
        int second = 0;
        for (int num : nums) {
            if (num > first) {
                second = first;
                first = num;
            } else if (num > second) {
                second = num;
            }
        }
        return (first - 1) * (second - 1);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1,5,4,5};
        System.out.println("test: " + sol.maxProduct(nums));
    }
}