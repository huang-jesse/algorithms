import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 查找右边界或小于等于目标值的最大值 (floor)，若目标值小于数组最小值则返回数组最小值
 * 当我们将区间[l, r]划分成[l, mid - 1]和[mid, r]时，其更新操作是r = mid - 1或者l = mid;，此时为了防止死循环，计算mid时需要加1。
 */
class BinarySearchRightBound {
    /**
     * 查找右边界或小于等于目标值的最大值 (floor)，若目标值小于数组最小值则返回数组最小值
     * @param nums
     * @param target
     * @return index of nums
     */
    public int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int mid = l + ((r - l + 1) >> 1);
            if (nums[mid] <= target) {
                l = mid;
            } else {
                r = mid - 1;
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
        BinarySearchRightBound binarySearch = new BinarySearchRightBound();
        int ans = binarySearch.search(nums, target);
        System.out.println("nums: " + Arrays.stream(nums).boxed().collect(Collectors.toList()));
        System.out.println("target: " + target);
        System.out.println("ans index: " + ans);
        System.out.println("ans num: " + nums[ans]);
    }
}