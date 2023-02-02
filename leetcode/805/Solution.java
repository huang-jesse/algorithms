import java.util.HashSet;
import java.util.Set;

class Solution {
    public boolean splitArraySameAverage(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return false;
        }
        int sum = 0;
        for (int i = 0; i < n; i++) {
            nums[i] = nums[i] * n;
            sum += nums[i];
        }
        int mean = sum / n;
        for (int i = 0; i < n; i++) {
            nums[i] = nums[i] - mean;
        }
        // The first half bit manipulation
        Set<Integer> firstHalfSeen = new HashSet<>();
        int firstHalf = n / 2;
        int maskLimit = (1 << firstHalf) - 1;
        for (int mask = 1; mask <= maskLimit; mask++) {
            int tot = 0;
            for (int i = 0; i < firstHalf; i++) {
                if (((mask >> i) & 1) == 1) {
                    tot += nums[i];
                }
            }
            firstHalfSeen.add(tot);
            if (tot == 0) {
                return true;
            }
        }
        int firstHalfSum = 0;
        for (int i = 0; i < firstHalf; i++) {
            firstHalfSum += nums[i];
        }
        // The second half bit manipulation
        int secondHalf = n - firstHalf;
        maskLimit = (1 << secondHalf) - 1;
        for (int mask = 1; mask < maskLimit; mask++) {
            int tot = 0;
            for (int i = firstHalf; i < n; i++) {
                if (((mask >> (i - firstHalf)) & 1) == 1) {
                    tot += nums[i];
                }
            }
            if (tot == 0 || firstHalfSeen.contains(-tot)) {
                return true;
            }
        }
        int secondHalfSum = 0;
        for (int i = firstHalf; i < n; i++) {
            secondHalfSum += nums[i];
        }
        firstHalfSeen.remove(firstHalfSum);
        if (secondHalfSum == 0 || firstHalfSeen.contains(-secondHalfSum)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[] nums = {1,8,15};
        // int[] nums = {1,2,3,4,5,6,7,8};
        int[] nums = {72,56,81,54,15,96,80,90,8};
        System.out.println("test: " + sol.splitArraySameAverage(nums));
    }
}