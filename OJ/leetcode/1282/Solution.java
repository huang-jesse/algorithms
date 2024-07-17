import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        Map<Integer, List<Integer>> groupSizesMap = new HashMap<>();
        int n = groupSizes.length;
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int size = groupSizes[i];
            if (size == 1) {
                // new a group for size one
                ans.add(Arrays.asList(i));
            } else {
                List<Integer> theSameSizeList = groupSizesMap.getOrDefault(size, new ArrayList<>());
                theSameSizeList.add(i);
                groupSizesMap.put(size, theSameSizeList);
            }
        }
        for (Map.Entry<Integer, List<Integer>> entry : groupSizesMap.entrySet()) {
            int groupSize = entry.getKey();
            List<Integer> theSameSizeList = entry.getValue();
            for (int i = 0; i < theSameSizeList.size(); i+=groupSize) {
                List<Integer> group = new ArrayList<>();
                for (int j = 0; j < groupSize; j++) {
                    group.add(theSameSizeList.get(i+j));
                }
                ans.add(group);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] groupSizes = {3,3,3,3,3,1,3};
        System.out.println("test: " + sol.groupThePeople(groupSizes));
    }
}