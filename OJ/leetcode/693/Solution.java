class Solution {
    public boolean hasAlternatingBits(int n) {
        boolean ans = true;
        int digit = 1;
        while ((n >> digit) > 0) {
            if (getBit(n, digit) == getBit(n, digit-1)) {
                ans = false;
                break;
            }
            digit++;
        }
        return ans;
    }

    private int getBit(int n, int digit) {
        return (n >> digit) & 1;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 7;
        System.out.println("test: " + sol.hasAlternatingBits(n));
    }
}