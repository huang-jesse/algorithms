import java.util.Arrays;

class Solution {
    public int findRadius(int[] houses, int[] heaters) {
        int m = houses.length;
        Arrays.sort(heaters);
        int ans = 0;
        for (int i = 0; i < m; i++) {
            ans = Math.max(ans, binarySearchForMinRadius(houses[i], heaters));
        }
        return ans;
    }

    private int binarySearchForMinRadius(int house, int[] heaters) {
        int n = heaters.length;
        int low = 0;
        int high = n-1;
        while (low < high) {
            int mid = low + ((high - low) >> 1);
            if (heaters[mid] == house) {
                high = mid;
                break;
            } else if (heaters[mid] > house) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        int radius = Math.abs(heaters[high] - house);
        if (high != 0) {
            radius = Math.min(radius, Math.abs(heaters[high-1] - house));
        }
        return radius;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] houses = {1,2,3,4};
        int[] heaters = {1,4};
        System.out.println("test: " + sol.findRadius(houses, heaters));
    }
}