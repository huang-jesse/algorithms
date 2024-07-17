import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Solution {
    public int[] arrayRankTransform(int[] arr) {
        int n = arr.length;
        int[] sortedArr = arr.clone();
        Arrays.sort(sortedArr);
        Map<Integer, Integer> rankMap = new HashMap<>();
        for (int num : sortedArr) {
            if (!rankMap.containsKey(num)) {
                rankMap.put(num, rankMap.size()+1);
            }
        }
        for (int i = 0; i < n; i++) {
            arr[i] = rankMap.get(arr[i]);
        }
        return arr;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] arr = {37,12,28,9,100,56,80,5,12};
        List<Integer> ans = Arrays.stream(sol.arrayRankTransform(arr)).boxed().collect(Collectors.toList());
        System.out.println("test: " + ans);
    }
}