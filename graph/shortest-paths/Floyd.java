import java.util.Arrays;

/**
 * Floyd 算法
 * <p>求图中的任意两个顶点之间的最短路
 */
public class Floyd {
    private static final int INF = 0x3fffffff;

    /**
     * 特殊处理图中存在负权边的场景
     * @param d
     */
    public static void floydNegativeWeight(int[][] d) {
        int n = d.length;
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (d[i][k] < INF && d[j][k] < INF) {
                        d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
                    }
                }
            }
        }
    }

    public static void floyd(int[][] d) {
        int n = d.length;
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
                }
            }
        }
    }

    public static void floydRollArray(int[][] d) {
        int n = d.length;
        for (int k = 0; k < n; k++) {
            int[][] newd = new int[n][n];
            for (int i = 0; i < n; i++) System.arraycopy(d[i], 0, newd[i], 0, n);;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    d[i][j] = Math.min(d[i][j], newd[i][k] + newd[k][j]);
                }
            }
        }
    }

    public static void main(String[] args) {
        int n = 3;
        // {u,v,weight}
        int[][] edges = {{0,1,2},{1,2,10},{0,2,10}};
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    graph[i][j] = 0;
                } else {
                    graph[i][j] = INF;
                }
            }
        };
        for (int[] edge : edges) {
            graph[edge[0]][edge[1]] = Math.min(graph[edge[0]][edge[1]], edge[2]);
            graph[edge[1]][edge[0]] = Math.min(graph[edge[1]][edge[0]], edge[2]);
        }
        floyd(graph);
        for (int i = 0; i < n; i++) System.out.println(Arrays.toString(graph[i]));
    }
}