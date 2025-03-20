import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;


class Solution {
    public int[] minReverseOperations(int n, int p, int[] banned, int k) {
        Set<Integer> bannedSet = Arrays.stream(banned).boxed().collect(Collectors.toSet());
        TreeSet<Integer>[] indices = new TreeSet[]{new TreeSet(), new TreeSet()};
        for (int i = 0; i < n; i++) {
            if (i != p && !bannedSet.contains(i)) {
                indices[i % 2].add(i);
            }
        }
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        ans[p] = 0;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(p);
        while (!queue.isEmpty()) {
            int i = queue.poll();
            // indices[mn % 2] 中的从 mn 到 mx 的所有下标都可以从 i 翻转到
            int mn = Math.max(i - k + 1, k - i - 1);
            int mx = Math.min(i + k - 1, n * 2 - k - i - 1);

            TreeSet<Integer> ts = indices[mn % 2];
            for (Iterator<Integer> it = ts.tailSet(mn).iterator(); it.hasNext(); it.remove()) {
                int j = it.next();
                if (j > mx) {
                    break;
                }
                queue.offer(j);
                ans[j] = ans[i] + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 4;
        int p = 0;
        int[] banned = {1, 2};
        int k = 4;
        System.out.println("test: " + Arrays.toString(sol.minReverseOperations(n, p, banned, k)));
    }
}