import java.util.Arrays;

class Solution {
    private static final int INF = 0x3fffffff;
    private TrieNode root;
    private int[] memo;
    public int minimumCost(String target, String[] words, int[] costs) {
        int n = words.length;
        this.root = new TrieNode();
        for (int i = 0; i < n; i++) {
            insert(words[i], costs[i]);
        }
        this.memo = new int[target.length()];
        Arrays.fill(memo, -1);
        int ans = backtrack(target, 0);
        return ans == INF ? -1 : ans;
    }

    private int backtrack(String target, int index) {
        int m = target.length();
        if (index >= m) return 0;
        if (this.memo[index] != -1) return this.memo[index];

        int res = INF;
        TrieNode p = root;
        for (int i = index; i < m; i++) {
            int cur = target.charAt(i) - 'a';
            if (p.children[cur] == null) {
                break;
            }
            p = p.children[cur];
            if (p.isEnd) {
                // substring
                res = Math.min(backtrack(target, i + 1) + p.cost, res);
            }
        }
        this.memo[index] = res;
        return res;
    }

    static class TrieNode {
        // 字符集大小
        private static final int C = 26;
        private TrieNode[] children = new TrieNode[C];
        private boolean isEnd;
        private int cost = INF;
    }

    public void insert(String word, int cost) {
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
        p.cost = Math.min(cost, p.cost);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // String target = "abcdef";
        // String[] words = {"abdef","abc","d","def","ef"};
        // int[] costs ={100,1,1,10,5}; // 7
        String target = "zyctznq";
        String[] words = {"ctznq","zy"};
        int[] costs ={13, 3}; // 16
        System.out.println("test: " + sol.minimumCost(target, words, costs));
    }
}