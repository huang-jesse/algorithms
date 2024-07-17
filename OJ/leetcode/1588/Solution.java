class Solution {
    public int sumOddLengthSubarrays(int[] arr) {
        int n = arr.length;
        int ans = 0;
        for (int len = 1; len <= n; len = len + 2) {
            int start = 0;
            int end = start + len - 1;
            if (end >= n) {
                break;
            }

            int sum = 0;
            for (int i = start; i <= end; i++) {
                sum += arr[i];
            }
            ans += sum;
            while (end+1 < n) {
                sum -= arr[start++];
                sum += arr[++end];

                ans += sum;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] arr = {10,11,12};
        System.out.println("test: " + sol.sumOddLengthSubarrays(arr));
    }
}