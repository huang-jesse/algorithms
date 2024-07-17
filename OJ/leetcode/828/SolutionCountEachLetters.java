import java.util.Arrays;

class SolutionCountEachLetters {
    public int uniqueLetterString(String s) {
        int n = s.length();
        if (n == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int ans = 0;
        int preCount = 0;
        int[] lastIndexesOfLetters = new int[26];
        Arrays.fill(lastIndexesOfLetters, -1);
        int[] lastPlusValOfLetters = new int[26];
        for (int i = 0; i < n; i++) {
            int curChar = chars[i] - 'A';
            int indexOfSameCurChar = lastIndexesOfLetters[curChar];
            int lastPlusVal = lastPlusValOfLetters[curChar];
            int curPlusVal = i - indexOfSameCurChar;
            int curCount = preCount - lastPlusVal + curPlusVal;
            ans += curCount;
            preCount = curCount;
            lastIndexesOfLetters[curChar] = i;
            lastPlusValOfLetters[curChar] = curPlusVal;
        }
        return ans;
    }

    public static void main(String[] args) {
        SolutionCountEachLetters sol = new SolutionCountEachLetters();
        String s = "AAA";
        System.out.println("test: " + sol.uniqueLetterString(s));
    }
}