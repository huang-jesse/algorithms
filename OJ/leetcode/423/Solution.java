import java.util.HashMap;
import java.util.Map;

class Solution {
    public String originalDigits(String s) {
        Map<Character, Integer> charsCounter = new HashMap<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char cur = s.charAt(i);
            charsCounter.put(cur, charsCounter.getOrDefault(cur, 0) + 1);
        }
        int[] digitCounter = new int[10];
        // zero
        digitCounter[0] = charsCounter.getOrDefault('z', 0);
        // two
        digitCounter[2] = charsCounter.getOrDefault('w', 0);
        // four
        digitCounter[4] = charsCounter.getOrDefault('u', 0);
        // six
        digitCounter[6] = charsCounter.getOrDefault('x', 0);
        // eight
        digitCounter[8] = charsCounter.getOrDefault('g', 0);

        // one
        digitCounter[1] = charsCounter.getOrDefault('o', 0) - digitCounter[0] - digitCounter[2] - digitCounter[4];
        // three
        digitCounter[3] = charsCounter.getOrDefault('h', 0) - digitCounter[8];
        // five
        digitCounter[5] = charsCounter.getOrDefault('f', 0) - digitCounter[4];
        // seven
        digitCounter[7] = charsCounter.getOrDefault('s', 0) - digitCounter[6];

        // nine
        digitCounter[9] = charsCounter.getOrDefault('i', 0) - digitCounter[5] - digitCounter[6] - digitCounter[8];

        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            int count = digitCounter[i];
            for (int j = 0; j < count; j++) {
                ans.append((char) (i + '0'));
            }
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "owoztneoer";
        System.out.println("test: " + sol.originalDigits(s));
    }
}