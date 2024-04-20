import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Solution {
    public List<String> findHighAccessEmployees(List<List<String>> access_times) {
        Map<String, List<int[]>> accessMap = new HashMap<>();
        for (List<String> accessInfo : access_times) {
            String time = accessInfo.get(1);
            int[] access = new int[]{Integer.valueOf(time.substring(0, 2)), Integer.valueOf(time.substring(2, 4))};
            List<int[]> accessList = accessMap.getOrDefault(accessInfo.get(0), new ArrayList<>());
            accessList.add(access);
            accessMap.put(accessInfo.get(0), accessList);
        }
        List<String> ans = new ArrayList<>();
        for (Map.Entry<String, List<int[]>> entry : accessMap.entrySet()) {
            List<int[]> accessList = entry.getValue();
            accessList.sort((o1, o2) -> {
                int firstCompare = o1[0] - o2[0];
                if (firstCompare == 0) {
                    return o1[1] - o2[1];
                } else {
                    return firstCompare;
                }
            });
            int m = accessList.size();
            for (int i = 0; i < m - 2; i++) {
                int j = i + 2;
                int startHour = accessList.get(i)[0];
                int startMinute = accessList.get(i)[1];
                int endHour = accessList.get(j)[0];
                int endMinute = accessList.get(j)[1];
                if (endHour > startHour + 1 || (endHour == startHour + 1 && endMinute >= startMinute)) {
                    // invalid
                    continue;
                } else {
                    // valied
                    ans.add(entry.getKey());
                    break;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        List<List<String>> access_times = Arrays.asList(
            Arrays.asList("d","0002"), Arrays.asList("c","0808"), Arrays.asList("c","0829"), Arrays.asList("e","0215")
            , Arrays.asList("d","1508"), Arrays.asList("d","1444"), Arrays.asList("d","1410"), Arrays.asList("c","0809"));
        System.out.println("test: " + sol.findHighAccessEmployees(access_times));
    }
}