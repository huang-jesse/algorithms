import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class SolutionTwoPointer {
    public int longestEqualSubarray(List<Integer> nums, int k) {
        int n = nums.size();
        // {num, {numIndex_i1, numIndex_i2,..., numIndex_im}}
        Map<Integer, List<Integer>> numIndexesMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            List<Integer> indexes = numIndexesMap.getOrDefault(nums.get(i), new ArrayList<>());
            indexes.add(i);
            numIndexesMap.put(nums.get(i), indexes);
        }
        int ans = 1;
        for (List<Integer> indexes : numIndexesMap.values()) {
            int size = indexes.size();
            int i = 0;
            int j = i + 1;
            while (j < size) {
                int subLen = j - i + 1;
                int startIndex= indexes.get(i);
                int endIndex = indexes.get(j);
                if (endIndex - startIndex + 1 - subLen <= k) {
                    // 找到一个删除 k 个元素后，能够成为连续子数组的情况，更新 ans 移动右指针 j
                    ans = Math.max(ans, subLen);
                    j++;
                } else {
                    // 不能成为连续子数组，则移动左指针 i
                    i++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        SolutionTwoPointer sol = new SolutionTwoPointer();
        List<Integer> nums = Arrays.asList(1,3,2,3,1,3);
        int k = 4;
        // List<Integer> nums = Arrays.asList(1,2,1);
        // int k = 0;
        System.out.println("test: " + sol.longestEqualSubarray(nums, k));
    }
}