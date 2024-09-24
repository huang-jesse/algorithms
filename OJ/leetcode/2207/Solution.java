class Solution {
    public long maximumSubsequenceCount(String text, String pattern) {
        char x = pattern.charAt(0);
        char y = pattern.charAt(1);
        int xCount = 0;
        int yCount = 0;
        long ans = 0;
        for (char c : text.toCharArray()) {
            if (c == y) {
                ans += xCount;
                yCount++;
            }
            if (c == x) {
                xCount++;
            }
        }
        return ans + Math.max(xCount, yCount);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String text = "bdcadbc";
        String pattern = "ac"; // res: 3
        System.out.println("test: " + sol.maximumSubsequenceCount(text, pattern));
    }
}