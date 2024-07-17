import java.util.Arrays;

class SolutionOptimization {
    public int[] fullBloomFlowers(int[][] flowers, int[] people) {
        int n = flowers.length;
        int m = people.length;
        int[] starts = new int[n];
        int[] stoppeds = new int[n];
        for (int i = 0; i < n; i++) {
            // 花开时间
            starts[i] = flowers[i][0];
            // 花谢时间
            stoppeds[i] = flowers[i][1] + 1;
        }
        Arrays.sort(starts);
        Arrays.sort(stoppeds);
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            int startsNum = binarySearchRightBoundary(starts, people[i]) + 1;
            int stoppedsNum = binarySearchRightBoundary(stoppeds, people[i]) + 1;
            ans[i] = startsNum - stoppedsNum;
        }
        return ans;
    }

    /**
     * floor, the greatest element index less than or equal to the target
     * @param args
     */
    private int binarySearchRightBoundary(int[] nums, int target) {
        if (nums[0] > target) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + ((right - left + 1) >> 1);
            if (nums[mid] <= target) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        SolutionOptimization sol = new SolutionOptimization();
        int[][] flowers = {{1,6},{3,7},{9,12},{4,13}};
        int[] people = {2,3,7,11};
        // int[][] flowers = {{1,10},{3,3}};
        // int[] people = {3,3,2};
        int[] ans = sol.fullBloomFlowers(flowers, people);
        System.out.println("test: " + Arrays.toString(ans));
    }
}