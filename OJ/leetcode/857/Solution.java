import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

class Solution {
    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        int n = quality.length;
        Integer[] indexes = IntStream.range(0, n).boxed().toArray(Integer[]::new);
        // compare: quality/wage asc
        // (double)wage[o1] / quality[o1] - (double)wage[o2] / quality[o2]
        Comparator<Integer> comparator = (o1, o2) -> wage[o1] * quality[o2] - wage[o2] * quality[o1];
        Arrays.sort(indexes, comparator);
        int totalQuality = 0;
        PriorityQueue<Integer> qualityMaxPq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int i = 0; i < k; i++) {
            totalQuality += quality[indexes[i]];
            qualityMaxPq.offer(quality[indexes[i]]);
        }
        double ans = (double)wage[indexes[k - 1]] / quality[indexes[k - 1]] * totalQuality;
        for (int i = k; i < n; i++) {
            if (quality[indexes[i]] < qualityMaxPq.peek()) {
                totalQuality = totalQuality - qualityMaxPq.poll() + quality[indexes[i]];
                qualityMaxPq.offer(quality[indexes[i]]);
                double res = (double)wage[indexes[i]] / quality[indexes[i]] * totalQuality;
                ans = Math.min(ans, res);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] quality = {3,1,10,10,1};
        int[] wage = {4,8,2,2,7};
        int k = 3;
        System.out.println("test: " + sol.mincostToHireWorkers(quality, wage, k));
    }
}