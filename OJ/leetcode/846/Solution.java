import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        int n = hand.length;
        if (n % groupSize != 0) {
            return false;
        }
        Arrays.sort(hand);
        Map<Integer, Integer> numCounter = new HashMap<>();
        for (int num : hand) {
            numCounter.put(num, numCounter.getOrDefault(num, 0) + 1);
        }
        for (int i = 0; i < n; i++) {
            int num = hand[i];
            if (!numCounter.containsKey(num)) {
                continue;
            }
            int j = 0;
            while (j < groupSize) {
                int curNum = num+j;
                if (!numCounter.containsKey(curNum)) {
                    return false;
                }
                numCounter.put(curNum, numCounter.getOrDefault(curNum, 0)-1);
                if (numCounter.get(curNum) == 0) {
                    numCounter.remove(curNum);
                }
                j++;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] hand = {1,2,3,6,2,3,4,7,8};
        int groupSize = 3;
        System.out.println("test: " + sol.isNStraightHand(hand, groupSize));
    }
}