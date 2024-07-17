class Solution {
    public int isPrefixOfWord(String sentence, String searchWord) {
        String[] sentenceArr = sentence.split(" ");
        int prefixLen = searchWord.length();
        int n = sentenceArr.length;
        int index = -1;
        for (int i = 0; i < n; i++) {
            String cur = sentenceArr[i];
            if (cur.length() >= prefixLen) {
                String prefix = cur.substring(0, prefixLen);
                if (searchWord.equals(prefix)) {
                    index = i;
                    break;
                }
            }
        }
        return index == -1 ? -1 : index + 1;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String sentence = "i love eating burger";
        String searchWord = "burg";
        System.out.println("test: " + sol.isPrefixOfWord(sentence, searchWord));
    }
}