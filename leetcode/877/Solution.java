import java.util.HashMap;
import java.util.Map;

class Solution {
    Map<String, int[]> memo = new HashMap<>();
    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        int[] count = dfs(piles, 0, n-1);
        return count[0] > count[1];
    }

    private int[] dfs(int[] piles, int left, int right) {
        if (left > right) return new int[]{0, 0};
        String key = left + "_" + right;
        if (this.memo.containsKey(key)) {
            return this.memo.get(key);
        }

        int range = right - left + 1;
        int[] pickLeftCount = dfs(piles, left+1, right);
        int[] pickRightCount = dfs(piles, left, right-1);
        int[] returnArr;
        if (range % 2 == 0) {
            // round of alex
            if (piles[left] + pickLeftCount[0] > piles[right] + pickRightCount[0]) {
                returnArr = new int[]{pickLeftCount[0] + piles[left], pickLeftCount[1]};
            } else {
                returnArr = new int[]{pickRightCount[0] + piles[right], pickRightCount[1]};
            }
        } else {
            // round of lee
            if (piles[left] + pickLeftCount[1] > piles[right] + pickRightCount[1]) {
                returnArr = new int[]{pickLeftCount[0], pickLeftCount[1] + piles[left]};
            } else {
                returnArr = new int[]{pickRightCount[0], pickRightCount[1] + piles[right]};
            }
        }
        this.memo.put(key, returnArr);
        return returnArr;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] piles = {5,3,4,5};
        System.out.println("test: " + sol.stoneGame(piles));
    }
}