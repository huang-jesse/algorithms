import java.util.TreeMap;

class Solution {
    public int sumOfBeauties(int[] nums) {
        int n = nums.length;
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for (int i = 1; i < n; i++) {
            Integer count = tm.getOrDefault(nums[i], 0);
            tm.put(nums[i], count+1);
        }
        int maxOfLeft = nums[0];
        int ans = 0;
        for (int i = 1; i < n-1; i++) {
            int current = nums[i];
            int count = tm.getOrDefault(current, 0);
            tm.put(current, count-1);
            if (count <= 1) {
                tm.remove(current);
            }

            int minOfRight = tm.firstKey();
            if (current > maxOfLeft && current < minOfRight) {
                ans += 2;
            } else if (current > nums[i-1] && current < nums[i+1]) {
                ans += 1;
            }
            maxOfLeft = Math.max(current, maxOfLeft);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1,2,3,4,5,7,8,9,10};
        System.out.println("test: " + sol.sumOfBeauties(nums));
    }
}