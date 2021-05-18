class Solution {
    public int countTriplets(int[] arr) {
        int ans = 0;
        int n = arr.length;
        int[] preXorSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            preXorSum[i] = preXorSum[i-1] ^ arr[i-1];
        }
        // loop
        for (int i = 0; i < n; i++) {
            for (int k = i+1; k < n; k++) {
                int a = preXorSum[i];
                int b = preXorSum[k+1];
                if (a == b) {
                    ans += k-i;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] arr = new int[]{7,11,12,9,5,2,7,17,22};
        System.out.println("test: " + sol.countTriplets(arr));
    }
}