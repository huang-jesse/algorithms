import java.util.Arrays;
import java.util.stream.Collectors;

class Solution {
    /**
     * 差分数组
     * 参考：https://leetcode-cn.com/problems/corporate-flight-bookings/solution/5118_hang-ban-yu-ding-tong-ji-by-user9081a/
     * @param bookings
     * @param n
     * @return
     */
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] counters = new int[n];
        for (int[] booking : bookings) {
            int first = booking[0];
            int last = booking[1];
            int seats = booking[2];
            counters[first-1] += seats;
            if (last < n) {
                counters[last] -= seats;
            }
        }

        for (int i = 1; i < n; i++) {
            counters[i] += counters[i-1];
        }
        return counters;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] bookings = {{1,2,10},{2,3,20},{2,5,25}};
        int n = 5;
        System.out.println("test: " + Arrays.stream(sol.corpFlightBookings(bookings, n)).boxed().collect(Collectors.toList()));
    }
}