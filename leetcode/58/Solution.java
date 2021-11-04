class Solution {
    public int lengthOfLastWord(String s) {
        char[] sChars = s.toCharArray();
        int n = sChars.length;
        int len = 0;
        int index = n-1;
        while (index >= 0) {
            char cur = sChars[index];
            if (cur == ' ') {
                if (len > 0) {
                    break;
                }
            } else {
                len++;
            }
            index--;
        }
        return len;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "Hello1 Worldaa ";
        System.out.println("test: " + sol.lengthOfLastWord(s));
    }
}