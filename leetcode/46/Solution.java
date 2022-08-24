import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
    private List<List<Integer>> allPermute;
    public List<List<Integer>> permute(int[] nums) {
        this.allPermute = new ArrayList<>();
        List<Integer> curPermute = Arrays.stream(nums).boxed().collect(Collectors.toList());
        backtrack(nums, curPermute, 0);
        return allPermute;
    }

    private void backtrack(int[] nums, List<Integer> curPermute, int position) {
        int n = nums.length;
        if (position == n) {
            allPermute.add(new ArrayList<>(curPermute));
            return;
        }
        for (int i = position; i < n; i++) {
            // pick i
            Collections.swap(curPermute, i, position);
            // track next position
            backtrack(nums, curPermute, position + 1);
            // revert for next pick
            Collections.swap(curPermute, i, position);
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1,2,3,4};
        System.out.println("test: " + sol.permute(nums));
    }
}