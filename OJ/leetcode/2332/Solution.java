import java.util.Arrays;

class Solution {
    public int latestTimeCatchTheBus(int[] buses, int[] passengers, int capacity) {
        int n = buses.length;
        int m = passengers.length;
        Arrays.sort(buses);
        Arrays.sort(passengers);

        int i = 0;
        int curCap = 0;
        for (int departuel : buses) {
            curCap = capacity;
            if (i >= m) break;
            while (i < m && curCap > 0 && passengers[i] <= departuel) {
                i++;
                curCap--;
            }
        }

        i--;
        int latestTime = curCap > 0 ? buses[n - 1] : passengers[i];
        while (i >= 0 && latestTime == passengers[i]) {
            latestTime--;
            i--;
        }
        return latestTime;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[] buses = {20,30,10};
        // int[] passengers = {19,13,26,4,25,11,21};
        // int capacity = 2; // res: 20
        int[] buses = {3};
        int[] passengers = {2, 4};
        int capacity = 2; // res: 3
        System.out.println("test: " + sol.latestTimeCatchTheBus(buses, passengers, capacity));
    }
}