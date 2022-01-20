class Solution {
    public int dominantIndex(int[] nums) {
        int n = nums.length;
        if (n <= 1) {
            return 0;
        }
        int first = 0;
        int index = 0;
        int second = 0;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            if (num > first) {
                second = first;
                first = num;
                index = i;
            } else if (num > second) {
                second = num;
            }
        }
        if ((first >> 1) >= second) {
            return index;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[] nums = {1,2,3,4};
        int[] nums = {3,6,1,0};
        System.out.println("test: " + sol.dominantIndex(nums));
    }
}