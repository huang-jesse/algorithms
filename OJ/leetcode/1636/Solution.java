import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

class Solution {
    public int[] frequencySort(int[] nums) {
        Map<Integer, Integer> counter = new HashMap<>();
        for (int num : nums) {
            counter.put(num, counter.getOrDefault(num, 0) + 1);
        }
        nums = Arrays.stream(nums).boxed().sorted((o1, o2) -> {
            int compare = counter.get(o1) - counter.get(o2);
            if (compare == 0) {
                return o2 - o1;
            } else {
                return compare;
            }
        }).mapToInt(num -> num).toArray();
        return nums;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1,1,2,2,2,3};
        System.out.println("test: " + Arrays.stream(sol.frequencySort(nums)).boxed().collect(Collectors.toList()));
        Integer a = 0;
        a.hashCode();
    }
}