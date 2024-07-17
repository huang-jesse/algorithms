import java.util.Arrays;

class Solution {
    public String findDifferentBinaryString(String[] nums) {
        int n = nums.length;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(nums[i], 2);
        }
        Arrays.sort(arr);
        int missingNum = 0;
        if (arr[0] == 0) {
            for (int i = 1; i < n; i++) {
                if (arr[i] != arr[i-1] && arr[i] != arr[i-1] + 1) {
                    missingNum = arr[i-1] + 1;
                    break;
                }
            }
            if (missingNum == 0) {
                missingNum = arr[n-1] + 1;
            }
        }

        String binaryString = Integer.toBinaryString(missingNum);
        if (binaryString.length() < n) {
            StringBuilder sb = new StringBuilder(binaryString);
            int len = binaryString.length();
            while (len < n) {
                sb.insert(0, "0");
                len++;
            }
            binaryString = sb.toString();
        }
        return binaryString;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] nums = {"001","000","100"};
        // String[] nums = {"001","000","000"};
        System.out.println("test: " + sol.findDifferentBinaryString(nums));
    }
}