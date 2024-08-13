import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public boolean[] isArraySpecial(int[] nums, int[][] queries) {
        int n = nums.length;
        // [{from, to}]
        List<int[]> blockList = new ArrayList<>();
        int count = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] % 2 == nums[i - 1] % 2) {
                count++;
            } else {
                // nums[i] != nums[i - 1]
                if (count > 1) {
                    blockList.add(new int[]{i - count, i - 1});
                }
                count = 1;
            }
        }
        if (count > 1) {
            blockList.add(new int[]{n - count, n - 1});
        }
        int m = queries.length;
        boolean[] ans = new boolean[m];
        for (int i = 0; i < m; i++) {
            int[] query = queries[i];
            int from = query[0];
            int to = query[1];
            if (blockList.isEmpty() || from == to) {
                ans[i] = true;
                continue;
            }
            int left = binarySearchLeftBoundary(blockList, from);
            int right = binarySearchRightBoundary(blockList, to);
            if (left > right) {
                ans[i] = true;
            }
        }
        return ans;
    }

    private int binarySearchLeftBoundary(List<int[]> blockList, int target) {
        int n = blockList.size();
        if (blockList.get(n - 1)[1] <= target) return n;
        int l = 0;
        int r = n - 1;
        while (l < r) {
            int mid = l + ((r - l) >> 1);
            if (blockList.get(mid)[1] > target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private int binarySearchRightBoundary(List<int[]> blockList, int target) {
        int n = blockList.size();
        if (blockList.get(0)[0] >= target) return -1;
        int l = 0;
        int r = n - 1;
        while (l < r) {
            int mid = l + ((r - l + 1) >> 1);
            if (blockList.get(mid)[0] < target) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {4,3,1,6};
        int[][] quereis = {{0,2}, {2,3}}; // res: [false,true]
        System.out.println("test: " + Arrays.toString(sol.isArraySpecial(nums, quereis)));
    }
}