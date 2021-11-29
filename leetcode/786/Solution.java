import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

class Solution {
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        int n = arr.length;
        if (n == 2)
            return new int[] {1, arr[1]};
        Comparator<int[]> fractionCompare = (o1, o2) -> arr[o1[0]]*arr[o2[1]] - arr[o2[0]]*arr[o1[1]];
        PriorityQueue<int[]> minPq = new PriorityQueue<>(fractionCompare);
        for (int i = 1; i < n; i++) {
            minPq.offer(new int[] {0, i});
        }
        for (int i = 0; i < k-1; i++) {
            int[] fractionIndexs = minPq.poll();
            int numerator = fractionIndexs[0];
            int denominator = fractionIndexs[1];
            if (numerator + 1 < denominator) {
                minPq.offer(new int[] {numerator + 1, denominator});
            }
        }
        int[] ansIndex = minPq.poll();
        return new int[] {arr[ansIndex[0]], arr[ansIndex[1]]};
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] arr = {1,2,3,5};
        int k = 3;
        List<Integer> fraction = Arrays.stream(sol.kthSmallestPrimeFraction(arr, k)).boxed().collect(Collectors.toList());
        System.out.println("test: " + fraction);
    }
}