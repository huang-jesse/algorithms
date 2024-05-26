class Solution {
    public String compressedString(String word) {
        int n = word.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char cur = word.charAt(i);
            int count = 1;
            while (count < 9 && i + 1 < n && word.charAt(i) == word.charAt(i + 1)) {
                count++;
                i++;
            }
            sb.append(count);
            sb.append(cur);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String word = "aaaaaaaaaaaaaabb";
        System.out.println("test: " + sol.compressedString(word));
    }
}