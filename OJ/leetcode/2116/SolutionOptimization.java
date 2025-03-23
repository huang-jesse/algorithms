/**
 * 在遍历字符串的过程中，维护 c 的取值范围。如果最终 c 的取值范围不为空，且包含 0，那么可以把 s 变成有效括号字符串。
 * c 表示未匹配的左括号的个数
 */
class SolutionOptimization {
    public boolean canBeValid(String s, String locked) {
        int n = s.length();
        if (n % 2 != 0) return false;
        int mx = 0;
        int mn = 0;
        for (int i = 0; i < n; i++) {
            if (locked.charAt(i) == '1') {
                // 括号不可变更
                int d = s.charAt(i) == '(' ? 1 : -1;
                mn += d;
                mx += d;
                if (mx < 0) {
                    // 无法匹配
                    return false;
                }
            } else {
                // 括号可以变更
                mx++; // 变成左括号
                mn--; // 变成右括号
            }
            if (mn < 0) {
                mn = 1; // c不能为负
            }
        }
        return mn == 0;
    }

    public static void main(String[] args) {
        SolutionOptimization sol = new SolutionOptimization();
        String s = "))()))";
        String locked = "010100";
        System.out.println("test: " + sol.canBeValid(s, locked));
    }
}