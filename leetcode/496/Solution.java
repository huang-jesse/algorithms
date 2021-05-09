import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Solution {

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> nums2Map = new HashMap<>();
        Deque<Integer> greatStack = new ArrayDeque<>();
        for (int i : nums2) {
            while (!greatStack.isEmpty() && greatStack.peekFirst() < i) {
                nums2Map.put(greatStack.removeFirst(), i);
            }
            greatStack.addFirst(i);
        }
        int[] ans = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            ans[i] = nums2Map.getOrDefault(nums1[i], -1);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums1 = new int[] {4,1,2};
        int[] nums2 = new int[] {1,3,4,2};
        int[] ans = sol.nextGreaterElement(nums1, nums2);
        List<Integer> ansList = Arrays.stream(ans).boxed().collect(Collectors.toList());
        System.out.println("nextGreaterElement: " + ansList);
    }
}