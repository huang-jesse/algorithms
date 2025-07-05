import java.util.ArrayList;
import java.util.List;

/**
 * 把偶数视作空位，奇数视作汽车。
 * 只需关注汽车，当所有汽车都移动到偶数（奇数）下标，所有空位就一定都位于奇数（偶数）下标。
 * 比如汽车的位置为 a=[3,4,5]，目标位置为 b=[1,3,5]。贪心地，最左边的汽车移动到最左边的目标位置，比移动到其他位置更优。如果最左边的汽车没有移动到最左边的目标位置，那么其他更靠右的汽车就要移动到最左边，这样移动的总距离是更大的。
 * 链接：https://leetcode.cn/problems/minimum-adjacent-swaps-to-alternate-parity/solutions/3705565/ba-ou-shu-shi-zuo-kong-wei-qi-shu-shi-zu-oqbi/
 */
class SolutionOptimization {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        List<Integer> pos1 = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] % 2 != 0) {
                pos1.add(i);
            }
        }
        if (Math.abs(pos1.size() * 2 - n) > 1) return -1;
        int res = Math.min(calc(n, pos1, 0), calc(n, pos1, 1));
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    /**
     * start=0 表示车要去偶数下标，start=1 表示车要去奇数下标
     * @param n
     * @param pos1
     * @param start
     * @return
     */
    private static int calc(int n, List<Integer> pos1, int start) {
        if ((n - start + 1) / 2 != pos1.size()) {
            return Integer.MAX_VALUE;
        }
        int res = 0;
        for (int i = 0; i < pos1.size(); i++) {
            // 汽车的目标地址（空位）
            int targetIdx = i * 2 + start;
            res += Math.abs(targetIdx - pos1.get(i));
        }
        return res;
    }

    public static void main(String[] args) {
        SolutionOptimization sol = new SolutionOptimization();
        // int[] nums = {2,4,6,5,7};
        int[] nums = {2,4,5,7};
        System.out.println("test: " + sol.minSwaps(nums));
    }
}