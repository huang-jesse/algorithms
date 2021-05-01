import java.util.Arrays;

class Solution {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        Arrays.sort(arr);
        arr[0] = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[i-1]) {
                arr[i] = arr[i-1] + 1;
            }
        }
        return arr[arr.length-1];
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] arr = new int[] {1,2,3,4,5};
        System.out.println("test: " + sol.maximumElementAfterDecrementingAndRearranging(arr));
    }
}