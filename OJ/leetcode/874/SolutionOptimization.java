import java.util.HashSet;
import java.util.Set;

class SolutionOptimization {
    private static final int[][] directions = {
        {0, 1},// north
        {1, 0},// east
        {0, -1},// south
        {-1, 0}// west
    };
    public int robotSim(int[] commands, int[][] obstacles) {
        Set<Integer> obstaclesSet = new HashSet<>();
        for (int[] obstacle : obstacles) {
            int x = obstacle[0];
            int y = obstacle[1];
            obstaclesSet.add(encoder(x, y));
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
                    if (obstaclesSet.contains(encoder(nextPositionX, nextPositionY))) {
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

    private int encoder(int x, int y) {
        return x * 60001 + y;
    }

    public static void main(String[] args) {
        SolutionOptimization sol = new SolutionOptimization();
        // int[] commands = {4,-1,4,-2,4};
        // int[][] obstacles = {{2, 4}};
        int[] commands = {-2,-1,-2,3,7};
        int[][] obstacles = {{1,-3},{2,-3},{4,0},{-2,5},{-5,2},{0,0},{4,-4},{-2,-5},{-1,-2},{0,2}};
        // int[] commands = {7,-2,-2,7,5};
        // int[][] obstacles = {{-3,2},{-2,1},{0,1},{-2,4},{-1,0},{-2,-3},{0,-3},{4,4},{-3,3},{2,2}};
        System.out.println("test: " + sol.robotSim(commands, obstacles));
    }
}