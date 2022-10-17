import java.util.Arrays;
import java.util.TreeMap;
import java.util.stream.Collectors;

class Solution {
    public int[] advantageCount(int[] nums1, int[] nums2) {
        int n = nums1.length;
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for (int num : nums1) {
            tm.put(num, tm.getOrDefault(num, 0) + 1);
        }
        for (int i = 0; i < n; i++) {
            int num2 = nums2[i];
            Integer targetKey = tm.higherKey(num2);
            if (targetKey == null) {
                targetKey = tm.firstKey();
            }
            nums1[i] = targetKey;
            tm.put(targetKey, tm.get(targetKey) - 1);
            if (tm.get(targetKey) == 0) {
                tm.remove(targetKey);
            }
        }
        return nums1;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums1 = {12,24,8,32};
        int[] nums2 = {13,25,32,11};
        System.out.println("test: " + Arrays.stream(sol.advantageCount(nums1, nums2)).boxed().collect(Collectors.toList()));
    }
}