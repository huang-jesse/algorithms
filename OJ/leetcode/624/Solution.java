import java.util.Arrays;
import java.util.List;

class Solution {
    public int maxDistance(List<List<Integer>> arrays) {
        int ans = 0;
        int min = Integer.MAX_VALUE >> 1;
        int max = Integer.MIN_VALUE >> 1;
        for (List<Integer> curArr : arrays) {
            int curMin = curArr.get(0);
            int curMax = curArr.get(curArr.size() - 1);
            ans = Math.max(ans, Math.max(max - curMin, curMax - min));
            min = Math.min(min, curMin);
            max = Math.max(max, curMax);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        List<Integer> arr1 = Arrays.asList(1,2,3);
        List<Integer> arr2 = Arrays.asList(1,2,3);
        List<Integer> arr3 = Arrays.asList(4,5);
        List<List<Integer>> arrays = Arrays.asList(arr1, arr2, arr3);
        System.out.println("test: " + sol.maxDistance(arrays)); // ans = 4
    }
}