class Solution {
    public int singleNumber(int[] nums) {
        int[] bitCount = new int[32];
        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                if (((num >> i) & 1) == 1) {
                    bitCount[i]++;
                }
            }
        }
        for (int i = 0; i < 32; i++) {
            bitCount[i] %= 3;
        }
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            ans |= (bitCount[i] << i);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {30000,500,100,30000,100,30000,100};
        System.out.println("test: " + sol.singleNumber(nums));
    }
}