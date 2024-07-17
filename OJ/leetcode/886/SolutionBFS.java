import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class SolutionBFS {
    public boolean possibleBipartition(int n, int[][] dislikes) {
        List<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] dislike : dislikes) {
            graph[dislike[0]].add(dislike[1]);
            graph[dislike[1]].add(dislike[0]);
        }
        int[] colors = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (colors[i] != 0) {
                continue;
            }
            colors[i] = 1;
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(i);
            while (!queue.isEmpty()) {
                int currentId = queue.poll();
                List<Integer> dislikeList = graph[currentId];
                for (int dislikeId : dislikeList) {
                    if (colors[dislikeId] != 0 && colors[dislikeId] == colors[currentId]) {
                        return false;
                    }
                    if (colors[dislikeId] == 0) {
                        colors[dislikeId] = 3 ^ colors[currentId];
                        queue.offer(dislikeId);
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        SolutionBFS sol = new SolutionBFS();
        // int n = 4;
        // int[][] dislikes = {{1,2},{1,3},{2,4}};
        int n = 3;
        int[][] dislikes = {{1,2},{1,3},{2,3}};
        System.out.println("test: " + sol.possibleBipartition(n, dislikes));
    }
}