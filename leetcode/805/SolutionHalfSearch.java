import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class SolutionHalfSearch {
    public boolean splitArraySameAverage(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return false;
        }
        int sum = Arrays.stream(nums).sum();
        // The first half bit manipulation
        // {tot, cntSet}
        Map<Integer, Set<Integer>> firstHalfMap = new HashMap<>();
        int firstHalf = n / 2;
        int maskLimit = (1 << firstHalf) - 1;
        for (int mask = 0; mask <= maskLimit; mask++) {
            int tot = 0;
            int cnt = 0;
            for (int i = 0; i < firstHalf; i++) {
                if (((mask >> i) & 1) == 1) {
                    tot += nums[i];
                    cnt++;
                }
            }
            Set<Integer> cntSet = firstHalfMap.getOrDefault(tot, new HashSet<>());
            cntSet.add(cnt);
            firstHalfMap.put(tot, cntSet);
        }
        // The second half bit manipulation
        int secondHalf = n - firstHalf;
        maskLimit = (1 << secondHalf) - 1;
        for (int mask = 0; mask <= maskLimit; mask++) {
            int tot = 0;
            int cnt = 0;
            for (int i = firstHalf; i < n; i++) {
                if (((mask >> (i - firstHalf)) & 1) == 1) {
                    tot += nums[i];
                    cnt++;
                }
            }
            for (int k = Math.max(1, cnt); k < n; k++) {
                if (k * sum % n != 0) {
                    continue;
                }
                int t = k * sum / n;
                if (!firstHalfMap.containsKey(t - tot)) {
                    continue;
                }
                if (!firstHalfMap.get(t - tot).contains(k - cnt)) {
                    continue;
                }
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        SolutionHalfSearch sol = new SolutionHalfSearch();
        // int[] nums = {1,8,15};
        // int[] nums = {1,2,3,4,5,6,7,8};
        int[] nums = {72,56,81,54,15,96,80,90,8};
        System.out.println("test: " + sol.splitArraySameAverage(nums));
    }
}