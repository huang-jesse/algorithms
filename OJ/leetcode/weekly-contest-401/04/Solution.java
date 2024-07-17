import java.math.BigInteger;
import java.util.Arrays;

class Solution {
    public int maxTotalReward(int[] rewardValues) {
        rewardValues = Arrays.stream(rewardValues).distinct().sorted().toArray();
        int n = rewardValues.length;
        // bitSet.
        BigInteger dp = BigInteger.ONE;
        dp.setBit(0);
        for (int i = 0; i < n; i++) {
            int v = rewardValues[i];
            // 0 <= j < v
            BigInteger mask = BigInteger.ONE.shiftLeft(v).subtract(BigInteger.ONE);
            dp = dp.or(dp.and(mask).shiftLeft(v));
        }
        return dp.bitLength() - 1;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[] rewardValues = {1,6,4,3,2};
        int[] rewardValues = {1,1,3,3};
        System.out.println("test: " + sol.maxTotalReward(rewardValues));
    }
}