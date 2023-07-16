import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class SolutionHashDualPointerBackward {
    public int longestValidSubstring(String word, List<String> forbidden) {
        Set<String> forbiddenSet = new HashSet<>();
        forbiddenSet.addAll(forbidden);

        int n = word.length();
        int left = n - 1;
        int right = left;
        int ans = 0;
        while (left >= 0) {
            if (!isValid(forbiddenSet, word, left, right)) {
                // 不合法，子字符串 word[left...right] 包含 forbiddenSet 中的字符串
                if (left < right) {
                    // 右指针向左后退，接下来会检查字符串 word[left...right-1] 是否合法
                    // right--;
                    // 剪枝优化，由于 forbiddenSet 中的字符串长度不超过 10 ，因此由于 word[left, left + 9] 已经不合法，其右侧的部分自然都不合法
                    // 所以优化到从 word[left, left + 9 - 1] 开始重新检查
                    right = Math.min(left + 9 - 1, right - 1);
                } else {
                    // 左右指针重叠，所以左右指针同时向左后退
                    left--;
                    right = left;
                }
            } else {
                // 子字符串 word[left...right] 为合法字符串
                ans = Math.max(ans, right - left + 1);
                left--;
            }
        }
        return ans;
    }

    /**
     * 检查以 word[left] 为首的字符串 word[left...right] 是否合法
     * 从左往右检查 word[left], word[left,left+1]...wrod[left,...,right] 的每个子字符串是否合法
     * @param forbiddenSet
     * @param word
     * @param left
     * @param right
     * @return
     */
    private boolean isValid(Set<String> forbiddenSet, String word, int left, int right) {
        // forbiddenSet 不会有超过 10 长度的字符串，而且以 word[left+1] 开头的字符串 word[left+1...n-1] 之前已经检查过了。
        // 所以仅检查子字符串 word[left...rightBoundary] 是否合法即可
        int rightBoundary = Math.min(right, left + 9);
        for (int i = left; i <= rightBoundary; i++) {
            String str = word.substring(left, i + 1);
            if (forbiddenSet.contains(str)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        SolutionHashDualPointerBackward sol = new SolutionHashDualPointerBackward();
        String word = "cbaaaabc";
        List<String> forbidden = Arrays.asList("aaa","cb");
        System.out.println("test: " + sol.longestValidSubstring(word, forbidden));
    }
}