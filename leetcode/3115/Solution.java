import java.util.ArrayList;
import java.util.List;

class Solution {
    private boolean[] isNotPrime;
    public int maximumPrimeDifference(int[] nums) {
        eulerSieve(100);
        int n = nums.length;
        int l = 0;
        while (l < n && isNotPrime[nums[l]]) {
            l++;
        }
        int r = n - 1;
        while (r > l && isNotPrime[nums[r]]) {
            r--;
        }
        return r - l;
    }

    private void eulerSieve(int num) {
        this.isNotPrime = new boolean[num + 1];
        isNotPrime[0] = true;
        isNotPrime[1] = true;
        List<Integer> primes = new ArrayList<>();
        for (int cur = 2; cur <= num; cur++) {
            if (!isNotPrime[cur]) primes.add(cur);
            for (int prime : primes) {
                int nextNum = prime * cur;
                if (nextNum > num) break;
                isNotPrime[nextNum] = true;
                if (cur % prime == 0) break;
            }
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {4,2,9,5,3}; // 3
        // int[] nums = {4,8,2,8}; // 0
        System.out.println("test: " + sol.maximumPrimeDifference(nums));
    }
}