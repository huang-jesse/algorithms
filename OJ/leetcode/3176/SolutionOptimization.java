import java.util.HashMap;
import java.util.Map;

class SolutionOptimization {
    public int maximumLength(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, int[]> dp = new HashMap<>();
        int[] zd = new int[k + 1];
        for (int i = 0; i < n; i++) {
            int[] temp = dp.computeIfAbsent(nums[i], x -> new int[k + 1]);
            for (int j = 0; j <= k; j++) {
                temp[j] = temp[j] + 1;
                if (j > 0) {
                    temp[j] = Math.max(temp[j], zd[j - 1] + 1);
                }
            }
            for (int j = 0; j <= k; j++) {
                zd[j] = Math.max(zd[j], temp[j]);
                if (j > 0) {
                    zd[j] = Math.max(zd[j], zd[j - 1]);
                }
            }
        }
        return zd[k];
    }

    public static void main(String[] args) {
        SolutionOptimization sol = new SolutionOptimization();
        int[] nums = {1,2,1,1,3};
        int k = 2;
        System.out.println("test: " + sol.maximumLength(nums, k));
    }
}