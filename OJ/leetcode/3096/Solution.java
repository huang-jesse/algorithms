import java.util.Arrays;

class Solution {
    public int minimumLevels(int[] possible) {
        int n = possible.length;
        for (int i = 0; i < n; i++) {
            if (possible[i] == 0) {
                possible[i] = -1;
            }
        }
        int totalPoints = Arrays.stream(possible).sum();
        int alicePoints = 0;
        for (int i = 0; i < n - 1; i++) {
            alicePoints += possible[i];
            int bobPoints = totalPoints - alicePoints;
            if (alicePoints > bobPoints) {
                return i + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[] possible = {1,1,1,1,1}; // 3
        int[] possible = {0,0}; // -1
        System.out.println("test: " + sol.minimumLevels(possible));
    }
}