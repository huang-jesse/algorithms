import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    private static final int INF = 0x3fffffff;
    public int minimumCost(String target, String[] words, int[] costs) {
        int m = words.length;
        Map<String, Integer> costsMap = new HashMap<>();
        for (int i = 0; i < m; i++) {
            costsMap.merge(words[i], costs[i], Integer::min);
        }
        int totalLen = 0;
        Set<Integer> lenSet = new HashSet<>();
        for (String word : costsMap.keySet()) {
            totalLen += word.length();
            lenSet.add(word.length());
        }
        // k 个类型的 len 进行离散化
        int k = lenSet.size();
        int[] lenArr = new int[k];
        int t = 0;
        for (int len : lenSet) {
            lenArr[t++] = len;
        }
        Arrays.sort(lenArr);
        int[] lenIndexes = new int[totalLen + 1];
        for (int i = 0; i < k; i++) {
            lenIndexes[lenArr[i]] = i;
        }

        SuffixArrayBinaryLiftingOptimization sa = new SuffixArrayBinaryLiftingOptimization(target);
        int n = target.length();
        // from[i][j] the min cost of suffix[0:i] and len(word) == j
        int[][] from = new int[n + 1][k];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(from[i], INF);
        }
        // sum(len(word)) = L <= 5*1e4
        // total time complexity: O(L*logn) + O(n*sqrt(L))
        for (Map.Entry<String, Integer> entry : costsMap.entrySet()) {
            String word = entry.getKey();
            int len = word.length();
            int cost = entry.getValue();
            // |s| = len(word)
            // time complexity: O(|s|*logn)
            int[] leftRight = sa.lookup(word);
            if (leftRight.length == 0) continue;
            int left = leftRight[0];
            int right = leftRight[1];
            for (int i = left; i <= right; i++) {
                int l = sa.index(i);
                int r = l + word.length();
                from[r][lenIndexes[len]] = cost;
            }
            totalLen += word.length();
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = INF;
            for (int j = 0; j < k && i - lenArr[j] >= 0; j++) {
                int len = lenArr[j];
                int cost = from[i][j];
                dp[i] = Math.min(dp[i], dp[i - len] + cost);
            }
        }
        return dp[n] < INF ? dp[n] : -1;
    }

    /**
     * 优化版本，倍增构造后缀数组 O(nlog(n)) 时间复杂度
     *
     * <p> 1-indexed
     *
     * <p> sa[i] rk[i] 的值均从 1 开始，即 sa[i], rk[i] 的取值范围为 [1,n]
     */
    public class SuffixArrayBinaryLiftingOptimization {
        // letters and numbers
        private static final int R = 128;
        private final int n;
        private final int[] sa;
        private final int[] oldSa;
        private final int[] rk;
        private final int[] oldRk;
        private final int[] counter;
        private String s;

        public SuffixArrayBinaryLiftingOptimization(String s) {
            this.s = s;
            this.n = s.length();
            this.sa = new int[n * 2 + 1];
            this.oldSa = new int[n + 1];
            this.rk = new int[n * 2 + 1];
            this.oldRk = new int[n * 2 + 1];
            this.counter = new int[Math.max(n + 1, R + 1)];
            int m = R;
            // 计数排序（单字符）
            for (int i = 1; i <= n; i++) {
                counter[s.charAt(i - 1)]++;
                rk[i] = s.charAt(i - 1);
            }
            for (int i = 1; i <= m; i++) counter[i] += counter[i - 1];
            for (int i = n; i >= 1; i--) sa[counter[rk[i]]--] = i;
            System.arraycopy(rk, 0, oldRk, 0, n + 1);
            int rank = 0;
            // 生成 rk （保证字符相同时 rk[i]=rk[j] ）
            for (int i = 1; i <= n; i++) {
                if (oldRk[sa[i]] == oldRk[sa[i - 1]]) {
                    rk[sa[i]] = rank;
                } else {
                    rk[sa[i]] = ++rank;
                }
            }
            // 倍增进行第1关键字和第2关键字的计数排序
            // 每次对 rk 进行更新之后，我们都计算了一个 rank ，这个 rank 即是 rk 的值域，将值域改成它即可。
            for (int w = 1; w <= n; w = w << 1, m = rank) {
                // 对第2关键字 oldsa[i] + w 排序
                // 可以不使用计数排序
                // 我们的目标是得到一个 rk(sa[i] + w) 单调不减（升序）的数组。实际上，我们可以把当前数组分为两部分：
                // 第一部分，是那些满足 sa[i] + w > n 的 sa[i] ，因为这部分 rk(sa[i] + w) 必然等于 0 ，所以它们会处于数组的开头部分即覆盖了 i 的 [1, w] 部分。
                // 第二部分，我们观察发现，在 sa[i] 中，如果 sa[i] > w ，说明 rk(sa[i]) 在排序中一定会作为 sa[i] - w 的第2关键字。
                // 而对于 sa[i] 而言，rk(sa[i]) 是单调不减的，所以对于符合条件的 sa[i] ，把 t = sa[i] - w 依次放入数组，则 rk(t + w) 即是 rk(sa[i]) 就是单调不减的；即覆盖了 i 的 [w+1, n] 部分
                Arrays.fill(counter, 0);
                System.arraycopy(sa, 0, oldSa, 0, n + 1);
                int cur = 0;
                for (int i = n - w + 1; i <= n; i++) oldSa[++cur] = i;
                for (int i = 1; i <= n; i++) {
                    if (sa[i] > w) {
                        oldSa[++cur] = sa[i] - w;
                    }
                }
                // 对第1关键字 oldsa[i] 排序
                for (int i = 1; i <= n; i++) counter[rk[oldSa[i]]]++;
                for (int i = 1; i <= m; i++) counter[i] += counter[i - 1];
                for (int i = n; i >= 1; i--) sa[counter[rk[oldSa[i]]]--] = oldSa[i];
                // 重新计算 rk
                rank = 0;
                System.arraycopy(rk, 0, oldRk, 0, n + 1);
                for (int i = 1; i <= n; i++) {
                    if (oldRk[sa[i]] == oldRk[sa[i - 1]] && oldRk[sa[i] + w] == oldRk[sa[i - 1] + w]) {
                        rk[sa[i]] = rank;
                    } else {
                        rk[sa[i]] = ++rank;
                    }
                }
                // 考虑新的 rk 数组，若其值域为 [1,n] 那么每个排名都不同，此时无需再排序。
                if (rank == n) break;
            }
        }

        // length of s
        public int length() {
            return this.n;
        }

        /**
         * returns index of ith sorted suffix
         * <p> 0-indexed
         * @param i
         * @return
         */
        public int index(int i) {
            return this.sa[i + 1] - 1;
        }

        /**
         * returns rank of ith origin suffix
         * <p> 0-indexed
         * @param i
         * @return
         */
        public int rank(int i) {
            return this.rk[i + 1] - 1;
        }

        /**
         * return index arr [left, right] of suffix that starts with target, if no such elements return []
         * <p> 0-indexed
         * @param target
         * @return
         */
        public int[] lookup(String target) {
            int left = binarySearchLeftBound(target);
            if (left == -1) return new int[]{};
            int right = binarySearchRightBound(left, target);
            if (left > right) return new int[]{};
            return new int[]{left, right};
        }

        private int binarySearchLeftBound(String target) {
            if (suffixCompareTo(this.index(this.n - 1), target) < 0) return -1;
            int l = 0;
            int r = this.n - 1;
            while (l < r) {
                int mid = l + ((r - l) >> 1);
                int suffixStartIndex = this.index(mid);
                if (suffixCompareTo(suffixStartIndex, target) >= 0) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            return l;
        }

        private int binarySearchRightBound(int leftLimit, String target) {
            // not match with target
            if (!this.s.startsWith(target, this.index(leftLimit))) return -1;
            int l = leftLimit;
            int r = this.n - 1;
            while (l < r) {
                int mid = l + ((r - l + 1) >> 1);
                int suffixStartIndex = this.index(mid);
                if (this.s.startsWith(target, suffixStartIndex)) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            return l;
        }

        /**
         * suffix[start:-1] compare to target
         * @param start
         * @param target
         * @return
         */
        private int suffixCompareTo(int start, String target) {
            int l = start;
            int r = Math.min(start + target.length() - 1, this.n - 1);
            for (int i = l; i <= r; i++) {
                char c = s.charAt(i);
                char t = target.charAt(i - start);
                if (c == t) continue;
                if (c < t) return -1;
                if (c > t) return 1;
            }
            int suffixLen = this.n - start;
            if (target.length() > suffixLen) {
                // suffix[start:-1] < target
                return -1;
            } else if (target.length() < suffixLen) {
                // suffix[start:-1] > target
                return 1;
            } else {
                // target.length() == suffixLen
                // suffix[start:-1] == target
                return 0;
            }
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String target = "abcdef";
        String[] words = {"abdef","abc","d","def","ef"};
        int[] costs = {100,1,1,10,5}; // 7
        System.out.println("test: " + sol.minimumCost(target, words, costs));
    }
}