import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.stream.Collectors;

class Solution {
    public int[] secondGreaterElement(int[] nums) {
        int n = nums.length;
        // monotone decreasing
        // {index, count}, when count is 1, then found the second greater element
        Deque<int[]> monotonicStack = new ArrayDeque<>();
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int curNum = nums[i];
            Deque<int[]> tempStack = new ArrayDeque<>();
            while (!monotonicStack.isEmpty() && curNum > nums[monotonicStack.peek()[0]]) {
                int[] element = monotonicStack.pop();
                if (element[1] == 0) {
                    // occur first greater element, then count + 1
                    element[1]++;
                    tempStack.push(element);
                } else {
                    // count is 1, found the second greater element
                    int index = element[0];
                    ans[index] = curNum;
                }
            }
            int[] curElement = new int[]{i, 0};
            monotonicStack.push(curElement);
            // push remain elements
            while (!tempStack.isEmpty()) {
                monotonicStack.push(tempStack.pop());
            }
        }
        while (!monotonicStack.isEmpty()) {
            ans[monotonicStack.pop()[0]] = -1;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {2,4,0,9,6};
        System.out.println("test: " + Arrays.stream(sol.secondGreaterElement(nums)).boxed().collect(Collectors.toList()));
    }
}