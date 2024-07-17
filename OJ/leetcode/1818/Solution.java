import java.util.TreeSet;

class Solution {
    static final int INF = (int)1e9 + 7;
    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int minAbsoluteSumDiff = 0;
        TreeSet<Integer> ts = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            int num1 = nums1[i];
            int num2 = nums2[i];

            ts.add(num1);

            int absoluteDiff = Math.abs(num1 - num2);
            minAbsoluteSumDiff = ((minAbsoluteSumDiff + absoluteDiff) % INF);
        }

        int maxAbsoluteDiff = 0;
        for (int i = 0; i < n; i++) {
            int num1 = nums1[i];
            int num2 = nums2[i];

            int minAbsolute = INF;
            Integer floorOfNum2 = ts.floor(num2);
            if (floorOfNum2 != null) {
                minAbsolute = Math.min(minAbsolute, num2 - floorOfNum2);
            }
            Integer ceilingOfNum2 = ts.ceiling(num2);
            if (ceilingOfNum2 != null) {
                minAbsolute = Math.min(minAbsolute, ceilingOfNum2 - num2);
            }
            int curAbsolute = Math.abs(num1 - num2);
            maxAbsoluteDiff = Math.max(maxAbsoluteDiff, Math.abs(curAbsolute - minAbsolute));
        }
        int ans = minAbsoluteSumDiff - maxAbsoluteDiff;
        if (ans < 0) {
            return INF + ans;
        } else {
            return ans;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums1 = {1,10,4,4,2,7};
        int[] nums2 = {9,3,5,1,7,4};
        System.out.println("test: " + sol.minAbsoluteSumDiff(nums1, nums2));
    }
}