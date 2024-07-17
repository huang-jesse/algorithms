import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> removeComments(String[] source) {
        List<String> ans = new ArrayList<>();
        boolean isBlockComment = false;
        StringBuilder sb = new StringBuilder();
        for (String line : source) {
            int len = line.length();
            for (int i = 0; i < len; i++) {
                if (isBlockComment) {
                    if (i + 1 < len && line.charAt(i) == '*' && line.charAt(i + 1) == '/') {
                        // 块注释结束
                        i++;
                        isBlockComment = false;
                        continue;
                    }
                } else {
                    if (i + 1 < len && line.charAt(i) == '/' && line.charAt(i + 1) == '/') {
                        // 行注释，跳过该行剩余部分
                        break;
                    } else if (i + 1 < len && line.charAt(i) == '/' && line.charAt(i + 1) == '*') {
                        // 块注释起始
                        i++;
                        isBlockComment = true;
                        continue;
                    } else {
                        sb.append(line.charAt(i));
                    }
                }
            }
            if (!isBlockComment && sb.length() > 0) {
                // 非块注释正常换行（块注释会删除隐含的换行符）
                ans.add(sb.toString());
                sb.setLength(0);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] source = {"/*Test program */", "int main()", "{ ", "  // variable declaration ", "int a, b, c;", "/* This is a test", "   multiline  ", "   comment for ", "   testing */", "a = b + c;", "}"};
        System.out.println("test: " + sol.removeComments(source));
    }
}