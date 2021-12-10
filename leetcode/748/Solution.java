class Solution {
    private final static int LETTER_NUM = 26;
    private final static char LETTER_LOWER_A = 'a';
    private final static char LETTER_UPPER_A = 'A';
    private int[] licenseCounter = new int[LETTER_NUM];
    public String shortestCompletingWord(String licensePlate, String[] words) {
        for (int i = 0; i < licensePlate.length(); i++) {
            char cur = licensePlate.charAt(i);
            if ((cur - LETTER_LOWER_A) >= 0 && (cur - LETTER_LOWER_A) < LETTER_NUM) {
                licenseCounter[cur - LETTER_LOWER_A]++;
            } else if ((cur - LETTER_UPPER_A) >= 0 && (cur - LETTER_UPPER_A) < LETTER_NUM) {
                licenseCounter[cur - LETTER_UPPER_A]++;
            }
        }

        int minLen = Integer.MAX_VALUE;
        String ans = null;
        for (String word : words) {
            if (isCompletingWord(word) && word.length() < minLen) {
                ans = word;
                minLen = ans.length();
            }
        }
        return ans;
    }

    private boolean isCompletingWord(String word) {
        int[] wordCounter = new int[LETTER_NUM];
        for (int i = 0; i < word.length(); i++) {
            wordCounter[word.charAt(i) - LETTER_LOWER_A]++;
        }
        for (int i = 0; i < LETTER_NUM; i++) {
            if (wordCounter[i] < licenseCounter[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String licensePlate = "1s3 PSt";
        String[] words = {"step", "steps", "stripe", "stepple"};
        System.out.println("test: " + sol.shortestCompletingWord(licensePlate, words));
    }
}