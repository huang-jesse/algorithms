import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class SolutionCircleReplacement {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        int times = gcd(n, k);
        int perCircleSteps = n / times;
        for (int i = 0; i < times; i++) {
            int cur = nums[i];
            int nextIndex = (i + k) % n;
            int step = 0;
            while (step < perCircleSteps) {
                int next = nums[nextIndex];
                nums[nextIndex] = cur;
                cur = next;
                nextIndex = (nextIndex + k) % n;
                step++;
            }
        }
    }

    private static int gcd(int a, int b) {
        return b > 0 ? gcd(b, a % b) : a;
    }

    public static void main(String[] args) {
        SolutionCircleReplacement sol = new SolutionCircleReplacement();
        // int[] nums = {-1,-100,3,99};
        // int k = 2;
        // int[] nums = {1,2,3,4,5,6,7};
        // int k = 3;
        int[] nums = {1,2,3,4,5,6};
        int k = 3;
        List<Integer> numsList = Arrays.stream(nums).boxed().collect(Collectors.toList());
        System.out.println("numsList: " + numsList);
        sol.rotate(nums, k);
        List<Integer> numsRotatedList = Arrays.stream(nums).boxed().collect(Collectors.toList());
        System.out.println("numsRotatedList: " + numsRotatedList);
    }
}