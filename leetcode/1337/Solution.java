import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

class Solution {
    public int[] kWeakestRows(int[][] mat, int k) {
        Comparator<int[]> compare = (o1, o2) -> {
            int comparePower = o1[0] - o2[0];
            if (comparePower == 0) {
                return o1[1] - o2[1];
            } else {
                return comparePower;
            }
        };
        // [power, rowIndex]
        PriorityQueue<int[]> pq = new PriorityQueue<>(compare.reversed());
        int n = mat.length;
        for (int i = 0; i < n; i++) {
            int power = getPower(mat[i]);
            pq.offer(new int[]{power + 1, i});
            if (pq.size() > k) {
                pq.remove();
            }
        }
        int[] ans = new int[k];
        for (int i = k-1; i >= 0; i--) {
            ans[i] = pq.poll()[1];
        }
        return ans;
    }

    private int getPower(int[] row) {
        int l = 0;
        int r = row.length - 1;
        int power = -1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (row[mid] == 0) {
                r = mid - 1;
            } else {
                power = mid;
                l = mid + 1;
            }
        }
        return power;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] mat = {{1,1,0,0,0},
                       {1,1,1,1,0},
                       {1,0,0,0,0},
                       {1,1,0,0,0},
                       {1,1,1,1,1}};
        int k = 3;
        int[] ans = sol.kWeakestRows(mat, k);
        System.out.println("test: " + Arrays.stream(ans).boxed().collect(Collectors.toList()));
    }
}