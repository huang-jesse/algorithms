import java.util.Arrays;

class Solution {
    int[] memo;
    int[] accNums;
    public int deleteAndEarn(int[] nums) {
        accumulateNums(nums);
        this.memo = new int[accNums.length];
        Arrays.fill(this.memo, -1);

        return dfs(0);
    }

    private void accumulateNums(int[] nums) {
        int numOfMax = 0;
        for (int i = 0; i < nums.length; i++) {
            numOfMax = Math.max(nums[i], numOfMax);
        }
        this.accNums = new int[numOfMax+1];
        for (int i = 0; i < nums.length; i++) {
            accNums[nums[i]] += nums[i];
        }
    }

    private int dfs(int index) {
        if (index >= accNums.length)
            return 0;
        if (index == accNums.length-1)
            return accNums[index];
        if (memo[index] != -1)
            return memo[index];
        int ans = 0;
        if (accNums[index] == 0) {
            // current is 0, so get next
            ans = dfs(index+1);
        } else if (accNums[index+1] == 0) {
            // next is 0, so get this accumulate num
            ans = accNums[index] + dfs(index + 2);
        } else {
            // this has two choice here
            // choose current index, then get this accumulate num
            int choosingCurIndex = accNums[index] + dfs(index + 2);
            // choose next index
            int choosingNextIndex = dfs(index + 1);
            ans = Math.max(choosingCurIndex, choosingNextIndex);
        }
        memo[index] = ans;
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = new int[]{2,2,3,3,3,4};
        System.out.println("test: " + sol.deleteAndEarn(nums));
    }
}