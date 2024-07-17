import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    public long kSum(int[] nums, int k) {
        long positiveSum = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int cur = nums[i];
            if (cur > 0) {
                positiveSum += (long)cur;
            } else {
                nums[i] = -cur;
            }
        }
        Arrays.sort(nums);
        k--;
        long minusSum = 0;
        PriorityQueue<long[]> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1[0], o2[0]));
        pq.offer(new long[]{nums[0], 0});
        while (k > 0) {
            k--;
            long[] curInfo = pq.poll();
            long curSum = curInfo[0];
            int index = (int)curInfo[1];
            minusSum = curSum;
            if (index < n - 1) {
                pq.offer(new long[]{curSum + nums[index + 1], index + 1});
                pq.offer(new long[]{curSum - nums[index] + nums[index + 1], index + 1});
            }
        }
        return positiveSum - minusSum;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[] nums = {2,4,-2};
        // int k = 5;
        int[] nums = {1,-2,3,4,-10,12};
        int k = 16;
        System.out.println("test: " + sol.kSum(nums, k));
    }
}