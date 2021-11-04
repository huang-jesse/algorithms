import java.util.Arrays;

class Solution {
    public int findMinMoves(int[] machines) {
        int n = machines.length;
        int sum = Arrays.stream(machines).sum();
        if (sum % n != 0) {
            return -1;
        }

        int avg = sum / n;
        int preSum = 0;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            preSum += machines[i];
            int result = Math.max(machines[i] - avg, Math.abs(preSum - avg * (i+1)));
            ans = Math.max(ans, result);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] machines = {1,0,5};
        System.out.println("test: " + sol.findMinMoves(machines));
    }
}