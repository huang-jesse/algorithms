import java.util.ArrayList;
import java.util.List;

/**
 * 并查集-朴素版本
 */
public class UnionFindSimple {
    private int[] fa;
    public UnionFindSimple(int n) {
        // init
        fa = new int[n];
        for (int i = 0; i < n; i++) {
            fa[i] = i;
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
        fa[find(i)] = fa[find(j)];
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
        UnionFindSimple ufs = new UnionFindSimple(6);
        System.out.println(ufs);
        ufs.merge(2, 3);
        System.out.println(ufs);
        ufs.merge(1, 3);
        System.out.println(ufs);
        ufs.merge(0, 1);
        System.out.println(ufs);
        ufs.merge(4, 5);
        System.out.println(ufs);
        ufs.merge(3, 4);
        System.out.println(ufs);
    }
}