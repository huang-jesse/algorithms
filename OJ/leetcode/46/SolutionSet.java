import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class SolutionSet {
    private List<List<Integer>> allPermute;
    public List<List<Integer>> permute(int[] nums) {
        this.allPermute = new ArrayList<>();
        int n = nums.length;
        Set<Integer> remainIndexes = IntStream.range(0, n).boxed().collect(Collectors.toSet());
        backtrack(nums, remainIndexes, new ArrayList<>());
        return allPermute;
    }

    private void backtrack(int[] nums, Set<Integer> remainIndexes, List<Integer> curPermute) {
        int n = nums.length;
        int index = curPermute.size();
        if (index == n) {
            allPermute.add(new ArrayList<>(curPermute));
            return;
        }
        Integer[] indexesPickList = remainIndexes.toArray(Integer[]::new);
        for (int pickIndex : indexesPickList) {
            int curPick = nums[pickIndex];
            curPermute.add(curPick);
            remainIndexes.remove(pickIndex);
            // pick next
            backtrack(nums, remainIndexes, curPermute);
            curPermute.remove(index);
            remainIndexes.add(pickIndex);
        }
    }

    public static void main(String[] args) {
        SolutionSet sol = new SolutionSet();
        int[] nums = {1,2,3};
        System.out.println("test: " + sol.permute(nums));
    }
}