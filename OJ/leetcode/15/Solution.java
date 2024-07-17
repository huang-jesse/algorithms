import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return Collections.emptyList();
        }
        if (n == 3) {
            int threeSum = Arrays.stream(nums).sum();
            if (threeSum == 0) {
                return Arrays.asList(Arrays.stream(nums).boxed().collect(Collectors.toList()));
            }
            return Collections.emptyList();
        }

        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        Map<Integer, Map<Integer, Integer>> visitedThreeSum = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int firstNum = nums[i];
            int twoSum = 0 - firstNum;
            Map<Integer, Integer> numCountMap = new HashMap<>();
            for (int j = i+1; j < n; j++) {
                int curNum = nums[j];
                numCountMap.put(curNum, numCountMap.getOrDefault(curNum, 0) + 1);
            }
            for (int j = i+1; j < n; j++) {
                int secondNum = nums[j];
                int thirdNum = twoSum - secondNum;
                numCountMap.put(secondNum, numCountMap.get(secondNum) - 1);
                if (numCountMap.get(secondNum) == 0) {
                    numCountMap.remove(secondNum);
                }

                if (numCountMap.containsKey(thirdNum)) {
                    Map<Integer, Integer> twoSumMap = visitedThreeSum.getOrDefault(firstNum, new HashMap<>());
                    if (twoSumMap.containsKey(secondNum) && twoSumMap.get(secondNum) == thirdNum) {
                        // visited
                        continue;
                    } else {
                        twoSumMap.put(secondNum, thirdNum);
                        visitedThreeSum.put(firstNum, twoSumMap);
                    }

                    List<Integer> threeSumList = Arrays.asList(firstNum, secondNum, thirdNum);
                    ans.add(threeSumList);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {-1,0,1,2,-1,-4};
        System.out.println("test: " + sol.threeSum(nums));
    }
}