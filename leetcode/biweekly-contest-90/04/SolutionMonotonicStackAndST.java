import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.stream.Collectors;

class SolutionMonotonicStackAndST {

    /**
     * Max Sparse Table
     */
    class ST {
        private int[] log2;
        private int[][] f;
        public ST(int[] nums) {
            int n = nums.length;
            this.log2 = preProcessLog2(n);
            this.f = new int[n][log2[n] + 1];
            for (int i = 0; i < n; i++) {
                f[i][0] = nums[i];
            }
            for (int j = 1; j <= log2[n]; j++) {
                for (int i = 0; i + (1 << (j - 1)) < n; i++) {
                    f[i][j] = Math.max(f[i][j - 1], f[i + (1 << (j - 1))][j - 1]);
                }
            }
        }

        private int[] preProcessLog2(int n) {
            int[] log2 = new int[n + 1];
            log2[1] = 0;
            for (int i = 2; i <= n; i++) {
                log2[i] = log2[i / 2] + 1;
            }
            return log2;
        }

        public int query(int l, int r) {
            int s = log2[r - l + 1];
            return Math.max(f[l][s], f[r - (1 << s) + 1][s]);
        }
    }

    public int[] secondGreaterElement(int[] nums) {
        int n = nums.length;
        // monotone decreasing
        Deque<Integer> monotonicStack = new ArrayDeque<>();
        int[] firstGreaterElements = new int[n];
        for (int i = 0; i < n; i++) {
            int curNum = nums[i];
            while (!monotonicStack.isEmpty() && curNum > nums[monotonicStack.peek()]) {
                int preNumIndex = monotonicStack.pop();
                firstGreaterElements[preNumIndex] = i;
            }
            monotonicStack.push(i);
        }
        while (!monotonicStack.isEmpty()) {
            firstGreaterElements[monotonicStack.pop()] = -1;
        }

        int[] ans = new int[n];
        ST maxSt = new ST(nums);
        for (int i = 0; i < n; i++) {
            int curNum = nums[i];
            int firstGreaterIndex = firstGreaterElements[i];
            int secondGreaterIndex = binarySearchLeftBoundary(maxSt, firstGreaterIndex + 1, n - 1, curNum);
            if (secondGreaterIndex == -1) {
                ans[i] = -1;
            } else {
                ans[i] = nums[secondGreaterIndex];
            }
        }
        return ans;
    }

    private int binarySearchLeftBoundary(ST maxSt, int left, int right, int target) {
        if (left <= 0 || left > right) {
            return -1;
        }
        if (maxSt.query(left, right) <= target) {
            return -1;
        }
        int lowerBound = left;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (maxSt.query(lowerBound, mid) > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        SolutionMonotonicStackAndST sol = new SolutionMonotonicStackAndST();
        int[] nums = {2,4,0,9,6};
        System.out.println("test: " + Arrays.stream(sol.secondGreaterElement(nums)).boxed().collect(Collectors.toList()));
    }
}