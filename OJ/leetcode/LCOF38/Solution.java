import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    List<String> ans = new ArrayList<>();
    boolean[] visits;
    char[] chars;
    public String[] permutation(String s) {
        int n = s.length();
        this.visits = new boolean[n];
        this.chars = s.toCharArray();
        Arrays.sort(chars);

        dfs(0, new StringBuffer());
        return ans.toArray(new String[ans.size()]);
    }

    private void dfs(int index, StringBuffer perm) {
        if (index == chars.length) {
            ans.add(perm.toString());
            return;
        }
        for (int i = 0; i < chars.length; i++) {
            char cur = chars[i];
            if (i > 0 && chars[i] == chars[i-1] && !visits[i-1]) continue;
            if (!visits[i]) {
                perm.append(cur);
                visits[i] = true;

                dfs(index + 1, perm);

                perm.deleteCharAt(perm.length()-1);
                visits[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "abb";
        String[] ans = sol.permutation(s);
        System.out.println("test: " + Arrays.asList(ans));
    }
}