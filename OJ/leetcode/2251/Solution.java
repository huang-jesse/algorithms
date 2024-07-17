import java.util.Arrays;

class Solution {
    public int[] fullBloomFlowers(int[][] flowers, int[] people) {
        int n = people.length;
        Integer[] indexes = new Integer[n];
        for (int i = 0; i < n; i++) {
            indexes[i] = i;
        }
        Arrays.sort(indexes, (o1, o2) -> people[o1] - people[o2]);
        int[] fullBloomFlowers = new int[n + 1];
        for (int[] flower : flowers) {
            int start = flower[0];
            int end = flower[1];
            int startIndex = binarySearchLeftBoundary(indexes, people, start);
            int endIndex = binarySearchRightBoundary(indexes, people, end);
            if (startIndex <= endIndex) {
                // [start, end] 范围内有数据
                fullBloomFlowers[startIndex]++;
                fullBloomFlowers[endIndex + 1]--;
            }
        }
        for (int i = 1; i < n; i++) {
            fullBloomFlowers[i] += fullBloomFlowers[i - 1];
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[indexes[i]] = fullBloomFlowers[i];
        }
        return ans;
    }

    /**
     * ceil, the least element index the indexes greater than or equal to the target
     * @param args
     */
    private int binarySearchLeftBoundary(Integer[] indexes, int[] people, int target) {
        int n = indexes.length;
        if (people[indexes[ n - 1]] < target) {
            return n;
        }
        int left = 0;
        int right = n - 1;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (people[indexes[mid]] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    /**
     * floor, the greatest element index the indexes less than or equal to the target
     * @param args
     */
    private int binarySearchRightBoundary(Integer[] indexes, int[] people, int target) {
        if (people[indexes[0]] > target) {
            return -1;
        }
        int left = 0;
        int right = indexes.length - 1;
        while (left < right) {
            int mid = left + ((right - left + 1) >> 1);
            if (people[indexes[mid]] <= target) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[][] flowers = {{1,6},{3,7},{9,12},{4,13}};
        // int[] people = {2,3,7,11};
        int[][] flowers = {{1,10},{3,3}};
        int[] people = {3,3,2};
        int[] ans = sol.fullBloomFlowers(flowers, people);
        System.out.println("test: " + Arrays.toString(ans));
    }
}