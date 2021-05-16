import java.util.HashSet;
import java.util.Set;

class Solution {
    /*
     * x
     * 000 0000 0000 0000 0000 0000 0000 0000
     * 30,29,28 27,26,25,24 ... 3,2,1,0
     * preKx = preKaj ^ preKai -> preKaj = preKx ^ preKai
     */
    static final int MAX_BITS = 30;// from 0 ~ 30
    public int findMaximumXOR(int[] nums) {
        int x = 0;
        int n = nums.length;
        // find each bits
        for (int k = MAX_BITS; k >= 0; k--) {
            // set of preKaj
            Set<Integer> set = new HashSet<>();
            for (int j = 0; j < n; j++) {
                int preKaj = nums[j] >> k;
                set.add(preKaj);
            }
            // check if preKxNext can be found
            int preKxNext = x*2 + 1;// like 0001 -> 0011
            boolean found = false;
            for (int i = 0; i < n; i++) {
                int preKai = nums[i] >> k;
                found = set.contains(preKxNext ^ preKai);
                if (found) {
                    break;
                }
            }
            if (found) {
                x = preKxNext;
            } else {
                x = preKxNext - 1;// x = x*2, like 0011 -> 0010
            }
        }
        return x;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = new int[]{3,10,5,25,2,8};
        System.out.println("test: " + sol.findMaximumXOR(nums));
    }
}