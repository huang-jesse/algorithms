class Solution {
    public int minimizedStringLength(String s) {
        int mask = 0;
        for (int i = 0; i < s.length(); i++) {
            mask |= 1 << (s.charAt(i) - 'a');
        }
        return Integer.bitCount(mask);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "aaabc"; // ans = 3
        System.out.println("test: " + sol.minimizedStringLength(s));
    }
}