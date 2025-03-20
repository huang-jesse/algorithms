import java.util.ArrayList;
import java.util.List;

class SolutionOptimization {
    public List<List<Integer>> findMatrix(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int[] counter = new int[nums.length + 1];
        for (int num : nums) {
            if (counter[num] >= ans.size()) {
                ans.add(new ArrayList<>());
            }
            ans.get(counter[num]).add(num);
            counter[num]++;
        }
        return ans;
    }

    public static void main(String[] args) {
        SolutionOptimization sol = new SolutionOptimization();
        int[] nums = {1,3,4,1,2,3,1};
        System.out.println("test: " + sol.findMatrix(nums));
    }
}