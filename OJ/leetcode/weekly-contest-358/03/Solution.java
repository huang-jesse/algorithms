import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

class Solution {
    public int minAbsoluteDifference(List<Integer> nums, int x) {
        TreeSet<Integer> ts = new TreeSet<>();
        int n = nums.size();
        int ans = Integer.MAX_VALUE;
        for (int i = n - 1; i - x >= 0; i--) {
            int curNum = nums.get(i);
            ts.add(curNum);
            int preNum = nums.get(i - x);
            int res = Integer.MAX_VALUE;
            Integer lowerBound = ts.floor(preNum);
            if (lowerBound != null) {
                res = Math.min(preNum - lowerBound, res);
            }
            Integer upperBound = ts.ceiling(preNum);
            if (upperBound != null) {
                res = Math.min(upperBound - preNum, res);
            }
            ans = Math.min(ans, res);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        List<Integer> nums = Arrays.asList(5,3,2,10,15);
        int x = 1;
        // List<Integer> nums = Arrays.asList(4,3,2,4);
        // int x = 2;
        System.out.println("test: " + sol.minAbsoluteDifference(nums, x));
    }
}