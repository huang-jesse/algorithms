import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
    public int[] findIntersectionValues(int[] nums1, int[] nums2) {
        Set<Integer> nums1Set = Arrays.stream(nums1).boxed().collect(Collectors.toSet());
        Set<Integer> nums2Set = Arrays.stream(nums2).boxed().collect(Collectors.toSet());
        int ans1 = 0;
        for (int num : nums1) {
            if (nums2Set.contains(num)) ans1++;
        }
        int ans2 = 0;
        for (int num : nums2) {
            if (nums1Set.contains(num)) ans2++;
        }
        return new int[]{ans1, ans2};
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums1 = {2,3,2};
        int[] nums2 = {1,2};
        System.out.println("test: " + Arrays.toString(sol.findIntersectionValues(nums1, nums2)));
    }
}