import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    private static final int MAX_VAL = 100010;
    // List's elements is the num that is contain this factor.
    private List<Integer>[] primeFactorsArr = (List<Integer>[])new ArrayList[MAX_VAL];
    // List's elements is all primeFactors of the num.
    private List<Integer>[] numsArr = (List<Integer>[])new ArrayList[MAX_VAL];
    public int largestComponentSize(int[] nums) {
        for (int i = 0; i < MAX_VAL; i++) {
            primeFactorsArr[i] = new ArrayList<>();
            numsArr[i] = new ArrayList<>();
        }
        for (int num : nums) {
            int curNum = num;
            for (int i = 2; i * i <= curNum; i++) {
                if (curNum % i == 0) {
                    primeFactorsArr[i].add(num);
                    numsArr[num].add(i);
                }
                while (curNum % i == 0) {
                    curNum = curNum / i;
                }
            }
            if (curNum != 1) {
                primeFactorsArr[curNum].add(num);
                numsArr[num].add(curNum);
            }
        }

        // BFS
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visitedNums = new boolean[MAX_VAL];
        boolean[] visitedFactors = new boolean[MAX_VAL];
        int ans = 1;
        for (int num : nums) {
            if (visitedNums[num]) {
                continue;
            }
            int count = 1;
            queue.offer(num);
            visitedNums[num] = true;
            while (!queue.isEmpty()) {
                int curNum = queue.poll();
                List<Integer> curFactorList = numsArr[curNum];
                for (int curFactor : curFactorList) {
                    if (visitedFactors[curFactor]) {
                        continue;
                    }
                    List<Integer> nextNumList = primeFactorsArr[curFactor];
                    for (int nextNum : nextNumList) {
                        if (visitedNums[nextNum]) {
                            continue;
                        }
                        queue.offer(nextNum);
                        visitedNums[nextNum] = true;
                        count++;
                    }
                    visitedFactors[curFactor] = true;
                }
            }
            ans = Math.max(ans, count);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {4,6,15,35};
        // int[] nums = {20,50,9,63};
        System.out.println("test: " + sol.largestComponentSize(nums));
    }
}