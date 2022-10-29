import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.Collectors;

class SolutionMonotonicQueue {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        Deque<Integer> dequeue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            int curNum = nums[i];
            if (!dequeue.isEmpty() && i - dequeue.peekFirst() >= k) {
                dequeue.pollFirst();
            }
            while(!dequeue.isEmpty() && nums[dequeue.peekLast()] <= curNum) {
                dequeue.pollLast();
            }
            dequeue.offerLast(i);
            int frontIndex = i - k + 1;
            if (frontIndex >= 0) {
                ans[frontIndex] = nums[dequeue.peekFirst()];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        SolutionMonotonicQueue sol = new SolutionMonotonicQueue();
        int[] nums = {1,3,6,2,5,1,7};
        int k = 4;
        System.out.println("test: " + Arrays.stream(sol.maxSlidingWindow(nums, k)).boxed().collect(Collectors.toList()));
    }
}