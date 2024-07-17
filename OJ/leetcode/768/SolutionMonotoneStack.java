import java.util.ArrayDeque;
import java.util.Deque;

class SolutionMonotoneStack {
    public int maxChunksToSorted(int[] arr) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int num : arr) {
            if (stack.isEmpty() || num >= stack.peek()) {
                stack.push(num);
            } else {
                int max = stack.pop();
                while (!stack.isEmpty() && num < stack.peek()) {
                    stack.pop();
                }
                stack.push(max);
            }
        }
        return stack.size();
    }

    public static void main(String[] args) {
        SolutionMonotoneStack sol = new SolutionMonotoneStack();
        int[] arr = {2,4,1,3,4};
        // int[] arr = {2,1,3,4,4};
        System.out.println("test: " + sol.maxChunksToSorted(arr));
    }
}