import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int numberOfWeakCharacters(int[][] properties) {
        Comparator<int[]> compare = (o1, o2) -> {
            // compare attack desc
            int compareAttack = o2[0] - o1[0];
            if (compareAttack == 0) {
                // compare defense asc
                return o1[1] - o2[1];
            } else {
                return compareAttack;
            }
        };
        Arrays.sort(properties, compare);
        int ans = 0;
        int maxDefense = 0;
        for (int[] property : properties) {
            int defense = property[1];
            if (defense < maxDefense) {
                ans++;
            }
            maxDefense = Math.max(maxDefense, defense);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] properties = {{1,5},{10,4},{4,3}};
        System.out.println("test: " + sol.numberOfWeakCharacters(properties));
    }
}