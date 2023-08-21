import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public int longestEqualSubarray(List<Integer> nums, int k) {
        int n = nums.size();
        // {num, {numIndex_i1, numIndex_i2,..., numIndex_im}}
        Map<Integer, List<Integer>> numIndexesMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            List<Integer> indexes = numIndexesMap.getOrDefault(nums.get(i), new ArrayList<>());
            indexes.add(i);
            numIndexesMap.put(nums.get(i), indexes);
        }
        int low = 1;
        int high = n;
        while (low < high) {
            int mid = low + ((high - low + 1) >> 1);
            if (check(numIndexesMap, k, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    private boolean check(Map<Integer, List<Integer>> numIndexesMap, int k, int subLen) {
        for (List<Integer> indexes : numIndexesMap.values()) {
            int size = indexes.size();
            if (size < subLen) {
                continue;
            }
            int i = 0;
            int j = i + subLen - 1;
            while (j < size) {
                int startIndex= indexes.get(i);
                int endIndex = indexes.get(j);
                if (endIndex - startIndex + 1 - subLen <= k) {
                    // 找到一个删除 k 个元素后，连续子数组长度为 subLen 的情况
                    return true;
                }
                i++;
                j++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // List<Integer> nums = Arrays.asList(1,3,2,3,1,3);
        // int k = 4;
        List<Integer> nums = Arrays.asList(1,2,1);
        int k = 0;
        System.out.println("test: " + sol.longestEqualSubarray(nums, k));
    }
}