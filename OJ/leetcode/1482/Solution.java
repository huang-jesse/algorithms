import java.util.Arrays;
import java.util.TreeSet;

class Solution {
    int[] bloomDay;
    int k; // number of flowers per bunch
    int n; // number of flowers
    int m; // number of need bunches
    long numOfNeedFlowers;
    static final int INF = (int)1e9; // 10^9
    public int minDays(int[] bloomDay, int m, int k) {
        this.k = k;
        this.n = bloomDay.length;
        this.m = m;
        this.bloomDay = bloomDay;
        this.numOfNeedFlowers = (long)m * (long)k;
        if (numOfNeedFlowers > n)
            return -1;
        TreeSet<Integer> ts = new TreeSet<>();
        Arrays.stream(bloomDay).forEach(i -> ts.add(i));
        int[] sortedBloomDay = ts.stream().mapToInt(t -> t).toArray();
        return binarySearch(sortedBloomDay);
    }

    private int binarySearch(int[] sortedBloomDay) {
        int left = 0;
        int right = sortedBloomDay.length-1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            int cur = sortedBloomDay[mid];
            if (canMake(cur)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return sortedBloomDay[right];
    }

    public boolean canMake(int days) {
        int bouquets = 0;
        int flowers = 0;
        int length = bloomDay.length;
        for (int i = 0; i < length && bouquets < m; i++) {
            if (bloomDay[i] <= days) {
                flowers++;
                if (flowers == k) {
                    bouquets++;
                    flowers = 0;
                }
            } else {
                flowers = 0;
            }
        }
        return bouquets >= m;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[] bloomDay = new int[] {7,7,7,7,12,7,7};
        // int m = 2;
        // int k = 3;
        // int[] bloomDay = new int[] {1,10,3,10,2};
        // int m = 3;
        // int k = 2;
        // int[] bloomDay = new int[] {1000000000,1000000000};
        // int m = 1;
        // int k = 1;
        int[] bloomDay = new int[] {86,96,17,52,85,50,68,76,28,96,27,63,2,31,10,85,2,14,77,13,84,30,75,7,95,37,87,79,3,7,41,33,39,46,99,4,88,84,65,55,56,77,31,94,62,43,86,53,67,8,3,21,5,94,57,30,46,62,41,92,4,22,93,31,57,9,54,38,5,65,80,90,80,50,92,26,15,12,8,17,3,45,100,96,43,38,93,22,23,96,38,15,24,92,38,17,87,74,69,77
        };
        int m = 32;
        int k = 3;
        // expectation 99
        System.out.println("minDays: " + sol.minDays(bloomDay, m, k));
    }
}