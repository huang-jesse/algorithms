import java.util.ArrayList;
import java.util.List;

class Solution {
    public int findMinFibonacciNumbers(int k) {
        List<Integer> fibonacciList = getFibonacciList(k);
        int ans = 0;
        while (k > 0) {
            ans++;
            int num = binarySearchRightBoundary(fibonacciList, k);
            k = k - num;
        }
        return ans;
    }

    private int binarySearchRightBoundary(List<Integer> list, int target) {
        int l = 0;
        int r = list.size()-1;
        while (l < r) {
            int mid = l + ((r-l+1) >> 1);
            if (list.get(mid) <= target) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return list.get(l);
    }

    private List<Integer> getFibonacciList(int k) {
        List<Integer> fibonacciList = new ArrayList<>();
        int first = 1;
        int second = 1;
        fibonacciList.add(first);
        fibonacciList.add(second);
        while (second < k) {
            int temp = first + second;
            first = second;
            second = temp;
            fibonacciList.add(second);
        }
        return fibonacciList;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int k = (int)(1e9);
        System.out.println("test: " + sol.findMinFibonacciNumbers(k));
    }
}