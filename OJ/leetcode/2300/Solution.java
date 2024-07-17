import java.util.Arrays;

class Solution {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int n = spells.length;
        Arrays.sort(potions);
        int[] pairs = new int[n];
        for (int i = 0; i < n; i++) {
            int pairCount = binarySearchLeftBoundary(potions, spells[i], success);
            pairs[i] = pairCount;
        }
        return pairs;
    }

    private int binarySearchLeftBoundary(int[] potions, int spell, long success) {
        if ((long)potions[potions.length - 1] * spell < success) {
            return 0;
        }
        int left = 0;
        int right = potions.length - 1;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if ((long)potions[mid] * spell >= success) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        int ans = potions.length - left;
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] spells = {5,1,3};
        int[] potions = {1,2,3,4,5};
        long success = 7;
        System.out.println("test: " + Arrays.toString(sol.successfulPairs(spells, potions, success)));
    }
}