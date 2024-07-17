class Solution {
    public int getWinner(int[] arr, int k) {
        int n = arr.length;
        int first = arr[0];
        int count = 0;
        int max = arr[0];
        for (int i = 1; i < n; i++) {
            max = Math.max(max, arr[i]);
            if (first > arr[i]) {
                count++;
            } else {
                count = 1;
                first = arr[i];
            }
            if (count >= k) {
                return first;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] arr = {2,1,3,5,4,6,7};
        int k = 2;
        System.out.println("test: " + sol.getWinner(arr, k));
    }
}