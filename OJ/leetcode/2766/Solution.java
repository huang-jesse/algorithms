import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public List<Integer> relocateMarbles(int[] nums, int[] moveFrom, int[] moveTo) {
        Set<Integer> positions = new HashSet<>(nums.length);
        for (int num : nums) positions.add(num);
        int n = moveFrom.length;
        for (int i = 0; i < n; i++) {
            positions.remove(moveFrom[i]);
            positions.add(moveTo[i]);
        }
        List<Integer> res = new ArrayList<>(positions);
        res.sort(Integer::compareTo);
        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1,6,7,8};
        int[] moveFrom = {1,7,2};
        int[] moveTo = {2,9,5};
        System.out.println("test: " + sol.relocateMarbles(nums, moveFrom, moveTo));
    }
}