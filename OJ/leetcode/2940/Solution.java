import java.util.Arrays;

class Solution {
    public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {
        int n = heights.length;
        int m = queries.length;
        Integer[] indexes = new Integer[m];
        for (int i = 0; i < m; i++) {
            indexes[i] = i;
            if (queries[i][0] > queries[i][1]) {
                int temp = queries[i][0];
                queries[i][0] = queries[i][1];
                queries[i][1] = temp;
            }
        }
        // desc
        Arrays.sort(indexes, (o1, o2) -> queries[o2][1] - queries[o1][1]);
        int[] ans = new int[m];
        // monotone decreasing
        ArrayStack monotonicStack = new ArrayStack(n);
        int preIndex = n;
        for (int index : indexes) {
            int[] query = queries[index];
            int a = query[0];
            int b = query[1];
            for (int i = preIndex - 1; i > b; i--) {
                while (!monotonicStack.isEmpty() && heights[i] >= heights[monotonicStack.peek()]) {
                    monotonicStack.pop();
                }
                monotonicStack.push(i);
            }
            while (!monotonicStack.isEmpty() && heights[b] >= heights[monotonicStack.peek()]) {
                monotonicStack.pop();
            }

            if (a == b || heights[a] < heights[b]) {
                ans[index] = b;
            } else {
                int maxHeight = Math.max(heights[a], heights[b]);
                ans[index] = binarySearch(monotonicStack, heights, maxHeight);
            }
            monotonicStack.push(b);
            preIndex = b;
        }
        return ans;
    }

    private int binarySearch(ArrayStack monotonicStack, int[] heights, int target) {
        if (monotonicStack.isEmpty() || target >= heights[monotonicStack.get(0)]) {
            return -1;
        }
        if (target < heights[monotonicStack.peek()]) {
            return monotonicStack.peek();
        }
        int size = monotonicStack.size();
        int l = 0;
        int r = size - 1;
        while (l < r) {
            int mid = l + ((r - l + 1) >> 1);
            if (heights[monotonicStack.get(mid)] > target) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return monotonicStack.get(l);
    }

    static class ArrayStack {
        private final int[] arr;
        private int size;
        public ArrayStack(int cap) {
            this.arr = new int[cap + 1];
            this.size = 0;
        }

        public void push(int num) {
            this.arr[size++] = num;
        }

        public void pop() {
            this.size--;
        }

        public int peek() {
            return this.arr[size - 1];
        }

        public int get(int index) {
            return this.arr[index];
        }

        public boolean isEmpty() {
            return this.size == 0;
        }

        public int size() {
            return this.size;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] heights = {6,4,8,5,2,7};
        int[][] queries = {{0,1},{0,3},{2,4},{3,4},{2,2}};
        // res: 2,5,-1,5,2
        // int[] heights = {5,3,8,2,6,1,4,6};
        // int[][] queries = {{0,7},{3,5},{5,2},{3,0},{1,6}};
        // // res: 7,6,-1,4,6
        // int[] heights = {3,1,2,4};
        // int[][] queries = {{0,0},{0,1},{0,2},{0,3}};
        // // res: 0,3,3,3
        System.out.println("test: " + Arrays.toString(sol.leftmostBuildingQueries(heights, queries)));
    }
}