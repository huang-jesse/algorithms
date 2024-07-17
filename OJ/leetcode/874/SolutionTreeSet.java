import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

class Solution {
    private static final int[][] directions = {
        {0, 1},// north
        {1, 0},// east
        {0, -1},// south
        {-1, 0}// west
    };
    public int robotSim(int[] commands, int[][] obstacles) {
        Map<Integer, TreeSet<Integer>> xObstacles = new HashMap<>();
        Map<Integer, TreeSet<Integer>> yObstacles = new HashMap<>();
        for (int[] obstacle : obstacles) {
            int x = obstacle[0];
            int y = obstacle[1];
            TreeSet<Integer> xTs = xObstacles.getOrDefault(y, new TreeSet<>());
            xTs.add(x);
            xObstacles.put(y, xTs);

            TreeSet<Integer> yTs = yObstacles.getOrDefault(x, new TreeSet<>());
            yTs.add(y);
            yObstacles.put(x, yTs);
        }
        int[] position = {0, 0};
        int ans = 0;
        // north
        int directionIdx = 0;
        for (int command : commands) {
            int currentX =  position[0];
            int currentY =  position[1];
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
                int nextPositionX = currentX + (directionX * command);
                int nextPositionY = currentY + (directionY * command);
                if (directionX != 0) {
                    // x move
                    TreeSet<Integer> xTs = xObstacles.get(currentY);
                    if (xTs == null) {
                        currentX = nextPositionX;
                    } else if (directionX > 0) {
                        Integer nextObstacle = xTs.higher(currentX);
                        if (nextObstacle == null || nextObstacle > nextPositionX) {
                            currentX = nextPositionX;
                        } else {
                            currentX = nextObstacle - 1;
                        }
                    } else {
                        // directionX < 0
                        Integer nextObstacle = xTs.lower(currentX);
                        if (nextObstacle == null || nextObstacle < nextPositionX) {
                            currentX = nextPositionX;
                        } else {
                            currentX = nextObstacle + 1;
                        }
                    }
                } else {
                    // y move
                    TreeSet<Integer> yTs = yObstacles.get(currentX);
                    if (yTs == null) {
                        currentY = nextPositionY;
                    } else if (directionY > 0) {
                        Integer nextObstacle = yTs.higher(currentY);
                        if (nextObstacle == null || nextObstacle > nextPositionY) {
                            currentY = nextPositionY;
                        } else {
                            currentY = nextObstacle - 1;
                        }
                    } else {
                        // directionY < 0
                        Integer nextObstacle = yTs.lower(currentY);
                        if (nextObstacle == null || nextObstacle < nextPositionY) {
                            currentY = nextPositionY;
                        } else {
                            currentY = nextObstacle + 1;
                        }
                    }
                }
                position[0] = currentX;
                position[1] = currentY;
                ans = Math.max(ans, currentX * currentX + currentY * currentY);
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
        System.out.println("test: " + sol.robotSim(commands, obstacles));
    }
}