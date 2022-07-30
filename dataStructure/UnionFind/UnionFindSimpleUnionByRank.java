import java.util.ArrayList;
import java.util.List;

/**
 * 并查集-按秩合并版本
 */
public class UnionFindSimpleUnionByRank {
    private int[] fa;
    private int[] rank;
    public UnionFindSimpleUnionByRank(int n) {
        // init
        fa = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            fa[i] = i;
            rank[i] = 1;
        }
    }
    public int find(int x) {
        if (fa[x] == x) {
            return x;
        } else {
            return find(fa[x]);
        }
    }
    public void merge(int i, int j) {
        //先找到两个根节点
        int rooti = find(i);
        int rootj = find(j);
        if (rooti == rootj) {
            return;
        }
        if (rank[rooti] <= rank[rootj]) {
            fa[rooti] = rootj;
        } else {
            fa[rootj] = rooti;
        }
        //如果深度相同且根节点不同，则新的根节点的深度+1
        if (rank[rooti] == rank[rootj]) {
            rank[rootj]++;
        }
    }

    @Override
    public String toString() {
        List<String> listStr = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < fa.length; i++) {
            String curStr = String.valueOf(i);
            sb.append(curStr + ": " + curStr);
            int cur = i;
            while (fa[cur] != cur) {
                String faStr = String.valueOf(fa[cur]);
                sb.append(" -> " + faStr);
                cur = fa[cur];
            }
            listStr.add(sb.toString());
            sb = new StringBuilder();
        }
        return listStr.toString();
    }
    public static void main(String[] args) {
        UnionFindSimpleUnionByRank ufs = new UnionFindSimpleUnionByRank(6);
        System.out.println(ufs);
        ufs.merge(0, 1);
        System.out.println(ufs);
        ufs.merge(0, 2);
        System.out.println(ufs);
        ufs.merge(0, 3);
        System.out.println(ufs);
        ufs.merge(0, 4);
        System.out.println(ufs);
    }
}