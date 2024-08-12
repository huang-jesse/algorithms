import java.util.HashSet;
import java.util.Set;

class MagicDictionaryBruteForce {
    private final Set<String> strSet;

    public MagicDictionaryBruteForce() {
        this.strSet = new HashSet<>();
    }

    public void buildDict(String[] dictionary) {
        for (String str : dictionary) {
            this.strSet.add(str);
        }
    }

    public boolean search(String searchWord) {
        int len = searchWord.length();
        char[] charArr = searchWord.toCharArray();
        for (int i = 0; i < len; i++) {
            char oldChar = charArr[i];
            for(char c = 'a'; c <= 'z'; c++) {
                if (c == oldChar) continue;
                charArr[i] = c;
                if (this.strSet.contains(new String(charArr))) return true;
                charArr[i] = oldChar;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        MagicDictionaryBruteForce md = new MagicDictionaryBruteForce();
        String[] dictionary = {"hello", "leetcode"};
        md.buildDict(dictionary);
        System.out.println("test: " + md.search("hlllo"));
    }
}