import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

class Solution {
    public long maximumSumOfHeights(List<Integer> maxHeights) {
        int n = maxHeights.size();
        // [height, count]
        Deque<int[]> stack = new ArrayDeque<>();
        long[] prefixSumArr = new long[n];
        long preSum = 0;
        for (int i = 0; i < n; i++) {
            int height = maxHeights.get(i);
            preSum += height;
            int count = 1;
            // 扣减 height 比当前项更高的数值（必须为从左到右非递减状态）
            while (!stack.isEmpty() && stack.peek()[0] > height) {
                int[] preHeightInfo = stack.pop();
                preSum -= (long)(preHeightInfo[0] - height) * preHeightInfo[1];
                // 重新将 preHeightInfo[1] 项，以 height 的高度加入栈中
                count += preHeightInfo[1];
            }
            // 累加
            prefixSumArr[i] = preSum;
            // 入栈
            stack.push(new int[]{height, count});
        }
        stack.clear();
        long[] suffixSumArr = new long[n];
        long suffixSum = 0;
        for (int i = n - 1; i >= 0; i--) {
            int height = maxHeights.get(i);
            suffixSum += height;
            int count = 1;
            // 扣减 height 比当前项更高的数值（必须为从右到左非递减状态）
            while (!stack.isEmpty() && stack.peek()[0] > height) {
                int[] suffixHeightInfo = stack.pop();
                suffixSum -= (long)(suffixHeightInfo[0] - height) * suffixHeightInfo[1];
                // 重新将 suffixHeightInfo[1] 项，以 height 的高度加入栈中
                count += suffixHeightInfo[1];
            }
            // 累加
            suffixSumArr[i] = suffixSum;
            // 入栈
            stack.push(new int[]{height, count});
        }
        long ans = 0;
        // 计算以 i 为峰值时的答案
        for (int i = 0; i < n; i++) {
            long res = prefixSumArr[i] + suffixSumArr[i] - maxHeights.get(i);
            ans = Math.max(ans, res);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // List<Integer> maxHeights = Arrays.asList(5,3,4,1,1);
        // List<Integer> maxHeights = Arrays.asList(6,5,3,9,2,7);

        List<Integer> maxHeights = Arrays.asList(528947205,446572672,245562434,29067688,230940022,237916991,546243960,898360263,110809423,616740838,141189952,272319413,173945434,944209607,709147298,291516002,697109513,945372506,321357175,271013697,140905315,154122459,745184252,379717676,468969774,302949377,261867781,933328834,791272529,583341139,83259489,304055888,873655860,808520318,194518325,703266873,281331446,927210596,422874036,902047413,774474564,809609201,493545785,415250991,884864225,392444408,610159103,903379193,816412790,591760883,361140449,759810143,588717612,694204370,42665571,517507625,702706955,952884579);
        // 11903251647
        System.out.println("test: " + sol.maximumSumOfHeights(maxHeights));
    }
}