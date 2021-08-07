class Solution {
    static final int INIT_INDEX = -1;
    public boolean circularArrayLoop(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (isLoop(nums, INIT_INDEX, i, 0)) {
                return true;
            }
        }
        return false;
    }

    private boolean isLoop(int[] nums, int firstIndex, int curIndex, int count) {
        int n = nums.length;
        if (firstIndex == curIndex) {
            return count > 1;
        }
        if (count >= n) {
            return false;
        }
        if (firstIndex == INIT_INDEX) {
            firstIndex = curIndex;
        }
        int firstStep = nums[firstIndex];
        int curStep = nums[curIndex];
        if (firstStep * curStep < 0) {
            return false;
        }
        int nextIndex = getNextIndex(nums, curIndex);
        return isLoop(nums, firstIndex, nextIndex, count + 1);
    }

    private int getNextIndex(int[] nums, int curIndex) {
        int n = nums.length;
        int step = nums[curIndex] % n;
        int next = curIndex + step;
        if (next > n-1) {
            return next - (n-1) - 1;
        } else if (next < 0) {
            return (n-1) + next + 1;
        } else {
            return next;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {-2, -3, -9};
        System.out.println("test: " + sol.circularArrayLoop(nums));
    }
}