import java.util.PriorityQueue;

class Solution {
    public int eliminateMaximum(int[] dist, int[] speed) {
        PriorityQueue<Integer> timePriorityQueue = new PriorityQueue<>();
        int n = dist.length;
        for (int i = 0; i < n; i++) {
            int time = (int)Math.ceil((double)dist[i] / speed[i]);
            timePriorityQueue.offer(time);
        }
        int curTime = 0;
        while (!timePriorityQueue.isEmpty()) {
            int time = timePriorityQueue.poll();
            if (curTime < time) {
                curTime++;
            } else {
                break;
            }
        }
        return curTime;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] dist = {3,2,4};
        int[] speed = {5,3,2};
        System.out.println("test: " + sol.eliminateMaximum(dist, speed));
    }
}