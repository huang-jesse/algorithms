import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

class SolutionFisherYates {
    private int[] nums;
    private Random random = new Random();
    public SolutionFisherYates(int[] nums) {
        this.nums = nums;
    }

    public int[] reset() {
        return nums;
    }

    public int[] shuffle() {
        int n = nums.length;
        int[] shuffled = nums.clone();
        for (int i = 0; i < n; i++) {
            // random index in [i, n-1]
            int index = random.nextInt(n-i) + i;
            // swap i and index
            int temp = shuffled[i];
            shuffled[i] = shuffled[index];
            shuffled[index] = temp;
        }
        return shuffled;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        SolutionFisherYates sol = new SolutionFisherYates(nums);
        int[] param_1 = sol.shuffle();
        int[] param_2 = sol.reset();
        System.out.println("param_1: " + Arrays.stream(param_1).boxed().collect(Collectors.toList()));
        System.out.println("param_2: " + Arrays.stream(param_2).boxed().collect(Collectors.toList()));
    }
}