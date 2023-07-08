import java.util.Arrays;
import java.util.stream.Collectors;

class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int n = numbers.length;
        if (n < 2) {
            return new int[]{-1, -1};
        }
        for (int i = 0; i < n - 1; i++) {
            int current = numbers[i];
            int searchIndex = binarySearch(numbers, i + 1, target - current);
            if (searchIndex > 0) {
                return new int[]{i + 1, searchIndex + 1};
            }
        }
        return new int[]{-1, -1};
    }

    private int binarySearch(int[] numbers, int start, int target) {
        int l = start;
        int r = numbers.length - 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (numbers[mid] == target) {
                return mid;
            }
            if (numbers[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] numbers = {2,7,11,15};
        int target = 9;
        System.out.println("test: " + Arrays.stream(sol.twoSum(numbers, target)).boxed().collect(Collectors.toList()));
    }
}