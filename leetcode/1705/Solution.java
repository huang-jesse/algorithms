import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    public int eatenApples(int[] apples, int[] days) {
        int n = apples.length;
        Comparator<int[]> compare = (o1, o2) -> o1[0]-o2[0];
        PriorityQueue<int[]> pq = new PriorityQueue<>(compare);
        int time = 0;
        int ans = 0;

        while (time < n || !pq.isEmpty()) {
            if (time < n && apples[time] > 0) {
                pq.offer(new int[]{time + days[time] - 1, apples[time]});
            }
            while (!pq.isEmpty() && pq.peek()[0] < time) {
                pq.poll();
            }
            if (!pq.isEmpty()) {
                int[] cur = pq.peek();
                cur[1]--;
                if (cur[1] == 0) {
                    pq.poll();
                }
                ans++;
            }
            time++;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[] apples = {1,2,3,5,2};
        // int[] days = {3,2,1,4,2};
        int[] apples = {2,1,10};
        int[] days = {2,10,1};
        System.out.println("test: " + sol.eatenApples(apples, days));
    }
}