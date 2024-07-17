import java.util.Arrays;

class Solution {
    public int maxIceCream(int[] costs, int coins) {
        Arrays.sort(costs);
        int count = 0;
        for (int i = 0; i < costs.length; i++) {
            if (coins >= costs[i]) {
                count++;
                coins -= costs[i];
            } else {
                return count;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] costs = new int[]{1,6,3,1,2,5};
        int coins = 20;
        Solution sol = new Solution();
        System.out.println("maxIceCream: "+ sol.maxIceCream(costs, coins));
    }
}