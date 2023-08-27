import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public int minOperations(List<Integer> nums, int target) {
        long sum = nums.stream().mapToLong(o -> (long)o).sum();
        if (sum < target) {
            return -1;
        }
        Map<Integer, Integer> counter = new HashMap<>();
        for (int num : nums) {
            counter.put(num, counter.getOrDefault(num, 0) + 1);
        }
        int ans = 0;
        // 累加所有更小的位的数的和（或者提前包含了当前位，可以直接跳过）
        // 由于 nums 中所有数都是 2 的幂的特性，如果 nums 中比 2^p 小的数之和大于等于 2^p 一定存在一种方案用它们凑出一个 2^p
        // 2^1 可以由和大于 2^1 的多个 2^0 得到，则可用数学归纳法证明，2^k 可以用和大于 2^k 的多个更小次幂构成
        long currentSum = 0;
        // 从小到大，遍历构造target的每个比特位
        for (int i = 0; i < 31; i++) {
            int current = 1 << i;
            // 累加nums在当前位的和
            currentSum += counter.getOrDefault(current, 0) << i;
            if (((target >> i) & 1) == 0) {
                // target 当前位为 0 ，则跳过
                continue;
            }
            if (currentSum >= current) {
                // 如果更小的位的数的和大于当前要表示的比特位，则说明 currentSum 可以构造一个 current = 1 << i
                currentSum -= current;
            } else {
                // 找到下一个更大的数，拆出当前数位
                for (int j = i + 1; j < 31; j++) {
                    if (counter.getOrDefault(1 << j, 0) > 0) {
                        currentSum += 1 << j;
                        counter.put(1 << j, counter.get(1 << j) - 1);
                        // 从 2^j 到 2^i 一定要拆分 j - i 次
                        ans += j - i;
                        currentSum -= current;
                        // 如果 j = i + 1 则此时 currentSum 中包含了小于等于 1 << i 的数的和
                        // 如果 j > i + 1 则此时 currentSum 中包含了大于 1 << i 的数的和
                        // ，但是此时加到 currentSum 中的数将会是 1 << i, 1 << {i + 1}, 1 << {i + 2} 等，可以直接构造接下来更大的 i 位
                        // 所以，不需要将 1 << j 拆分后的其它大于 1 << i 的值加到 counter 中
                        break;
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        List<Integer> nums = Arrays.asList(1,32,1,2);
        int target = 12;
        System.out.println("test: " + sol.minOperations(nums, target));
    }
}