import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        int n = s.length();
        int m = indices.length;
        Map<Integer, List<Integer>> opsMap = new HashMap<>();
        for (int i = 0; i < m; i++) {
            List<Integer> ops = opsMap.getOrDefault(indices[i], new ArrayList<>());
            ops.add(i);
            opsMap.put(indices[i], ops);
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < n;) {
            boolean isSuccess = false;
            if (opsMap.containsKey(i)) {
                // 该位置可能需要替换操作
                List<Integer> ops = opsMap.get(i);
                for (int opIndex : ops) {
                    String source = sources[opIndex];
                    if (s.startsWith(source, i)) {
                        // 匹配成功，进行替换
                        ans.append(targets[opIndex]);
                        i += source.length();
                        isSuccess = true;
                        break;
                    }
                }
            }
            if (!isSuccess) {
                ans.append(s.charAt(i));
                i++;
            }
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "abcd";
        int[] indices = {0, 2};
        String[] sources = {"ab","ec"};
        String[] targets = {"eee", "ffff"};
        // String s = "abcd";
        // int[] indices = {0, 2};
        // String[] sources = {"a", "cd"};
        // String[] targets = {"eee", "ffff"};
        System.out.println("test: " + sol.findReplaceString(s, indices, sources, targets));
    }
}