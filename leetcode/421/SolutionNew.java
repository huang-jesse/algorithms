import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class SolutionNew {
    public int findMaximumXOR(int[] nums) {
        int max = Arrays.stream(nums).max().getAsInt();
        int k = 32 - Integer.numberOfLeadingZeros(max);
        // 贪心
        // 循环每一个比特位
        int ans = 0;
        for (int i = k - 1; i >= 0; i--) {
            // 集合保存 [0, i] 区间的比特位信息
            Set<Integer> set = new HashSet<>();
            for (int num : nums) {
                int x = num >> i;
                set.add(x);
            }
            // 贪心，将 ans 向左移位并加1 ，尝试检查当前比特位是否合法
            ans = (ans << 1) + 1;
            boolean valid = false;
            for (int num : nums) {
                int current = num >> i;
                if (set.contains(current ^ ans)) {
                    // 表示当前 ans 合法，存在一个 x 使 x ^ current == ans
                    valid = true;
                    break;
                }
            }
            if (!valid) {
                // 不合法需要移除当前比特位
                ans = ans - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        SolutionNew sol = new SolutionNew();
        int[] nums = new int[]{3,10,5,25,2,8};
        System.out.println("test: " + sol.findMaximumXOR(nums));
    }
}