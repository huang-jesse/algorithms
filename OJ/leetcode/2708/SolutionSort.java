import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class SolutionSort {
    public long maxStrength(int[] nums) {
        int n = nums.length;
        List<Integer> negatives = new ArrayList<>();
        long ans = 1L;
        int count = 0;
        int zeroCount = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                ans *= nums[i];
                count++;
            } else if (nums[i] < 0) {
                negatives.add(nums[i]);
            } else {
                // 0
                zeroCount++;
            }
        }
        // asc order;
        Collections.sort(negatives);
        int m = negatives.size();
        for (int i = 0; i + 1 < m; i += 2) {
            ans *= negatives.get(i) * negatives.get(i + 1);
            count++;
        }
        if (count > 0) {
            return ans;
        } else {
            if (zeroCount > 0) {
                return 0;
            } else {
                // negative
                return negatives.get(0);
            }
        }
    }

    public static void main(String[] args) {
        SolutionSort sol = new SolutionSort();
        int[] nums = {3,-1,-5,2,5,-9};
        System.out.println("test: " + sol.maxStrength(nums));
    }
}