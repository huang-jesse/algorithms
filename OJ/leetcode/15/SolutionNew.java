import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class SolutionNew {
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < n - 2; i++) {
            if(nums[i] > 0) {
                // There is no more answer exist
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int l = i + 1;
            int r = n - 1;
            while (l < r) {
                int curSum = nums[i] + nums[l] + nums[r];
                if (curSum == 0) {
                    ans.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    // Distinct
                    while (l < r && nums[l] == nums[l + 1]) {
                        l++;
                    }
                    while (l < r && nums[r] == nums[r - 1]) {
                        r--;
                    }
                    l++;
                    r--;
                } else if (curSum > 0) {
                    r--;
                } else {
                    // curSum < 0
                    l++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        SolutionNew sol = new SolutionNew();
        int[] nums = {0,0,0,0};
        // int[] nums = {-1,0,1,2,-1,-4};
        System.out.println("test: " + sol.threeSum(nums));
    }
}