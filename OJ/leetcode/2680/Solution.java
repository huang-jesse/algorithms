import java.util.Arrays;

class Solution {
    public long maximumOr(int[] nums, int k) {
        int max = Arrays.stream(nums).max().getAsInt();
        int highestBitIdx = 32 - Integer.numberOfLeadingZeros(max) - 1;
        int[] bitCounter = new int[highestBitIdx + 1];
        long sumOr = 0L;
        for (int num : nums) {
            sumOr |= num;
            for (int i = 0; i <= highestBitIdx; i++) {
                bitCounter[i] += (num >> i) & 1;
            }
        }
        long ans = 0L;
        for (int num : nums) {
            // 只需要校验最高位highestBitIdx为1的数值
            if ((num >> highestBitIdx & 1) != 1) continue;
            // 操作 k 次
            long opNum = (long)num << k;
            // 对sumOr进行num或运算的“逆运算”
            long tempSumOr = sumOr;
            for (int i = 0; i <= highestBitIdx; i++) {
                if ((num >> i & 1) == 1 && bitCounter[i] == 1) {
                    // 有且仅有一位，则需要在tempSumOr移除当前位
                    tempSumOr ^= 1 << i;
                }
            }
            // 计算当前num操作k次后与tempSumOr或运算的结果并更新最大值
            ans = Math.max(ans, opNum | tempSumOr);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {8,1,2};
        int k = 2;
        System.out.println("test: " + sol.maximumOr(nums, k));
    }
}