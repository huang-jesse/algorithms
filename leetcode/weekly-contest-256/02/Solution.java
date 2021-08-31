import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public String kthLargestNumber(String[] nums, int k) {
        int n = nums.length;
        String[] newNums = new String[n];
        Map<String, String> numsMap = new HashMap<>();
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            maxLen = Math.max(maxLen, nums[i].length());
        }
        for (int i = 0; i < n; i++) {
            if (nums[i].length() < maxLen) {
                newNums[i] = getZeroString(maxLen - nums[i].length()).concat(nums[i]);
            } else {
                newNums[i] = nums[i];
            }
            numsMap.put(newNums[i], nums[i]);
        }
        Arrays.sort(newNums);
        return numsMap.get(newNums[n-k]);
    }

    private static String getZeroString(int len) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        while (count < len) {
            sb.append("0");
            count++;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // String[] nums = {"623986800","3","887298","695","794","6888794705","269409","59930972","723091307","726368","8028385786","378585"};
        // int k = 11;
        String[] nums = {"2","21","12","1"};
        int k = 3;
        System.out.println("test: " + sol.kthLargestNumber(nums, k));
    }
}