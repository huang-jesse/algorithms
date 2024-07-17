import java.util.ArrayList;
import java.util.List;

class SolutionMonotonousStack {
    class ArrayListStack<E> {
        private List<E> list;
        public ArrayListStack() {
            this.list = new ArrayList<>();
        }
        public void push(E element) {
            list.add(element);
        }
        public E pop() {
            return list.remove(list.size() - 1);
        }
        public E peek() {
            return list.get(list.size() - 1);
        }
        public boolean isEmpty() {
            return list.isEmpty();
        }
        public E get(int index) {
            return list.get(index);
        }
        public int size() {
            return list.size();
        }
    }

    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        long[] prefixSums = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSums[i + 1] = prefixSums[i] + nums[i];
        }
        int ans = n + 1;
        // monotone increasing
        ArrayListStack<Integer> monotonousStack = new ArrayListStack<>();
        for (int i = 0; i <= n; i++) {
            long curSum = prefixSums[i];
            while (!monotonousStack.isEmpty() && curSum <= prefixSums[monotonousStack.peek()]) {
                monotonousStack.pop();
            }
            monotonousStack.push(i);
            int indexOfStack = binarySearch(monotonousStack, prefixSums, k);
            if (indexOfStack == -1) {
                continue;
            }
            int leftIndex = monotonousStack.get(indexOfStack);
            ans = Math.min(ans, i - leftIndex);
        }
        return ans > n ? -1 : ans;
    }

    private int binarySearch(ArrayListStack<Integer> monotonousStack, long[] prefixSums, int target) {
        int size = monotonousStack.size();
        if (monotonousStack.isEmpty() || getSubSum(monotonousStack, prefixSums, 0, size - 1) < target) {
            return -1;
        }
        int left = 0;
        int right = size - 1;
        while (left < right) {
            int mid = left + ((right - left + 1) >> 1);
            long subSum = getSubSum(monotonousStack, prefixSums, mid, size - 1);
            if (subSum >= target) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private long getSubSum(ArrayListStack<Integer> monotonousStack, long[] prefixSums, int stackIndexStart, int stackIndexEnd) {
        return prefixSums[monotonousStack.get(stackIndexEnd)] - prefixSums[monotonousStack.get(stackIndexStart)];
    }

    public static void main(String[] args) {
        SolutionMonotonousStack sol = new SolutionMonotonousStack();
        int[] nums = {2,-1,2,0};
        int k = 3;
        // int[] nums = {2,-1,2};
        // int k = 3;
        System.out.println("test: " + sol.shortestSubarray(nums, k));
    }
}