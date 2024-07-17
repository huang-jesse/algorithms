import java.util.Arrays;

class Solution {
    public long maxPower(int[] stations, int r, int k) {
        long[] powerOfCitys = getPowerOfCitys(stations, r);
        long minPower = Arrays.stream(powerOfCitys).min().getAsLong();
        // Binary search
        long left = minPower;
        long right = minPower + k;
        while (left < right) {
            long mid = left + ((right - left + 1) >> 1);
            if (check(powerOfCitys, r, k, mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private boolean check(long[] powerOfCitys, int r, int k, long target) {
        int n = powerOfCitys.length;
        int rangeOfStation = 2 * r + 1;
        long[] differenceArr = new long[n];
        long preSum = 0L;
        for (int i = 0; i < n; i++) {
            long currentPower = powerOfCitys[i];
            preSum += differenceArr[i];
            currentPower += preSum;
            if (currentPower >= target) {
                continue;
            }
            long diff = target - currentPower;
            if (k < diff) {
                return false;
            }
            k -= diff;
            differenceArr[i] += diff;
            if (i + rangeOfStation < n) {
                differenceArr[i + rangeOfStation] -= diff;
            }
            preSum += diff;
        }
        return true;
    }

    private long[] getPowerOfCitys(int[] stations, int r) {
        int n = stations.length;
        long[] differenceArr = new long[n];
        long[] powerOfCitys = new long[n];
        for (int i = 0; i < n; i++) {
            int leftBoundary = Math.max(0, i - r);
            differenceArr[leftBoundary] += stations[i];
            if (i + r + 1 < n) {
                differenceArr[i + r + 1] -= stations[i];
            }
        }
        powerOfCitys[0] = differenceArr[0];
        for (int i = 1; i < n; i++) {
            powerOfCitys[i] = powerOfCitys[i - 1] + differenceArr[i];
        }
        return powerOfCitys;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] stations = {1,2,4,5,0};
        int r = 1;
        int k = 2;
        System.out.println("test: " + sol.maxPower(stations, r, k));
    }
}