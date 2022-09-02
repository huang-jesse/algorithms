import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.Collectors;

class SolutionMonotonicQueue {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        Deque<Integer> deQueue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            int curNum = nums[i];
            if (!deQueue.isEmpty() && i - deQueue.peekFirst() >= k) {
                deQueue.pollFirst();
            }
            while(!deQueue.isEmpty() && nums[deQueue.peekLast()] <= curNum) {
                deQueue.pollLast();
            }
            deQueue.offerLast(i);
            int frontIndex = i - k + 1;
            if (frontIndex >= 0) {
                ans[frontIndex] = nums[deQueue.peekFirst()];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        SolutionMonotonicQueue sol = new SolutionMonotonicQueue();
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;
        // int[] nums = {1};
        // int k = 1;
        // int[] nums = {0,0};
        // int k = 2;
        // int[] nums = {0,0};
        // int k = 1;
        System.out.println("test: " + Arrays.stream(sol.maxSlidingWindow(nums, k)).boxed().collect(Collectors.toList()));
    }
}