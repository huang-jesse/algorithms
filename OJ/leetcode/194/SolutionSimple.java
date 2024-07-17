import java.util.HashMap;
import java.util.Map;

class SolutionSimple {
    public int maxPoints(int[][] points) {
        int n = points.length;
        if (n <= 2)
            return n;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (ans >= n-i || ans > n/2)
                break;
            Map<Integer, Integer> countMap = new HashMap<>();
            for (int j = i+1; j < n; j++) {
                int x = points[i][0] - points[j][0];
                int y = points[i][1] - points[j][1];
                int gcdVal = gcd(x, y);
                int mx = x / gcdVal;
                int my = y / gcdVal;
                // mx [-2*1e4, 2*1e4], my [0, 2*1e4]
                int slopeVal = my + 20001 * mx;
                countMap.put(slopeVal, countMap.getOrDefault(slopeVal, 0) + 1);
            }
            int numOfMax = 0;
            for (Integer val : countMap.values()) {
                numOfMax = Math.max(numOfMax, val + 1);
            }
            ans = Math.max(ans, numOfMax);
        }
        return ans;
    }

    public static int gcd(int a, int b) {
        return b != 0 ? gcd(b, a % b) : a;
    }

    public static void main(String[] args) {
        SolutionSimple sol = new SolutionSimple();
        int[][] points = {{1,1}, {2,2}, {3,3}};
        System.out.println("test: " + sol.maxPoints(points));
    }
}