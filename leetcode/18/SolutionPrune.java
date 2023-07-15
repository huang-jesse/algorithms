import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class SolutionPrune {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        for (int first = 0; first < n - 3; first++) {
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            if ((long)nums[first] + nums[first + 1] + nums[first + 2] + nums[first + 3] > target) {
                break;
            }
            if ((long)nums[first] + nums[n - 3] + nums[n - 2] + nums[n - 1] < target) {
                continue;
            }
            for (int second = first + 1; second < n - 2; second++) {
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                if ((long)nums[first] + nums[second] + nums[second + 1] + nums[second + 2] > target) {
                    break;
                }
                if ((long)nums[first] + nums[second] + nums[n - 2] + nums[n - 1] < target) {
                    continue;
                }

                int third = second + 1;
                int fourth = n - 1;
                while (third < fourth) {
                    long currentSum = (long)nums[first] + nums[second] + nums[third] + nums[fourth];
                    if (currentSum == target) {
                        ans.add(Arrays.asList(nums[first], nums[second], nums[third], nums[fourth]));
                        while (third < fourth && nums[third] == nums[third + 1]) {
                            third++;
                        }
                        while (third < fourth && nums[fourth] == nums[fourth - 1]) {
                            fourth--;
                        }
                        third++;
                        fourth--;
                    } else if (currentSum < target) {
                        third++;
                    } else {
                        // current > target
                        fourth--;
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        SolutionPrune sol = new SolutionPrune();
        int[] nums = {1,0,-1,0,-2,2};
        int target = 0;
        // int[] nums = {1000000000,1000000000,1000000000,1000000000};
        // int target = -294967296;
        System.out.println("test: " + sol.fourSum(nums, target));
    }
}