import java.util.Arrays;
import java.util.stream.Collectors;

class Solution {
    public int[] bestCoordinate(int[][] towers, int radius) {
        int maxNetworkQuality = 0;
        int[] ans = {0, 0};
        for (int x = 0; x <= 50; x++) {
            for (int y = 0; y <= 50; y++) {
                // calculate network quality
                int networkQuality = 0;
                for (int[] tower : towers) {
                    int x1 = tower[0];
                    int y1 = tower[1];
                    int q = tower[2];
                    int squareDistance = (x1 - x) * (x1 - x) + (y1 - y) * (y1 - y);
                    if (squareDistance > radius * radius) {
                        // not reachable
                        continue;
                    }
                    int signalQuality = (int)Math.floor(q / (1 + Math.sqrt(squareDistance)));
                    networkQuality += signalQuality;
                }
                if (networkQuality > maxNetworkQuality) {
                    maxNetworkQuality = networkQuality;
                    ans[0] = x;
                    ans[1] = y;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] towers = {{1,2,5},{2,1,7},{3,1,9}};
        int radius = 2;
        System.out.println("test: " + Arrays.stream(sol.bestCoordinate(towers, radius)).boxed().collect(Collectors.toList()));
    }
}