class Trie {
    class TrieNode {
        // 字符集大小
        private static final int C = 26;
        private TrieNode[] children = new TrieNode[C];
        private boolean isEnd;
        private int count = 0;
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
            p.children[cur].count += 1;
            p = p.children[cur];
        }
        p.isEnd = true;
    }

    public boolean search(String word) {
        int n = word.length();
        TrieNode p = root;
        for (int i = 0; i < n; i++) {
            int cur = word.charAt(i) - 'a';
            if (p.children[cur] == null) {
                return false;
            }
            p = p.children[cur];
        }
        return p.isEnd;
    }

    public boolean startsWith(String word) {
        int n = word.length();
        TrieNode p = root;
        for (int i = 0; i < n; i++) {
            int cur = word.charAt(i) - 'a';
            if (p.children[cur] == null) {
                return false;
            }
            p = p.children[cur];
        }
        return true;
    }

    public int prefixCount(String prefix) {
        int n = prefix.length();
        TrieNode p = root;
        for (int i = 0; i < n; i++) {
            int cur = prefix.charAt(i) - 'a';
            if (p.children[cur] == null) {
                return 0;
            }
            p = p.children[cur];
        }
        return p.count;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));   // 返回 True
        System.out.println(trie.search("app"));     // 返回 False
        System.out.println(trie.startsWith("app")); // 返回 True
        trie.insert("app");
        System.out.println(trie.search("app"));     // 返回 True
    }
}