import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public int longestEqualSubarray(List<Integer> nums, int k) {
        int n = nums.size();
        // {num, {num_index1, num_index2...num_indexi}}
        Map<Integer, List<Integer>> indexesMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int num = nums.get(i);
            List<Integer> indexes = indexesMap.getOrDefault(num, new ArrayList<>());
            indexes.add(i);
            indexesMap.putIfAbsent(num, indexes);
        }

        int ans = 1;
        for (List<Integer> indexes : indexesMap.values()) {
            int size = indexes.size();
            if (size <= ans) continue;
            int i = 0;
            int j = i + 1;
            while (j < size) {
                int subLen = j - i + 1;
                if (indexes.get(j) - indexes.get(i) + 1 - subLen <= k) {
                    // valid
                    ans = Math.max(ans, subLen);
                    j++;
                } else {
                    // invalid
                    i++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        List<Integer> nums = Arrays.asList(1,3,2,3,1,3);
        int k = 3;
        // List<Integer> nums = Arrays.asList(4,4,2,2,4);
        // int k = 1;
        System.out.println("test: " + sol.longestEqualSubarray(nums, k));
    }
}