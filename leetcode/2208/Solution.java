import java.util.PriorityQueue;

class Solution {
    public int halveArray(int[] nums) {
        PriorityQueue<Double> maxHeap = new PriorityQueue<>((o1, o2) -> o2.compareTo(o1));
        double sum = 0.0;
        for (int num : nums) {
            sum += num;
            maxHeap.offer((double)num);
        }
        double halfSum = sum / 2;
        double currentSub = 0.0;
        int ans  = 0;
        while (currentSub < halfSum) {
            ans++;
            double current = maxHeap.poll();
            double halfCurrent = current / 2;
            currentSub += halfCurrent;
            maxHeap.offer(current - halfCurrent);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {3,8,20};
        // int[] nums = {5,19,8,1};
        System.out.println("test: " + sol.halveArray(nums));
    }
}