class Solution {
    public int secondsToRemoveOccurrences(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        int ans = 0;
        while (true) {
            boolean isContinue = false;
            for (int i = 0; i < n-1;) {
                char first = chars[i];
                char second = chars[i+1];
                if (first == '0' && second == '1') {
                    isContinue = true;
                    chars[i] = '1';
                    chars[i+1] = '0';
                    i = i + 2;
                } else {
                    i++;
                }
            }
            if (!isContinue) {
                break;
            }
            ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "0110101";
        System.out.println("test: " + sol.secondsToRemoveOccurrences(s));
    }
}