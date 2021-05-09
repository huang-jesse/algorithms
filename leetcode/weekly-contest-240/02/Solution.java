import java.util.TreeMap;

class Solution {
    public int maxDistance(int[] nums1, int[] nums2) {
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for (int j = 0; j < nums2.length; j++) {
            tm.put(nums2[j], j);
        }
        int ans = 0;
        for (int i = 0; i < nums1.length; i++) {
            Integer ceiling = tm.ceilingKey(nums1[i]);
            if (ceiling != null) {
                int j = tm.get(ceiling);
                if (i <= j) {
                    ans = Math.max(ans, j-i);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // System.out.println("test: " + sol.test());
    }
}