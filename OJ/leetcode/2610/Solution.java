import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public List<List<Integer>> findMatrix(int[] nums) {
        Map<Integer, Integer> counter = new HashMap<>();
        for (int num : nums) {
            counter.merge(num, 1, Integer::sum);
        }
        List<List<Integer>> ans = new ArrayList<>();
        while (!counter.isEmpty()) {
            List<Integer> row = new ArrayList<>(counter.keySet());
            List<Integer> list = new ArrayList<>();
            for (Integer key : row) {
                list.add(key);
                int cnt = counter.get(key) - 1;
                if (cnt == 0) {
                    counter.remove(key);
                } else {
                    counter.put(key, cnt);
                }
            }
            ans.add(list);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1,3,4,1,2,3,1};
        System.out.println("test: " + sol.findMatrix(nums));
    }
}