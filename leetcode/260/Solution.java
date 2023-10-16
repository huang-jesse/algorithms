import java.util.Arrays;

class Solution {
    public int[] singleNumber(int[] nums) {
        int xorAll = 0;
        for (int num : nums) {
            xorAll ^= num;
        }
        int lowbit = xorAll & -xorAll;
        // xorAll = a ^ b 因此所有 xorAll 中为 1 的比特位都表示 a 和 b 在该比特位不同
        // 所以，可以根据 xorAll 的最低位 1 对所有 nums 按照比特位为 0 或 1 进行分组，每个组的 xor 结果即为 a 或 b
        int[] ans = new int[]{0, 0};
        for (int num : nums) {
            ans[(lowbit & num) > 0 ? 1 : 0] ^= num;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1,2,1,3,2,5};
        System.out.println("test: " + Arrays.toString(sol.singleNumber(nums)));
    }
}