import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 参考：https://leetcode.cn/problems/reducing-dishes/solutions/2492854/mei-ju-zuo-ji-dao-cai-tan-xin-pythonjava-k7w2/
 */
class SolutionOptimization {
    private static final int INF = 0x3fffffff;
    public int maxSatisfaction(int[] satisfaction) {
        int n = satisfaction.length;
        Arrays.sort(satisfaction);
        int ans = 0;
        int s = 0;
        for (int i = n - 1; i >= 0; i--) {
            s += satisfaction[i];
            if (s > 0) {
                ans += s; // f(k) = f(k - 1) + s
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        SolutionOptimization sol = new SolutionOptimization();
        int[] satisfaction = {-1,-8,0,5,-9};
        System.out.println("test: " + sol.maxSatisfaction(satisfaction));
    }
}