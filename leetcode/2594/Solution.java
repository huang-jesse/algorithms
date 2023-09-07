class Solution {
    public long repairCars(int[] ranks, int cars) {
        int[] counter = new int[101];
        for (int rank :ranks) {
            counter[rank]++;
        }
        long left = 1;
        long right = (long)ranks[0] * cars * cars;
        while (left < right) {
            long mid = left + ((right - left) >> 1);
            if (check(counter, cars, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean check(int[] counter, int cars, long repairMinutes) {
        long sum = 0L;
        for (int rank = 1; rank <= 100; rank++) {
            if (sum >= cars) {
                break;
            }
            long y = repairMinutes / rank;
            int repairCar = (int)Math.floor(Math.sqrt(y));
            sum += (long)repairCar * counter[rank];
        }
        return sum >= cars;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] ranks = {4,2,3,1};
        int cars = 10;
        // int[] ranks = {5,1,8};
        // int cars = 6;
        System.out.println("test: " + sol.repairCars(ranks, cars));
    }
}