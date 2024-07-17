class Solution {
    public int preimageSizeFZF(int k) {
        return (int)(binarySearchLeftBoundary(k + 1) - binarySearchLeftBoundary(k));
    }

    /**
     * 二分查找，尾数 0 的数量大于等于 k 个的 x! 中 x 的最小值。
     * 其中右边界为 5k
     * @param k
     * @return
     */
    private long binarySearchLeftBoundary(int k) {
        long left = 0;
        long right = 5L * k;
        while (left < right) {
            long mid = left + ((right - left) >> 1);
            if (trailingZeros(mid) >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private long trailingZeros(long x) {
        long ans = 0;
        while (x != 0) {
            ans = ans + x / 5;
            x = x / 5;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int k = 5;
        System.out.println("test: " + sol.preimageSizeFZF(k));

    }
}