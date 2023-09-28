import java.util.Arrays;

class SolutionOptimizationSimple {
    private static final int INF = (int)(1e9 + 7);
    public int[] fullBloomFlowers(int[][] flowers, int[] people) {
        int n = flowers.length;
        int m = people.length;
        int[][] allPoints = new int[2 * n + m][2];
        int index = 0;
        for (int[] flower : flowers) {
            // 花开时间点
            allPoints[index++] = new int[]{flower[0], -INF};
            // 花落时间点
            allPoints[index++] = new int[]{flower[1], INF};
        }
        for (int i = 0; i < m; i++) {
            // 人观赏时间点
            allPoints[index++] = new int[]{people[i], i};
        }
        // 注意：当有多个first值相同时，按花开(-INF)，人(i)，花落(INF)的顺序处理
        Arrays.sort(allPoints, (o1, o2) -> {
            int compareFirst = o1[0] - o2[0];
            if (compareFirst == 0) {
                return o1[1] - o2[1];
            } else {
                return compareFirst;
            }
        });
        // 把所有点排序，维护变量 currentBloomsNum ，遇到花期开始则 currentBloomsNum++，花期结束则 currentBloomsNum--，询问则答案就是当前的 currentBloomsNum 值。
        int[] ans = new int[m];
        int currentBloomsNum = 0;
        for (int[] point : allPoints) {
            if (point[1] == -INF) {
                // 花开
                currentBloomsNum++;
            } else if (point[1] == INF) {
                // 花落
                currentBloomsNum--;
            } else {
                // 人观赏
                ans[point[1]] = currentBloomsNum;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        SolutionOptimizationSimple sol = new SolutionOptimizationSimple();
        int[][] flowers = {{1,6},{3,7},{9,12},{4,13}};
        int[] people = {2,3,7,11};
        // int[][] flowers = {{1,10},{3,3}};
        // int[] people = {3,3,2};
        int[] ans = sol.fullBloomFlowers(flowers, people);
        System.out.println("test: " + Arrays.toString(ans));
    }
}