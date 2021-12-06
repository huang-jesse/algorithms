class Solution {
    public String truncateSentence(String s, int k) {
        int n = s.length();
        StringBuilder ans = new StringBuilder();
        int i = 0;
        while (k > 0) {
            if (i != 0) {
                ans.append(" ");
            }
            while (i < n && s.charAt(i) != ' ') {
                ans.append(s.charAt(i));
                i++;
            }
            k--;
            i++;
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "Hello how are you Contestant";
        int k = 4;
        System.out.println("test: " + sol.truncateSentence(s, k));
    }
}