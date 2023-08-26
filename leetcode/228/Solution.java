import java.util.ArrayList;
import java.util.List;

class Solution {
    private static final String SEPARATOR = "->";
    public List<String> summaryRanges(int[] nums) {
        List<String> ans = new ArrayList<>();
        int n = nums.length;
        if (n == 0) {
            return ans;
        }
        int i = 0;
        while (i < n) {
            int low = i;
            i++;
            while (i < n && nums[i - 1] + 1 == nums[i]) {
                i++;
            }
            int high = i - 1;
            StringBuilder range = new StringBuilder(Integer.toString(nums[low]));
            if (high > low) {
                range.append(SEPARATOR);
                range.append(nums[high]);
            }
            ans.add(range.toString());
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {0,2,3,4,6,8,9};
        System.out.println("test: " + sol.summaryRanges(nums));
    }
}