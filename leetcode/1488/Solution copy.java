import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

class Solution {
    public int[] avoidFlood(int[] rains) {
        Map<Integer, Integer> fullLakes = new HashMap<>();
        TreeSet<Integer> dryDays = new TreeSet<>();
        int[] ans = new int[rains.length];
        Arrays.fill(ans, 1);
        for (int i = 0; i < rains.length; i++) {
            int lake = rains[i];
            if (lake == 0) {
                // dry day
                dryDays.add(i);
            } else {
                ans[i] = -1;
                if (fullLakes.containsKey(lake)) {
                    // need dry this lake before
                    int rainDay = fullLakes.get(lake);
                    Integer dryDay = dryDays.ceiling(rainDay);
                    if (dryDay == null) {
                        // can not avoid flood
                        return new int[]{};
                    } else {
                        dryDays.remove(dryDay);
                        ans[dryDay] = lake;
                    }
                }
                fullLakes.put(lake, i);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] rains = {1,2,0,0,2,1};
        // int[] rains = {0,1,1};
        System.out.println("test: " + Arrays.toString(sol.avoidFlood(rains)));
    }
}