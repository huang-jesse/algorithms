import java.util.Arrays;
import java.util.TreeSet;
import java.util.stream.Collectors;

class Solution {
    public int maxChunksToSorted(int[] arr) {
        int ans = 0;
        TreeSet<Integer> ts = new TreeSet<>(Arrays.stream(arr).boxed().collect(Collectors.toList()));
        int max = -1;
        for (int num : arr) {
            ts.remove(num);
            if (num > max) {
                max = num;
            }
            if (ts.isEmpty() || max < ts.first()) {
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] arr = {1,2,0,3};
        System.out.println("test: " + sol.maxChunksToSorted(arr));
    }
}