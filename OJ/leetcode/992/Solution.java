/**
 * 「最多存在 K 个不同整数的子区间的个数」与「恰好存在 K 个不同整数的子区间的个数」的差恰好等于「最多存在 K−1 个不同整数的子区间的个数」。
 *  即 「最多存在 K 个不同整数的子区间的个数」 - 「最多存在 K−1 个不同整数的子区间的个数」 = 「恰好存在 K 个不同整数的子区间的个数」
 */
class Solution {
    public int subarraysWithKDistinct(int[] nums, int k) {
        return atMostWithKDistinct(nums, k) - atMostWithKDistinct(nums, k - 1);
    }

    /**
     * 最多包含 K 个不同整数的子区间的个数
     * @param nums
     * @param k
     * @return
     */
    private int atMostWithKDistinct(int[] nums, int k) {
        if (k == 0) return 0;
        int n = nums.length;
        int[] counter = new int[n + 1];
        int left = 0;
        int right = 0;
        // [left, right) 里不同整数的个数
        int distinct = 0;
        // [left, right) 包含不同整数的个数小于等于 K
        int res = 0;
        while (right < n) {
            if (counter[nums[right]] == 0) distinct++;
            counter[nums[right]]++;
            right++;
            while (distinct > k) {
                counter[nums[left]]--;
                if (counter[nums[left]] == 0) {
                    distinct--;
                }
                left++;
            }
            res += right - left;
        }
        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[] nums = {1,2,1,2,3};
        // int k = 2; // ans = 7
        int[] nums = {1,2};
        int k = 1; // ans = 2
        System.out.println("test: " + sol.subarraysWithKDistinct(nums, k));
    }
}