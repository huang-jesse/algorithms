import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    private static final int[][] directions = {
        {0, 1},// north
        {1, 0},// east
        {0, -1},// south
        {-1, 0}// west
    };
    public int robotSim(int[] commands, int[][] obstacles) {
        Map<Integer, Set<Integer>> obstaclesMap = new HashMap<>();
        for (int[] obstacle : obstacles) {
            int x = obstacle[0];
            int y = obstacle[1];
            Set<Integer> ySet = obstaclesMap.getOrDefault(x, new HashSet<>());
            ySet.add(y);
            obstaclesMap.put(x, ySet);
        }
        int currentX = 0;
        int currentY = 0;
        int ans = 0;
        // north
        int directionIdx = 0;
        for (int command : commands) {
            if (command == -1) {
                // turn right 90 degrees
                directionIdx = (directionIdx + 1) % 4;
            } else if (command == -2) {
                // turn left 90 degrees
                directionIdx = (directionIdx - 1 + 4) % 4;
            } else {
                // move
                int directionX = directions[directionIdx][0];
                int directionY = directions[directionIdx][1];
                for (int move = 0; move < command; move++) {
                    int nextPositionX = currentX + directionX;
                    int nextPositionY = currentY + directionY;
                    if (obstaclesMap.containsKey(nextPositionX) && obstaclesMap.get(nextPositionX).contains(nextPositionY)) {
                        // the robot runs into an obstacle
                        break;
                    } else {
                        currentX = nextPositionX;
                        currentY = nextPositionY;
                        ans = Math.max(ans, currentX * currentX + currentY * currentY);
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[] commands = {4,-1,4,-2,4};
        // int[][] obstacles = {{2, 4}};
        int[] commands = {-2,-1,-2,3,7};
        int[][] obstacles = {{1,-3},{2,-3},{4,0},{-2,5},{-5,2},{0,0},{4,-4},{-2,-5},{-1,-2},{0,2}};
        // int[] commands = {7,-2,-2,7,5};
        // int[][] obstacles = {{-3,2},{-2,1},{0,1},{-2,4},{-1,0},{-2,-3},{0,-3},{4,4},{-3,3},{2,2}};
        System.out.println("test: " + sol.robotSim(commands, obstacles));
    }
}