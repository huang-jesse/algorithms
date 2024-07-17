import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class SolutionMapCount {
    public int maxChunksToSorted(int[] arr) {
        int ans = 0;
        Map<Integer, Integer> counterMap = new HashMap<>();
        int[] sortedArr = arr.clone();
        Arrays.sort(sortedArr);
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            counterMap.put(num, counterMap.getOrDefault(num, 0) + 1);
            if (counterMap.get(num) == 0) {
                counterMap.remove(num);
            }
            int sortedNum = sortedArr[i];
            counterMap.put(sortedNum, counterMap.getOrDefault(sortedNum, 0) - 1);
            if (counterMap.get(sortedNum) == 0) {
                counterMap.remove(sortedNum);
            }
            if (counterMap.isEmpty()) {
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        SolutionMapCount sol = new SolutionMapCount();
        int[] arr = {2,4,1,3,4};
        // int[] arr = {2,1,3,4,4};
        System.out.println("test: " + sol.maxChunksToSorted(arr));
    }
}