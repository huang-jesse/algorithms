import java.util.TreeSet;

class Solution {
    public int minimumOperations(int[] nums) {
        TreeSet<Integer> ts = new TreeSet<>();
        for (int num : nums) {
            if (num > 0) {
                ts.add(num);
            }
        }
        int ans = 0;
        int pre = 0;
        while (!ts.isEmpty()) {
            pre = ts.first();
            ans++;
            while (!ts.isEmpty() && pre == ts.first()) {
                ts.remove(ts.first());
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1,5,0,3,5};
        System.out.println("test: " + sol.minimumOperations(nums));
    }
}