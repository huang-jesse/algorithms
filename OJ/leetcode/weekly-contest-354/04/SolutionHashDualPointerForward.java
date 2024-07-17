import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class SolutionHashDualPointerForward {
    public int longestValidSubstring(String word, List<String> forbidden) {
        Set<String> forbiddenSet = new HashSet<>();
        forbiddenSet.addAll(forbidden);

        int n = word.length();
        int left = 0;
        int right = left;
        int ans = 0;
        while (right < n) {
            if (!isValid(forbiddenSet, word, left, right)) {
                // 不合法，子字符串 word[left...right] 包含 forbiddenSet 中的字符串
                if (left < right) {
                    // 左指针向左前进，接下来会检查字符串 word[left+1...right] 是否合法
                    // left++;
                    // 剪枝优化，由于 forbiddenSet 中的字符串长度不超过 10 ，因此 word[right - 9, right] 及其左侧的部分都不合法
                    // 所以优化到从 word[right - 9 + 1, right] 开始重新检查
                    left = Math.max(right - 9 + 1, left + 1);
                } else {
                    // 左右指针重叠，所以左右指针同时向左前进
                    right++;
                    left = right;
                }
            } else {
                // 子字符串 word[left...right] 为合法字符串
                ans = Math.max(ans, right - left + 1);
                right++;
            }
        }
        return ans;
    }

    /**
     * 检查以 word[right] 结尾的字符串 word[left...right] 是否合法
     * 从右往左检查 word[right], word[right-1,right]...wrod[left,...,right] 的每个子字符串是否合法
     * @param forbiddenSet
     * @param word
     * @param left
     * @param right
     * @return
     */
    private boolean isValid(Set<String> forbiddenSet, String word, int left, int right) {
        // forbiddenSet 不会有超过 10 长度的字符串，而且以 word[right-1] 结尾的字符串 word[0...right-1] 之前已经检查过了。
        // 所以仅检查子字符串 word[leftBoundary...right] 是否合法即可
        int leftBoundary = Math.max(left, right - 9);
        for (int i = right; i >= leftBoundary; i--) {
            String str = word.substring(i, right + 1);
            if (forbiddenSet.contains(str)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        SolutionHashDualPointerForward sol = new SolutionHashDualPointerForward();
        String word = "cbaaaabc";
        List<String> forbidden = Arrays.asList("aaa","cb");
        System.out.println("test: " + sol.longestValidSubstring(word, forbidden));
    }
}