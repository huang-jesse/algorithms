import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public int numberOfPoints(List<List<Integer>> nums) {
        Set<Integer> set = new HashSet<>();
        for(List<Integer> num : nums) {
            for (int i = num.get(0); i <= num.get(1); i++) {
                set.add(i);
            }
        }
        return set.size();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        List<List<Integer>> nums = Arrays.asList(Arrays.asList());
        System.out.println("test: " + sol.numberOfPoints(nums));
    }
}