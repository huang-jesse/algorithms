class Solution {
    public String smallestBeautifulString(String s, int k) {
        int n = s.length();
        char[] chars = s.toCharArray();
        int increaseIndex = findIncreaseIndex(chars, k);
        if (increaseIndex == -1) {
            return "";
        }
        int max = 'a' + k - 1;
        // chars[increaseIndex + 1] 到 chars[n - 1] 贪心填入与前两个字符不同的最小字符即可
        // 因为 k >= 4 所以一定可以找到与前两个字符不同的最小字符
        for (int i = increaseIndex + 1; i < n; i++) {
            for (int j = 'a'; j <= max; j++) {
                // 当前字符和前两个字符中的某一个相同，跳过
                if ((i > 0 && chars[i - 1] == j) || (i > 1 && chars[i - 2] == j)) continue;
                // 找到了一个可行字符，修改 chars[i]
                chars[i] = (char)j;
                break;
            }
        }
        return new String(chars);
    }

    /**
     * 这个函数从 s[n - 1] 开始尝试修改，返回最少要修改到哪个位置
     * @param chars
     * @param k
     * @return
     */
    private int findIncreaseIndex(char[] s, int k) {
        int n = s.length;
        int max = 'a' + k - 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = s[i] + 1; j <= max; j++) {
                // 当前字符和前两个字符中的某一个相同，跳过
                if ((i > 0 && s[i - 1] == j) || (i > 1 && s[i - 2] == j)) continue;
                // 找到了一个可行字符，修改 s[i]
                s[i] = (char)j;
                return i;
            }
        }
        // 改 s[0] 都不行，无解
        return -1;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "czyxz"; // res = "dabca"
        int k = 26;
        System.out.println("test: " + sol.smallestBeautifulString(s, k));
    }
}