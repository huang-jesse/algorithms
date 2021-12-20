import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 查找左边界或大于等于目标值的最小值 (ceil)，若目标值大于数组中的最大值则返回数组最大值
 * 当我们将区间[l, r]划分成[l, mid]和[mid + 1, r]时，其更新操作是r = mid或者l = mid + 1;，计算mid时不需要加1。
 */
class BinarySearchLeftBound {
    /**
     * 查找左边界或大于等于目标值的最小值 (ceil)，若目标值大于数组中的最大值则返回数组最大值
     * @param nums
     * @param target
     * @return index of nums
     */
    public int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int mid = l + ((r - l) >> 1);
            if (nums[mid] >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        Random random = new Random();
        IntStream ints = random.ints(0, 10);
        int target = random.nextInt(10);
        int[] nums = ints.limit(10).toArray();
        Arrays.sort(nums);
        BinarySearchLeftBound binarySearch = new BinarySearchLeftBound();
        int ans = binarySearch.search(nums, target);
        System.out.println("nums: " + Arrays.stream(nums).boxed().collect(Collectors.toList()));
        System.out.println("target: " + target);
        System.out.println("ans index: " + ans);
        System.out.println("ans num: " + nums[ans]);
    }
}