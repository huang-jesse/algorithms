class SolutionHeapify {
    public long maxKelements(int[] nums, int k) {
        long ans = 0;
        // 原地堆化，最大堆，nums[0] 为堆的最大元素
        heapify(nums);
        for (int i = 0; i < k; i++) {
            ans += nums[0];
            nums[0] = (nums[0] + 2) / 3;
            // 下沉 /3 后的 nums[0]
            sink(nums, 0);
        }

        return ans;
    }

    /**
     * 原地堆化（最大堆）
     * 堆化可以保证 nums[0] 是堆顶元素，且 nums[i] >= max(nums[2*i+1], nums[2*i+2])
     * @param nums
     */
    private void heapify(int[] nums) {
        int n = nums.length;
        // 倒序遍历，保证当前遍历的节点的左右子节点已经为堆
        for (int i = (n - 1) / 2; i >= 0; i--) {
            sink(nums, i);
        }
    }

    /**
     * 最大堆，下沉 i 直到大于左右子节点，或 i 为子节点
     * @param nums
     * @param i
     */
    private void sink(int[] nums, int i) {
        int n = nums.length;
        while (i * 2 + 1 < n) {
            int j = i * 2 + 1;
            // 右节点 nums[i * 2 + 2] > nums[i * 2 + 1]
            if (j + 1 < n && nums[j + 1] > nums[j]) {
                j++;
            }
            if (nums[i] > nums[j]) {
                // i 大于左右子节点，不需要继续下沉
                break;
            }
            // i 比左右子节点的最大值小
            swap(nums, i, j);
            // 继续下沉
            i = j;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        SolutionHeapify sol = new SolutionHeapify();
        int[] nums = {10,10,10,10,10};
        int k = 5;
        System.out.println("test: " + sol.maxKelements(nums, k));
    }
}