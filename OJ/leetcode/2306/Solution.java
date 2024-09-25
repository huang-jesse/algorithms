import java.util.HashMap;
import java.util.Map;

class Solution {
    public long distinctNames(String[] ideas) {
        // {suffix, mask}
        Map<String, Integer> suffixMasks = new HashMap<>();
        for (String idea : ideas) {
            String suffix = idea.substring(1);
            int mask = suffixMasks.getOrDefault(suffix, 0);
            int cur = idea.charAt(0) - 'a';
            mask |= 1 << cur;
            suffixMasks.put(suffix, mask);
        }

        int[][] swapCounter = new int[26][26];
        for (String idea : ideas) {
            String suffix = idea.substring(1);
            int mask = suffixMasks.get(suffix);
            int cur = idea.charAt(0) - 'a';
            for (int i = 0; i < 26; i++) {
                if (((mask >> i) & 1) == 0) {
                    swapCounter[cur][i]++;
                }
            }
        }

        long ans = 0;
        for (String idea : ideas) {
            String suffix = idea.substring(1);
            int mask = suffixMasks.get(suffix);
            int cur = idea.charAt(0) - 'a';
            for (int i = 0; i < 26; i++) {
                if (((mask >> i) & 1) == 0) {
                    ans += swapCounter[i][cur];
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // String[] ideas = {"coffee","donuts","time","toffee"}; // res: 6
        // String[] ideas = {"kofe","refqlthagu","mofe","ff","veyg","q","jzdi"}; // res: 40
        String[] ideas = {"aaa","baa","caa","bbb","cbb","dbb"}; // res: 2
        System.out.println("test: " + sol.distinctNames(ideas));
    }
}