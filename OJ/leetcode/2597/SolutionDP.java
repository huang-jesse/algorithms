import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

class SolutionDP {
    public int beautifulSubsets(int[] nums, int k) {
        Map<Integer, TreeMap<Integer, Integer>> groups = new HashMap<>();
        for (int num : nums) {
            // 模 k 分组(不同余的不同分组之间，二者相差一定不等于 k ，详见同余定理)
            groups.computeIfAbsent(num % k, x -> new TreeMap<Integer, Integer>()).merge(num, 1, Integer::sum);
        }
        int ans = 1;
        for (TreeMap<Integer, Integer> group : groups.values()) {
            int m = group.size();
            // dp
            int[] f = new int[m + 1];
            // 默认所有 group 包含空集
            f[0] = 1;
            int i = 1;
            int pre = 0;
            for (Map.Entry<Integer, Integer> entry : group.entrySet()) {
                int num = entry.getKey();
                int c = entry.getValue(); // count
                if (i > 1 && num - pre == k) {
                    // 不可选当前项（包含不选当前项的 f[i - 1] ）
                    f[i] = f[i - 1] + f[i - 2] * ((1 << c) - 1);
                } else {
                    // 选当前项（包含不选当前项的 f[i - 1] ）
                    f[i] = f[i - 1] * (1 << c);

                }
                pre = num;
                i++;
            }
            ans *= f[m];
        }

        // 减去所有 group 都为空集的唯一一种情况
        return ans - 1;
    }

    public static void main(String[] args) {
        SolutionDP sol = new SolutionDP();
        int[] nums = {2,4,6};
        int k = 2; //ans: 4
        System.out.println("test: " + sol.beautifulSubsets(nums, k));
    }
}