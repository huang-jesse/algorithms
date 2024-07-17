import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> findWinners(int[][] matches) {
        int max = 0;
        for (int[] match : matches) {
            max = Math.max(max, Math.max(match[0], match[1]));
        }
        int[] lossesCounter = new int[max + 1];
        Arrays.fill(lossesCounter, -1);
        for (int[] match : matches) {
            if (lossesCounter[match[0]] == -1) {
                lossesCounter[match[0]] = 0;
            }
            if (lossesCounter[match[1]] == -1) {
                lossesCounter[match[1]] = 0;
            }
            lossesCounter[match[1]]++;
        }
        List<Integer> lossesZero = new ArrayList<>();
        List<Integer> lossesOne = new ArrayList<>();
        for (int i = 0; i <= max; i++) {
            if (lossesCounter[i] == 0) {
                lossesZero.add(i);
            } else if (lossesCounter[i] == 1) {
                lossesOne.add(i);
            }
        }
        return Arrays.asList(lossesZero, lossesOne);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] matches = {{1,3},{2,3},{3,6},{5,6},{5,7},{4,5},{4,8},{4,9},{10,4},{10,9}};
        System.out.println("test: " + sol.findWinners(matches));
    }
}