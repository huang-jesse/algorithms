import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        int[] handleCounter = new int[k];
        // [completeTime, serverIndex]
        Comparator<int[]> compareByCompleteTime = (o1, o2) -> o1[0] - o2[0];
        PriorityQueue<int[]> taskQueue = new PriorityQueue<>(compareByCompleteTime);
        List<Integer> allServers = IntStream.range(0, k).boxed().collect(Collectors.toList());
        TreeSet<Integer> availableServersTs = new TreeSet<>(allServers);
        int n = arrival.length;
        for (int i = 0; i < n; i++) {
            int arrivalTime = arrival[i];
            // task complete
            while (!taskQueue.isEmpty() && taskQueue.peek()[0] <= arrivalTime) {
                int serverIndex = taskQueue.poll()[1];
                availableServersTs.add(serverIndex);
            }
            // handle server
            if (!availableServersTs.isEmpty()) {
                int nextServer = i % k;
                Integer handleServer = availableServersTs.ceiling(nextServer);
                if (handleServer == null) {
                    handleServer = availableServersTs.first();
                }
                availableServersTs.remove(handleServer);
                handleCounter[handleServer]++;
                int completeTime = arrivalTime+load[i];
                taskQueue.offer(new int[]{completeTime, handleServer});
            }
        }

        // get busiestServers
        List<Integer> ans = new ArrayList<>();
        int maxHandle = Arrays.stream(handleCounter).max().getAsInt();
        for (int i = 0; i < k; i++) {
            if (handleCounter[i] == maxHandle) {
                ans.add(i);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int k = 3;
        int[] arrival = {1,2,3,4,5};
        int[] load = {5,2,3,3,3};
        System.out.println("test: " + sol.busiestServers(k, arrival, load));
    }
}