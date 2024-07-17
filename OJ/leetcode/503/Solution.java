import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] nextGreaterArr = new int[n];
        Arrays.fill(nextGreaterArr, -1);
        Deque<Integer> monotonicStack = new ArrayDeque<>();
        for (int i = 0; i < 2 * n; i++) {
            int index = i % n;
            while (!monotonicStack.isEmpty() && nums[index] > nums[monotonicStack.peek()]) {
                int preIndex = monotonicStack.pop();
                nextGreaterArr[preIndex] = nums[index];
            }
            monotonicStack.push(index);
        }
        return nextGreaterArr;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1,2,3,4,3};
        System.out.println("test: " + Arrays.toString(sol.nextGreaterElements(nums)));
    }
}