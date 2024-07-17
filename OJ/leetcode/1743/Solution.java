import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Solution {
    public int[] restoreArray(int[][] adjacentPairs) {
        int n = adjacentPairs.length + 1;
        Map<Integer, List<Integer>> adjacentMap = new HashMap<>();
        for (int[] adjacent : adjacentPairs) {
            int adjacent1 = adjacent[0];
            int adjacent2 = adjacent[1];

            List<Integer> neighborList1 = adjacentMap.getOrDefault(adjacent1, new ArrayList<>());
            neighborList1.add(adjacent2);
            adjacentMap.put(adjacent1, neighborList1);

            List<Integer> neighborList2 = adjacentMap.getOrDefault(adjacent2, new ArrayList<>());
            neighborList2.add(adjacent1);
            adjacentMap.put(adjacent2, neighborList2);
        }

        int first = Integer.MIN_VALUE;
        for (Map.Entry<Integer, List<Integer>> entry : adjacentMap.entrySet()) {
            if (entry.getValue().size() == 1) {
                first = entry.getKey();
                break;
            }
        }

        int[] ans = new int[n];
        ans[0] = first;
        ans[1] = adjacentMap.get(first).get(0);
        for (int i = 2; i < n; i++) {
            int pre = ans[i-1];
            int preNeighbor = ans[i-2];
            List<Integer> preNeighborList = adjacentMap.get(pre);
            if (preNeighbor == preNeighborList.get(0)) {
                ans[i] = preNeighborList.get(1);
            } else {
                ans[i] = preNeighborList.get(0);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] adjacentPairs = {{2,1},{3,4},{3,2}};
        List<Integer> ans = Arrays.stream(sol.restoreArray(adjacentPairs)).boxed().collect(Collectors.toList());
        System.out.println("test: " + ans);
    }
}