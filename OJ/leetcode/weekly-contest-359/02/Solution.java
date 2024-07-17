import java.util.HashSet;
import java.util.Set;

class Solution {
    public int minimumSum(int n, int k) {
        Set<Integer> nums = new HashSet<>();
        nums.add(1);
        int ans = 1;
        for (int i = 1, num = 2; i < n; i++, num++) {
            while (nums.contains(k - num)) {
                num++;
            }
            nums.add(num);
            ans += num;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 5;
        int k = 4;
        System.out.println("test: " + sol.minimumSum(n, k));
    }
}