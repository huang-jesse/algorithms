import java.util.Deque;
import java.util.LinkedList;

class Solution {
    public String simplifyPath(String path) {
        Deque<String> pathStack = new LinkedList<>();
        int n = path.length();
        StringBuilder dirName = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char cur = path.charAt(i);
            if (cur != '/') {
                dirName.append(cur);
            }
            if (cur == '/' || i == n-1) {
                String dirStr = dirName.toString();
                dirName = new StringBuilder();
                if (dirStr.length() == 0 || dirStr.equals(".")) {
                    continue;
                } else if (dirStr.equals("..")) {
                    if (!pathStack.isEmpty()) {
                        pathStack.pop();
                    }
                } else {
                    pathStack.push(dirStr);
                }
            }
        }
        StringBuilder ans = new StringBuilder();
        if (pathStack.isEmpty()) {
            ans.append("/");
        } else {
            while (!pathStack.isEmpty()) {
                ans.append("/");
                ans.append(pathStack.pollLast());
            }
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // String path = "/a/./b";
        String path = "/a/./b/../../c/";
        System.out.println("test: " + sol.simplifyPath(path));
    }
}