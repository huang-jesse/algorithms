import java.util.Arrays;

class Solution {
    public int maxIceCream(int[] costs, int coins) {
        int n = costs.length;
        Arrays.sort(costs);
        int index = 0;
        while (coins > 0 && index < n) {
            int cost = costs[index];
            if (coins >= cost) {
                index++;
                coins -= cost;
            } else {
                break;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] costs = {1,3,2,4,1};
        int coins = 7;
        System.out.println("test: " + sol.maxIceCream(costs, coins));
    }
}