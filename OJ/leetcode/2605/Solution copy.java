import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

class Solution {
    public int minNumber(int[] nums1, int[] nums2) {
        HashSet<Integer> nums1Set = Arrays.stream(nums1).boxed().collect(Collectors.toCollection(HashSet::new));
        int res = 10;
        for (int num2 : nums2) {
            if (nums1Set.contains(num2)) {
                res = Math.min(num2, res);
            }
        }
        if (res < 10) {
            return res;
        } else {
            int digit1 = Arrays.stream(nums1).min().getAsInt();
            int digit2 = Arrays.stream(nums2).min().getAsInt();
            int first = Math.min(digit1, digit2);
            int second = Math.max(digit1, digit2);
            return first * 10 + second;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums1 = {4,1,3};
        int[] nums2 = {5,7};
        System.out.println("test: " + sol.minNumber(nums1, nums2));
    }
}