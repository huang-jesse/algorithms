import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

class Solution {
    private int[] nums;
    private List<Integer> numList;
    public Solution(int[] nums) {
        this.nums = nums;
        this.numList = Arrays.stream(nums).boxed().collect(Collectors.toList());
    }

    public int[] reset() {
        return nums;
    }

    public int[] shuffle() {
        int n = nums.length;
        int[] shuffled = new int[n];
        List<Integer> tempList = new ArrayList<>(numList);
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            int index = random.nextInt(tempList.size());
            shuffled[i] = tempList.remove(index);
        }
        return shuffled;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        Solution sol = new Solution(nums);
        int[] param_1 = sol.shuffle();
        int[] param_2 = sol.reset();
        System.out.println("param_1: " + Arrays.stream(param_1).boxed().collect(Collectors.toList()));
        System.out.println("param_2: " + Arrays.stream(param_2).boxed().collect(Collectors.toList()));
    }
}