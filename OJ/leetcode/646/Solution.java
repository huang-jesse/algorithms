import java.util.Map;
import java.util.TreeMap;

class Solution {
    public int findLongestChain(int[][] pairs) {
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for (int[] pair : pairs) {
            int first = pair[0];
            int second = pair[1];
            // {key: second, value: first}
            tm.put(second, Math.max(tm.getOrDefault(second, Integer.MIN_VALUE), first));
        }
        int ans = 0;
        int[] cur = {Integer.MIN_VALUE, Integer.MIN_VALUE};// {first, second}
        while (!tm.isEmpty()) {
            int curSecond = cur[1];
            Map.Entry<Integer, Integer> entry = tm.pollFirstEntry();
            int entryFirst = entry.getValue();
            int entrySecond = entry.getKey();
            if (curSecond < entryFirst) {
                ans++;
                cur = new int[]{entryFirst, entrySecond};
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[][] pairs = {{1,2}, {2,3}, {2,4}};
        // int[][] pairs = {{1,2}, {2,3}, {3,10}, {4,5}, {6,7}};
        int[][] pairs = {{-10,-8},{8,9},{-5,0},{6,10},{-6,-4},{1,7},{9,10},{-4,7}};
        // int[][] pairs = {{1,2}, {2,4}, {3,4}, {3,5}, {5,7}, {5,8}};
        // int[][] pairs = {{1,2}};
        // int[][] pairs = {{1,2}, {2,3}, {3,4}};
        System.out.println("test: " + sol.findLongestChain(pairs));
    }
}