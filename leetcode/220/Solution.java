import java.util.TreeSet;

class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (k == 0 || nums.length < 2) return false;

        int n = nums.length - 1;
        int i = 1;
        // initailize set of slide window
        TreeSet<Long> set = new TreeSet<>();
        // iterator
        while(i <= n) {
            set.add((long)nums[i-1]);
            // find
            Long ceiling = set.floor((long)nums[i] + (long)t);
            long floor = (long)nums[i] - (long)t;
            if (ceiling != null && ceiling >= floor) {
                return true;
            }
            if (set.size() >= k) {
                // remove boundary of left
                set.remove((long)nums[i - k]);
            }
            i++;
        }
        return false;
    }

    public static void main(String[] args) {
        // int[] nums = new int[] {1,5,9,1,5,9};
        int[] nums = new int[] {1,2,2,3,4,5};
        int k = 2;
        int t = 0;
        Solution sol = new Solution();
        System.out.println("test: "+ sol.containsNearbyAlmostDuplicate(nums, k, t));

    }
}