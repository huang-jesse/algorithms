class Solution {
    public int maxEqualFreq(int[] nums) {
        int n = nums.length;
        int[] counter = new int[100010];
        int[] freq = new int[100010];
        int maxFreq = 0;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int curNum = nums[i];
            if (freq[counter[curNum]] > 0) {
                freq[counter[curNum]]--;
            }
            counter[curNum]++;
            maxFreq = Math.max(maxFreq, counter[curNum]);
            freq[counter[curNum]]++;
            if (check(freq, maxFreq, i)) {
                ans = Math.max(ans, i+1);
            }
        }
        return ans;
    }

    private boolean check(int[] freq, int maxFreq, int index) {
        return maxFreq == 1
            || freq[maxFreq] * maxFreq + freq[maxFreq-1] * (maxFreq - 1) == index + 1 && freq[maxFreq] == 1
            || freq[maxFreq] * maxFreq + 1 == index + 1 && freq[1] == 1;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[] nums = {2,2,1,1,5,3,3,5};
        // int[] nums = {1,1,1,2,2,2,3,3,3,4,4,4,5};
        int[] nums = {10,2,8,9,3,8,1,5,2,3,7,6};
        System.out.println("test: " + sol.maxEqualFreq(nums));
    }
}