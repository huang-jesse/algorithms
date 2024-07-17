import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

class Solution {
    private final static Set<Character> vowels = new HashSet<>() {{
        add('a');
        add('e');
        add('i');
        add('o');
        add('u');
    }};
    public String sortVowels(String s) {
        PriorityQueue<Character> pq = new PriorityQueue<>((o1, o2) -> o1 - o2);
        char[] charArr = s.toCharArray();
        for (int i = 0; i < charArr.length; i++) {
            if (vowels.contains(Character.toLowerCase(charArr[i]))) {
                pq.offer(charArr[i]);
            }
        }
        for (int i = 0; i < charArr.length; i++) {
            if (vowels.contains(Character.toLowerCase(charArr[i]))) {
                charArr[i] = pq.poll();
            }
        }
        return new String(charArr);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "lEetcOde";// lEOtcede
        System.out.println("test: " + sol.sortVowels(s));
    }
}