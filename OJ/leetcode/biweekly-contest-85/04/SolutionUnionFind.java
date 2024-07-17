import java.util.Arrays;
import java.util.stream.Collectors;

class SolutionUnionFind {
    private int[] fa;
    public long[] maximumSegmentSum(int[] nums, int[] removeQueries) {
        int n = nums.length;
        long[] ans = new long[n];
        fa = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            fa[i] = i;
        }
        // 从删除数据，逆向思考为添加数据，从后向前倒序循环
        long[] sum = new long[n + 1];
        for (int i = n-1; i > 0; i--) {
            int curNodeIndex = removeQueries[i];
            int nextNodeIndex = curNodeIndex + 1;
            // 并查集合并当前节点和下一节点（右邻节点），同时合并sum[x]
            merge(curNodeIndex, nextNodeIndex);
            int nextNodeRoot = find(nextNodeIndex);
            sum[nextNodeRoot] += sum[curNodeIndex] + nums[curNodeIndex];

            // 当前最大分段和 a[i - 1]，取当前节点（合并后根节点）的和，或者上一个a[i]的最大分段和两者的最大值。
            ans[i - 1] = Math.max(ans[i], sum[nextNodeRoot]);
        }
        return ans;
    }

    /**
     * 并查集查找操作（路径压缩）
     * @param x
     * @return
     */
    private int find(int x) {
        if (fa[x] == x) {
            return x;
        } else {
            // 路径压缩
            fa[x] = find(fa[x]);
            return fa[x];
        }
    }

    /*
     * 并查集合并操作
     */
    private void merge(int i, int j) {
        fa[find(i)] = find(j);
    }

    public static void main(String[] args) {
        SolutionUnionFind sol = new SolutionUnionFind();
        // int[] nums = {1,2,5,6,1};
        // int[] removeQueries = {0,3,2,4,1};
        int[] nums = {500,822,202,707,298,484,311,680,901,319,343,340};
        // int[] nums = {500,822,202,707,0,484,0,680,901,319,343,340};
        int[] removeQueries = {6,4,0,5,2,3,10,8,7,9,1,11};
        long[] ans = sol.maximumSegmentSum(nums, removeQueries);
        System.out.println("test: " + Arrays.stream(ans).boxed().collect(Collectors.toList()));
    }
}
