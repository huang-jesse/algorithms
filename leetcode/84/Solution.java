import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            int cur = heights[i];
            while (!stack.isEmpty() && cur <= heights[stack.peek()]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                left[i] = -1;
            } else {
                left[i] = stack.peek();
            }
            stack.push(i);
        }
        stack.clear();
        for (int i = n - 1; i >= 0; i--) {
            int cur = heights[i];
            while (!stack.isEmpty() && cur <= heights[stack.peek()]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                right[i] = n;
            } else {
                right[i] = stack.peek();
            }
            stack.push(i);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            int hieght = heights[i];
            int width = right[i] - left[i] - 1;
            ans = Math.max(ans, hieght * width);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] heights = {2,1,5,6,2,3};
        // int[] heights = {1};
        // int[] heights = {0,0,0,0};
        // int[] heights = {0};
        // int[] heights = {0,1,0};
        // int[] heights = {0,10,8,0,12,12};
        System.out.println("test: " + sol.largestRectangleArea(heights));
    }
}