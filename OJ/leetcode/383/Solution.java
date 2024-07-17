class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] magazineCounter = new int[26];
        for (int i = 0; i < magazine.length(); i++) {
            char cur = magazine.charAt(i);
            magazineCounter[cur - 'a']++;
        }

        for (int i = 0; i < ransomNote.length(); i++) {
            char cur = ransomNote.charAt(i);
            magazineCounter[cur - 'a']--;
            if (magazineCounter[cur - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String randomNote = "aa";
        String magazine = "aaa";
        System.out.println("test: " + sol.canConstruct(randomNote, magazine));
    }
}