import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public List<Long> maximumEvenSplit(long finalSum) {
        if (finalSum % 2 != 0) {
            return Collections.emptyList();
        }
        long numOfTwo = finalSum / 2;
        long numOfMaxEven = binarySearchNumOfMaxEven(numOfTwo);
        // Do not add the last even num
        numOfMaxEven--;
        List<Long> ans = new ArrayList<>();
        long evenNum = 2L;
        while (numOfMaxEven > 0) {
            numOfMaxEven--;
            finalSum -= evenNum;
            ans.add(evenNum);
            evenNum += 2L;
        }
        // Add the final even num
        ans.add(finalSum);
        return ans;
    }

    private long binarySearchNumOfMaxEven(long numOfTwo) {
        // There has x, 1 <= x <= sqrt(2 * numOfTwo), then numOfTwo = x * (1 + x) / 2, we will found the numOfMaxEven by binarySearch;
        long left = 1L;
        long right = (long)Math.sqrt(2 * numOfTwo);
        // RightBoundary search
        while (left < right) {
            long mid = left + ((right - left + 1) >> 1);
            long result = mid * (1 + mid) / 2;
            if (result <= numOfTwo) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // long finalSum = 7L;
        long finalSum = 28L;
        System.out.println("test: " + sol.maximumEvenSplit(finalSum));
    }
}