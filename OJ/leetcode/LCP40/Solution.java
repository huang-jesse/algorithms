import java.util.Arrays;

class Solution {
    public int maxmiumScore(int[] cards, int cnt) {
        int n = cards.length;
        Arrays.sort(cards);
        int minOdd = -1;
        int minEven = -1;
        int sum = 0;
        for (int i = n - cnt; i < n; i++) {
            int cur = cards[i];
            if (minEven == -1 && cur %  2 == 0) {
                minEven = cur;
            }
            if (minOdd == -1 && cur % 2 == 1) {
                minOdd = cur;
            }
            sum += cur;
        }
        if (sum % 2 == 0) return sum;
        int res = 0;
        if (minOdd != -1) {
            // find max even num
            int maxEven = -1;
            for (int i = n - cnt - 1; i >= 0; i--) {
                if (cards[i] % 2 == 0) {
                    maxEven = cards[i];
                    break;
                }
            }
            if (maxEven != -1) {
                res = sum - minOdd + maxEven;
            }
        }
        if (minEven != -1) {
            // find max odd num
            int maxOdd = -1;
            for (int i = n - cnt - 1; i >= 0; i--) {
                if (cards[i] % 2 == 1) {
                    maxOdd = cards[i];
                    break;
                }
            }
            if (maxOdd != -1) {
                res = Math.max(res, sum - minEven + maxOdd);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[] cards = {1,2,8,9};
        // int cnt = 3;
        int[] cards = {1,1,1,1,1};
        int cnt = 3;
        System.out.println("test: " + sol.maxmiumScore(cards, cnt));
    }
}