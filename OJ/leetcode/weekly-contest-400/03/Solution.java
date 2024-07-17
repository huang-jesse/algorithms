import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

class Solution {
    private static final char STAR = '*';
    public String clearStars(String s) {
        int n = s.length();
        char[] charArr = s.toCharArray();
        Set<Integer> indexesSet = new HashSet<>();
        Comparator<Integer> compare = (o1, o2) -> {
            int compareFirst = charArr[o1] - charArr[o2];
            if (compareFirst == 0) {
                return o2 - o1;
            } else {
                return compareFirst;
            }
        };
        PriorityQueue<Integer> pq = new PriorityQueue<>(compare);
        pq.add(0);
        for (int i = 1; i < n; i++) {
            char cur = s.charAt(i);
            if (cur != STAR) {
                pq.add(i);
            } else {
                // star
                int leastIndex = pq.poll();
                indexesSet.add(leastIndex);
                indexesSet.add(i);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (indexesSet.contains(i)) continue;
            sb.append(charArr[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "aaba*ccc**";
        System.out.println("test: " + sol.clearStars(s));
    }
}