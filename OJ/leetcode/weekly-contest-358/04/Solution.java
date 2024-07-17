import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

class Solution {
    private static final int MOD = (int)(1e9 + 7);
    public int maximumScore(List<Integer> nums, int k) {
        int n = nums.size();
        // 预处理每个值的不同质因子数量，即质数分数
        int[] primeSizes = new int[n];
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
        for (int i = 0; i < n; i++) {
            primeSizes[i] = primeFactorizationSize(nums.get(i));
            maxHeap.add(new int[]{i, nums.get(i)});
        }
        // 预处理子数组数量，当选取 nums[i] 时子数组的数量为 subArrNum[i]
        int[] subArrNum = getSubArrNum(primeSizes);
        // 贪心处理，获取最大的 x ，此时 x 贡献的分数为 x ^ {subArrNum[index]}
        int ans = 1;
        while (k > 0 && !maxHeap.isEmpty()) {
            int[] numInfo = maxHeap.poll();
            int index = numInfo[0];
            int x = numInfo[1];
            int times = (int)Math.min(k, subArrNum[index]);
            k -= times;

            int res = qpow(x, times);
            ans = (int)(((long)ans * res) % MOD);
        }
        return ans;
    }

    /**
     * 单调栈预处理，当选取 {@code nums[i]} 时子数组的数量为 {@code subArrNum[i]}
     * @param primeSizes
     * @return
     */
    private int[] getSubArrNum(int[] primeSizes) {
        int n = primeSizes.length;
        int[] leftBoundary = new int[n];
        int[] rightBoundary = new int[n];
        Deque<Integer> monotonicStack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            int curNum = primeSizes[i];
            while (!monotonicStack.isEmpty() && primeSizes[monotonicStack.peek()] < curNum) {
                monotonicStack.pop();
            }
            if (!monotonicStack.isEmpty()) {
                leftBoundary[i] = monotonicStack.peek() + 1;
            } else {
                leftBoundary[i] = 0;
            }
            monotonicStack.push(i);
        }
        monotonicStack.clear();
        for (int i = n - 1; i >= 0; i--) {
            int curNum = primeSizes[i];
            while (!monotonicStack.isEmpty() && primeSizes[monotonicStack.peek()] <= curNum) {
                monotonicStack.pop();
            }
            if (!monotonicStack.isEmpty()) {
                rightBoundary[i] = monotonicStack.peek() - 1;
            } else {
                rightBoundary[i] = n - 1;
            }
            monotonicStack.push(i);
        }

        int[] subArrNum = new int[n];
        for (int i = 0; i < n; i++) {
            subArrNum[i] = (int)((long)(i - leftBoundary[i] + 1) * (rightBoundary[i] - i + 1) % MOD);
        }
        return subArrNum;
    }

    /**
     * 分解质因数
     * @param num
     * @return
     */
    private int primeFactorizationSize(int num) {
        Set<Integer> factors = new HashSet<>();
        for (int i = 2; i * i <= num; i++) {
            while (num % i == 0) {
                num = num / i;
                factors.add(i);
            }
        }
        //若最后剩余数不为1，则为最后一个质因数
        if (num != 1) {
            factors.add(num);
        }
        return factors.size();
    }

    /**
     * 快速幂
     * @param a
     * @param n
     * @return
     */
    private int qpow(int a, int n) {
        int ans = 1;
        while (n > 0) {
            if ((n & 1) == 1) {
                // 当前二进制最后一位为1
                ans = (int)((long)ans * a % MOD);
            }
            // a累乘
            a = (int)((long)a * a % MOD);
            // n右移一位
            n = n >> 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        List<Integer> nums = Arrays.asList(3289,2832,14858,22011);
        int k = 6;
        // List<Integer> nums = Arrays.asList(19,12,14,6,10,18);
        // int k = 3;
        // List<Integer> nums = Arrays.asList(8,3,9,3,8);
        // int k = 2;
        System.out.println("test: " + sol.maximumScore(nums, k));
    }
}