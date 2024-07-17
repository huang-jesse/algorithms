import java.util.Arrays;
import java.util.List;

class Solution {
    public int longestValidSubstring(String word, List<String> forbidden) {
        Trie forbiddenTrie = new Trie();
        for (String str : forbidden) {
            forbiddenTrie.insert(str);
        }
        int n = word.length();
        int left = n - 1;
        int right = left;
        int ans = 0;
        while (left >= 0) {
            if (!isValid(forbiddenTrie, word, left, right)) {
                // 不合法，子字符串 word[left...right] 包含 forbidden 中的字符串
                if (left < right) {
                    // 右指针向左后退，接下来会检查字符串 word[left...right-1] 是否合法
                    // right--;
                    // 剪枝优化，由于 forbidden 中的字符串长度不超过 10 ，因此 word[left, left + 9] 及其右侧的部分都不合法
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
     * @param forbiddenTrie
     * @param word
     * @param left
     * @param right
     * @return
     */
    private boolean isValid(Trie forbiddenTrie, String word, int left, int right) {
        // forbidden 不会有超过 10 长度的字符串，而且以 word[left+1] 开头的字符串 word[left+1...n-1] 之前已经检查过了。
        // 所以仅检查子字符串 word[left...rightBoundary] 是否合法即可
        StringBuilder sb = new StringBuilder();
        int rightBoundary = Math.min(right, left + 9);
        for (int i = left; i <= rightBoundary; i++) {
            sb.append(word.charAt(i));
        }
        // 如果子字符串 word[left...rightBoundary] 及其前缀不存在与 forbiddenTrie 中则合法
        return !forbiddenTrie.searchPrefix(sb.toString());
    }

    class Trie {
        class TrieNode {
            // 字符集大小
            private static final int C = 26;
            private TrieNode[] children = new TrieNode[C];
            private boolean isEnd;
        }

        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            int n = word.length();
            TrieNode p = root;
            for (int i = 0; i < n; i++) {
                int cur = word.charAt(i) - 'a';
                if (p.children[cur] == null) {
                    p.children[cur] = new TrieNode();
                }
                p = p.children[cur];
            }
            p.isEnd = true;
        }

        public boolean searchPrefix(String word) {
            int n = word.length();
            TrieNode p = root;
            for (int i = 0; i < n; i++) {
                int cur = word.charAt(i) - 'a';
                if (p.children[cur] == null) {
                    return false;
                }
                p = p.children[cur];
                if (p.isEnd) {
                    return true;
                }
            }
            return p.isEnd;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String word = "cbaaaabc";
        List<String> forbidden = Arrays.asList("aaa","cb");
        System.out.println("test: " + sol.longestValidSubstring(word, forbidden));
    }
}