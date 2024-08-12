import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class MagicDictionary {
    private static final int MOD = (int)(1e9 + 7);
    private static final char LETTER_A = 'a';
    private static final int R = 26;
    private final Map<Integer, List<String>> hashStrMap;
    private int[] bPow;
    private int maxLength;

    public MagicDictionary() {
        this.hashStrMap = new HashMap<>();
    }

    public void buildDict(String[] dictionary) {
        this.maxLength = 0;
        for (String str : dictionary) {
            int len = str.length();
            maxLength = Math.max(maxLength, len);
            long hashL = 0;
            for (int i = len - 1; i >= 0; i--) {
                hashL = (hashL * R + charInt(str.charAt(i))) % MOD;
            }
            int hash = (int)hashL;
            List<String> strList = this.hashStrMap.computeIfAbsent(hash, k -> new ArrayList<>(1));
            strList.add(str);
        }
        this.bPow = new int[maxLength];
        // initialize bPow
        bPow[0] = 1;
        for (int i = 1; i < maxLength; i++) {
            bPow[i] = (int)(((long)bPow[i - 1] * R) % MOD);
        }
    }

    public boolean search(String searchWord) {
        int len = searchWord.length();
        if (len > maxLength) return false;
        long hashL = 0;
        for (int i = len - 1; i >= 0; i--) {
            hashL = (hashL * R + charInt(searchWord.charAt(i))) % MOD;
        }
        for (int i = 0; i < len; i++) {
            int cur = charInt(searchWord.charAt(i));
            for (int digit = 0; digit < R; digit++) {
                if (cur == digit) continue;
                int changedHash = (int)((hashL + MOD - ((long)cur * bPow[i]) % MOD + ((long)digit * bPow[i]) % MOD) % MOD);
                if (check(changedHash, searchWord, i, (char)(LETTER_A + digit))) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean check(int hash, String searchWord, int changeIndex, char changeChar) {
        List<String> list = this.hashStrMap.getOrDefault(hash, Collections.emptyList());
        int len = searchWord.length();
        for (String str : list) {
            if (str.length() != searchWord.length()) continue;
            for (int i = 0; i < len; i++) {
                if (i == changeIndex) {
                    if (str.charAt(i) != changeChar) continue;
                } else {
                    if (str.charAt(i) != searchWord.charAt(i)) continue;
                }
            }
            return true;
        }
        return false;
    }

    private int charInt(char letter) {
        return letter - LETTER_A;
    }

    public static void main(String[] args) {
        MagicDictionary md = new MagicDictionary();
        String[] dictionary = {"hello", "leetcode"};
        md.buildDict(dictionary);
        System.out.println("test: " + md.search("hlllo"));
    }
}