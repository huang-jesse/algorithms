import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
    public int[] maximizeXor(int[] nums, int[][] queries) {
        int n = nums.length;
        int m = queries.length;
        Arrays.sort(nums);

        int[][] newQueries = new int[m][3];
        for (int i = 0; i < m; i++) {
            newQueries[i][0] = queries[i][0];
            newQueries[i][1] = queries[i][1];
            newQueries[i][2] = i;
        }
        Comparator<int[]> comparator = (q1, q2) -> q1[1] - q2[1];
        Arrays.sort(newQueries, comparator);

        Trie trie = new Trie();
        int index = 0;
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            int xi = newQueries[i][0];
            int mi = newQueries[i][1];
            int qIndex = newQueries[i][2];
            while (index < n && mi >= nums[index]) {
                trie.insert(nums[index]);
                index++;
            }
            if (index == 0) {
                ans[qIndex] = -1;
            } else {
                ans[qIndex] = trie.getMaxXor(xi);
            }
        }

        return ans;
    }

    private class Trie {
        static final int L = 30;
        Trie[] children = new Trie[2];

        public void insert(int num) {
            Trie node = this;
            for (int i = L-1; i >= 0; i--) {
                int bit = (num >> i) & 1;
                if (node.children[bit] == null) {
                    node.children[bit] = new Trie();
                }
                node = node.children[bit];
            }
        }

        public int getMaxXor(int num) {
            int max = 0;
            Trie node = this;
            for (int i = L-1; i >= 0; i--) {
                int bit = (num >> i) & 1;
                if (node.children[bit ^ 1] != null) {
                    node = node.children[bit ^ 1];
                    max = max | (1 << i);
                } else {
                    node = node.children[bit];
                }
            }
            return max;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {0,1,2,3,4};
        int[][] queries = {{3,1},{1,3},{5,6}};
        int[] ans = sol.maximizeXor(nums, queries);
        List<Integer> ansList = Arrays.stream(ans).boxed().collect(Collectors.toList());
        System.out.println("test: " + ansList);
    }
}