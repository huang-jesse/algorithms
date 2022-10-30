import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    class Creator {
        private long viewSum;
        private int mostView = -1;
        private String id;
    }
    public List<List<String>> mostPopularCreator(String[] creators, String[] ids, int[] views) {
        Map<String, Creator> creatorMap = new HashMap<>();
        int n = creators.length;
        long maxViewSum = 0;
        for (int i = 0; i < n; i++) {
            String creatorName = creators[i];
            int curView = views[i];
            String curId = ids[i];
            Creator creator = creatorMap.getOrDefault(creatorName, new Creator());
            creator.viewSum += curView;
            if (curView == creator.mostView && curId.compareTo(creator.id) < 0) {
                creator.id = curId;
            } else if (curView > creator.mostView) {
                creator.mostView = curView;
                creator.id = curId;
            }
            creatorMap.put(creatorName, creator);
            maxViewSum = Math.max(maxViewSum, creator.viewSum);
        }
        List<List<String>> ans = new ArrayList<>();
        for (Map.Entry<String, Creator> entry : creatorMap.entrySet()) {
            Creator curCreator = entry.getValue();
            String curCreatorName = entry.getKey();
            if (curCreator.viewSum != maxViewSum) {
                continue;
            }
            ans.add(Arrays.asList(curCreatorName, curCreator.id));
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // String[] creators = {"a","b"};
        // String[] ids = {"a","a"};
        // int[] views = {1,0};
        String[] creators = {"alice","bob","alice","chris"};
        String[] ids = {"one","two","three","four"};
        int[] views = {5,10,5,4};
        System.out.println("test: " + sol.mostPopularCreator(creators, ids, views));
    }
}