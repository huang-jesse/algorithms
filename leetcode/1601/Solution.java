import java.util.Arrays;

class Solution {
    private static final int UNACHIEVABLE = -16;
    public int maximumRequests(int n, int[][] requests) {
        int[] buildingCounter = new int[n];
        return dfs(requests, 0, buildingCounter);
    }

    private int dfs(int[][] requests, int index, int[] buildingCounter) {
        if (index >= requests.length) {
            if (isRequestAchievable(buildingCounter)) {
                return 0;
            } else {
                return UNACHIEVABLE;
            }
        }

        buildingCounter[requests[index][0]]--;
        buildingCounter[requests[index][1]]++;
        int selected = 1 + dfs(requests, index+1, buildingCounter);
        buildingCounter[requests[index][0]]++;
        buildingCounter[requests[index][1]]--;

        int unselected = dfs(requests, index+1, buildingCounter);

        int res = Math.max(selected, unselected);
        return res;
    }

    private boolean isRequestAchievable(int[] buildingCounter) {
        return !Arrays.stream(buildingCounter).anyMatch(counter -> counter != 0);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 3;
        int[][] requests = {{0,0},{1,2},{2,1}};
        System.out.println("test: " + sol.maximumRequests(n, requests));
    }
}