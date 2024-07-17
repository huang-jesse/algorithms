import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> countOfPeaks(int[] nums, int[][] queries) {
        int n = nums.length;
        int[] peakArr = new int[n];
        for (int i = 1; i < n - 1; i++) {
            if (nums[i - 1] < nums[i] && nums[i] > nums[i + 1]) {
                peakArr[i] = 1;
            }
            // else peakArr[i] = 0;
        }
        BinaryIndexedTree bit = new BinaryIndexedTree(peakArr);
        List<Integer> ans = new ArrayList<>();
        for (int[] query : queries) {
            if (query[0] == 2) {
                updateVal(nums, peakArr, bit, query[1], query[2]);
            } else {
                int left = query[1];
                int right = query[2];
                int res = 0;
                if (left != right) {
                    res = bit.getRangeSum(left + 1, right + 1);
                    res = res - (bit.getRangeSum(left + 1, left + 1) + bit.getRangeSum(right + 1, right + 1));
                }
                ans.add(res);
            }
        }
        return ans;
    }

    private void updateVal(int[] nums, int[] peakArr, BinaryIndexedTree bit, int index, int val) {
        int n = nums.length;
        // update val
        nums[index] = val;
        for (int i = index - 1; i <= index + 1; i++) {
            if (i <= 0 || i >= n - 1) continue;
            if (nums[i - 1] < nums[i] && nums[i] > nums[i + 1]) {
                update(peakArr, bit, i, 1);
            } else {
                update(peakArr, bit, i, 0);
            }
        }
    }

    private void update(int[] peakArr, BinaryIndexedTree bit, int index, int val) {
        int delta = val - peakArr[index];
        peakArr[index] = val;
        bit.add(index + 1, delta);
    }

    static class BinaryIndexedTree {
        private int n;
        private int[] c;

        public BinaryIndexedTree(int n) {
            this.n = n;
            this.c = new int[n + 1];// 1-indexed
        }

        public BinaryIndexedTree(int[] a) {
            this(a.length);
            this.init(a);
        }

        private int lowbit(int x) {
            return x & -x;
        }

        public void init(int[] a) {
            for (int i = 1; i <= n; i++) {
                c[i] += a[i - 1];
                int j = i + lowbit(i);
                if (j <= n) {
                    c[j] += c[i];
                }
            }
        }

        public void add(int x, int delta) {
            while (x <= n) {
                c[x] += delta;
                x = x + lowbit(x);
            }
        }

        public int getSum(int x) {
            int res = 0;
            while (x > 0) {
                res += c[x];
                x = x - lowbit(x);
            }
            return res;
        }

        public int getRangeSum(int l, int r) {
            return getSum(r) - getSum(l - 1);
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[] nums = {3,1,4,2,5};
        // int[][] queries = {{2,3,4},{1,0,4}};
        // int[] nums = {3,6,9};
        // int[][] queries = {{1,1,1},{1,2,2},{2,2,3}};
        int[] nums = {5,4,8,6};
        int[][] queries = {{1,2,2},{1,1,2},{2,1,6}}; // res[0,0]
        System.out.println("test: " + sol.countOfPeaks(nums, queries));
    }
}