import java.util.HashSet;
import java.util.Set;

class Solution {
    public int numJewelsInStones(String jewels, String stones) {
        Set<Character> jewelsSet = new HashSet<>();
        for (int i = 0; i < jewels.length(); i++) {
            jewelsSet.add(jewels.charAt(i));
        }
        int ans = 0;
        for (int i = 0; i < stones.length(); i++) {
            if (jewelsSet.contains(stones.charAt(i))) {
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String jewels = "aA";
        String stones = "aAAbbbb";
        System.out.println("test: " + sol.numJewelsInStones(jewels, stones));
    }
}