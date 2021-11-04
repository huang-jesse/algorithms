class Solution {
    public boolean isPerfectSquare(int num) {
        if (num == 1)
            return true;
        boolean ans = false;
        int low = 2;
        int high = num >> 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            long numPow2 = (long)mid * mid;
            if (numPow2 == num) {
                ans = true;
                break;
            } else if (numPow2 < num) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int num = 808201;
        System.out.println("test: " + sol.isPerfectSquare(num));
    }
}