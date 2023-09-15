import java.util.Arrays;

class Solution {
    public int giveGem(int[] gem, int[][] operations) {
        for (int[] operation : operations) {
            int num = gem[operation[0]] / 2;
            gem[operation[0]] -= num;
            gem[operation[1]] += num;
        }
        int max = Arrays.stream(gem).max().getAsInt();
        int min = Arrays.stream(gem).min().getAsInt();
        return max - min;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] gem = {3,1,2};
        int[][] operations = {{0,2}, {2,1}, {2, 0}};
        System.out.println("test: " + sol.giveGem(gem, operations));
    }
}