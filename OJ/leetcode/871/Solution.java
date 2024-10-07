import java.util.PriorityQueue;

class Solution {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        int n = stations.length;
        int ans = 0;
        int prePosition = 0;
        long curFule = startFuel;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> (o2 - o1));
        for (int i = 0; i <= n; i++) {
            int position = i < n ? stations[i][0] : target;
            curFule -= position - prePosition;
            while (curFule < 0 && !maxHeap.isEmpty()) {
                curFule += maxHeap.poll();
                ans++;
            }

            if (curFule < 0) {
                // can not reach target
                return -1;
            }
            if (i < n) {
                maxHeap.offer(stations[i][1]);
            }
            prePosition = position;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int target = 100;
        int startFuel = 10;
        int[][] stations = {{10,60},{20,30},{30,30},{60,40}}; // res: 2
        System.out.println("test: " + sol.minRefuelStops(target, startFuel, stations));
    }
}