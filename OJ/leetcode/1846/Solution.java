import java.util.Arrays;

class Solution {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length;
        arr[0] = 1;
        for (int i = 1; i < n; i++) {
            int cur = arr[i];
            int pre = arr[i-1];
            arr[i] = Math.min(pre+1, cur);
        }
        return arr[n-1];
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] arr = {1,2,3,4,5};
        System.out.println("test: " + sol.maximumElementAfterDecrementingAndRearranging(arr));
    }
}