import java.util.Arrays;
import java.util.stream.Collectors;

class Solution {
    public int[] sumPrefixScores(String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }
        int n = words.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = trie.prefixCount(words[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] words = {"abc","ab","bc","b"};
        int[] ans = sol.sumPrefixScores(words);
        System.out.println("test: " + Arrays.stream(ans).boxed().collect(Collectors.toList()));
    }
}

class Trie {
    class TrieNode {
        int count = 0;
        TrieNode[] children = new TrieNode[26];
    }

    TrieNode root;
    public Trie() {
        root = new TrieNode();
    }

    public void insert(String s) {
        TrieNode p = root;
        for(int i = 0; i < s.length(); i++) {
            int u = s.charAt(i) - 'a';
            if (p.children[u] == null) p.children[u] = new TrieNode();
            p.children[u].count += 1;
            p = p.children[u];
        }
    }

    public int prefixCount(String s) {
        TrieNode p = root;
        int count = 0;
        for(int i = 0; i < s.length(); i++) {
            int u = s.charAt(i) - 'a';
            if (p.children[u] == null) return count;
            count += p.children[u].count;
            p = p.children[u];
        }
        return count;
    }
}
