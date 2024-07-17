import java.util.Arrays;
import java.util.stream.Collectors;

class SolutionDualPointer {
    public int[] twoSum(int[] numbers, int target) {
        int n = numbers.length;
        if (n < 2) {
            return new int[]{-1, -1};
        }
        int l = 0;
        int r = n - 1;
        while (l < r) {
            int currentSum = numbers[l] + numbers[r];
            if (currentSum == target) {
                return new int[]{l + 1, r + 1};
            }
            if (currentSum < target) {
                l++;
            } else {
                r--;
            }
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        SolutionDualPointer sol = new SolutionDualPointer();
        int[] numbers = {2,7,11,15};
        int target = 9;
        System.out.println("test: " + Arrays.stream(sol.twoSum(numbers, target)).boxed().collect(Collectors.toList()));
    }
}