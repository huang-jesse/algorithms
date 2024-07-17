import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

class Solution {
    static final int MOD = (int)1e9 + 7;
    public int sumOfFlooredPairs(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int ans = 0;
        TreeMap<Integer, Integer> countMap = new TreeMap<>();
        Map<Integer, Integer> endIndexMap = new HashMap<>();
        for (int i = 0; i< n; i++) {
            endIndexMap.put(nums[i], i);
            countMap.put(nums[i], countMap.getOrDefault(nums[i], 0) + 1);
        }

        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            int target = nums[i];
            int count = countMap.get(target);
            int tempSum = 0;
            int j = i;
            while (j < n) {
                int cur = nums[j];
                int quotient = cur / target;
                int nextLimit = target * (quotient + 1);
                int next = countMap.floorKey(nextLimit-1);
                int endIndex = endIndexMap.get(next);
                int range = endIndex - j + 1;
                tempSum = (int)(((long)tempSum + ((long)count * (long)quotient * (long)range)) % MOD);

                j = endIndex + 1;
            }
            ans = (ans + tempSum) % MOD;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = new int[]{7,7,7,7,7,7,7};
        // int[] nums = new int[]{4,1,1,2,4};
        System.out.println("test: " + sol.sumOfFlooredPairs(nums));
    }
}