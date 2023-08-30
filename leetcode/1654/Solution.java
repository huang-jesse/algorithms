import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        Set<Integer> forbiddenSet = Arrays.stream(forbidden).boxed().collect(Collectors.toSet());
        int f = Arrays.stream(forbidden).max().getAsInt();
        int lower = 0;
        int upper = Math.max(f + a, x) + b;
        // {nextPosition*nextDirection} 记录已经达到过的位置和方向状态
        Set<Integer> visited = new HashSet<>();
        // {nextPosition, nextDirection, step}
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 1, 0});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int position = cur[0];
            int direction = cur[1];
            int step = cur[2];
            if (position == x) {
                return step;
            }
            int nextPosition = position + a;
            int nextDirection = 1;
            if (nextPosition >= lower && nextPosition <= upper && !visited.contains(nextPosition*nextDirection) && !forbiddenSet.contains(nextPosition)) {
                queue.offer(new int[]{nextPosition, nextDirection, step + 1});
                visited.add(nextPosition*nextDirection);
            }
            if (direction == 1) {
                nextPosition = position - b;
                nextDirection = -1;
                if (nextPosition >= lower && nextPosition <= upper && !visited.contains(nextPosition*nextDirection) && !forbiddenSet.contains(nextPosition)) {
                    queue.offer(new int[]{nextPosition, nextDirection, step + 1});
                    visited.add(nextPosition*nextDirection);
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] forbidden = {1,6,2,14,5,17,4};
        int a = 16;
        int b = 9;
        int x = 7;
        System.out.println("test: " + sol.minimumJumps(forbidden, a, b, x));
    }
}