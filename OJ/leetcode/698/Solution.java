import java.util.Arrays;
import java.util.Comparator;

class Solution {
    private int usedMask = 0;
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();
        if (sum % k != 0) {
            return false;
        }
        int target = sum / k;
        nums = Arrays.stream(nums).boxed().sorted(Comparator.reverseOrder()).mapToInt(num -> num).toArray();
        for (int i = 0; i < k; i++) {
            if (!backtrack(nums, 0, target, 0)) {
                return false;
            }
        }
        return true;
    }

    private boolean backtrack(int[] nums, int index, int target, int visitedMask) {
        int n = nums.length;
        if (index == n) {
            if (target == 0) {
                usedMask = usedMask | visitedMask;
                System.out.println(Integer.toBinaryString(visitedMask));
                return true;
            } else {
                return false;
            }
        }
        // unvisited
        boolean res = backtrack(nums, index + 1, target, visitedMask);
        if (res) {
            return res;
        } else if (!isVisited(visitedMask, index) && !isVisited(usedMask, index)) {
            if (target >= nums[index]) {
                // visited
                visitedMask = visitedMask | (1 << index);
                res = backtrack(nums, index + 1, target - nums[index], visitedMask);
                // clear mask
                visitedMask = visitedMask ^ (1 << index);
            }
        }
        return false;
    }

    private boolean isVisited(int mask, int index) {
        return ((mask >> index) & 1) == 1;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[] nums = {2,2,2,2,3,4,5};
        // int k = 4;
        int[] nums = {9,10,1,7,2,7,1,1,1,3};
        int k = 3;
        System.out.println("test: " + sol.canPartitionKSubsets(nums, k));
    }
}