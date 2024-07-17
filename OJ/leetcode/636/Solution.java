import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] ans = new int[n];
        Deque<Integer> d = new ArrayDeque<>();
        int cur = -1;
        for (String log : logs) {
            String[] ss = log.split(":");
            int idx = Integer.parseInt(ss[0]), ts = Integer.parseInt(ss[2]);
            if (ss[1].equals("start")) {
                if (!d.isEmpty()) ans[d.peekLast()] += ts - cur;
                d.addLast(idx);
                cur = ts;
            } else {
                int func = d.pollLast();
                ans[func] += ts - cur + 1;
                cur = ts + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 2;
        List<String> logs = Arrays.asList("0:start:0","0:start:2","0:end:5","1:start:6","1:end:6","0:end:7");
        List<Integer> ans = Arrays.stream(sol.exclusiveTime(n, logs)).boxed().collect(Collectors.toList());
        System.out.println("test: " + ans);
    }
}