class Solution {
    public int balancedStringSplit(String s) {
        char[] chars = s.toCharArray();
        int ans = 0;
        int countOfL = 0;
        int countOfR = 0;
        for (char cur : chars) {
            if (cur == 'L') {
                countOfL++;
            } else {
                countOfR++;
            }

            if (countOfL > 0 && countOfL == countOfR) {
                ans++;
                countOfL = 0;
                countOfR = 0;
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "LLLLRRRR";
        System.out.println("test: " + sol.balancedStringSplit(s));
    }
}