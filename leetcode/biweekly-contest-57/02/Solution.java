import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    static final int INF = Integer.MAX_VALUE;
    public int smallestChair(int[][] times, int targetFriend) {
        int n = times.length;
        Comparator<Integer[]> compare = (o1, o2) -> o1[0].compareTo(o2[0]);
        PriorityQueue<Integer[]> arrivaPq = new PriorityQueue<>(compare);
        PriorityQueue<Integer[]> leavingPq = new PriorityQueue<>(compare);
        PriorityQueue<Integer> unusedPq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            int[] time = times[i];
            arrivaPq.offer(new Integer[] {time[0], i});
        }
        int maxChar = 0;
        while (!arrivaPq.isEmpty() || !leavingPq.isEmpty()) {
            int arrivalTime = INF;
            if (!arrivaPq.isEmpty()) {
                arrivalTime = arrivaPq.peek()[0];
            }
            int leavingTime = INF;
            if (!leavingPq.isEmpty()) {
                leavingTime = leavingPq.peek()[0];
            }

            if (leavingTime <= arrivalTime) {
                Integer[] leaving = leavingPq.poll();
                int unusedChar = leaving[1];
                unusedPq.offer(unusedChar);
            } else {
                Integer[] arrival = arrivaPq.poll();
                int friendIndex = arrival[1];
                int[] time = times[friendIndex];
                int nextLeavingTime = time[1];
                int nextChar;
                if (!unusedPq.isEmpty()) {
                    nextChar = unusedPq.poll();
                } else {
                    nextChar = maxChar++;
                }
                if (friendIndex == targetFriend) {
                    return nextChar;
                }
                Integer[] nextLeaving = new Integer[] {nextLeavingTime, nextChar};
                leavingPq.offer(nextLeaving);
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] times =  {{3,10},{1,5},{2,6}};
        int targetFriend = 0;
        System.out.println("test: " + sol.smallestChair(times, targetFriend));
    }
}