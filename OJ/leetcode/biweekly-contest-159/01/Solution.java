import java.util.PriorityQueue;

class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            cnt += nums[i] % 2;
        }
        if ((n % 2 == 0 && cnt != n / 2) || (n % 2 == 1 && (cnt < n / 2 || cnt > (n / 2 + 1)))) return -1;
        int parity = cnt > n / 2 ? 1 : 0;
        int res = getMinSwaps(nums, parity);
        if (n % 2 == 0) {
            res = Math.min(res, getMinSwaps(nums, parity ^ 1));
        }
        return res;
    }

    private static int getMinSwaps(int[] nums, int parity) {
        int n = nums.length;
        int[] parityNums = new int[n];
        for (int i = 0; i < n; i++) {
            parityNums[i] = nums[i] % 2;
        }
        PriorityQueue<Integer>[] minHeaps = (PriorityQueue<Integer>[])new PriorityQueue[2];
        minHeaps[0] = new PriorityQueue<>();
        minHeaps[1] = new PriorityQueue<>();
        for (int idx = 0; idx < n; idx++) {
            minHeaps[parityNums[idx]].offer(idx);
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (parityNums[i] == parity) {
                // 不需要交换
                // 移除当前位
                minHeaps[parityNums[i]].poll();
            } else {
                // 需要交换
                // 将当前位与最近一个 parity 位交换
                // 移除 parity 位
                int idx = minHeaps[parity].poll();
                // 将当前位后移到 idx 位置
                minHeaps[parityNums[i]].poll();
                minHeaps[parityNums[i]].offer(idx);
                parityNums[idx] ^= 1;
                // 累加交换的次数
                res += idx - i;
            }
            parity = 1^parity;
        }
        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[] nums = {2,4,6,5,7};
        int[] nums = {2,4,5,7};
        System.out.println("test: " + sol.minSwaps(nums));
    }
}