class Solution {

    public int reverse(int x) {
        int ans = 0;
        while (x != 0) {
            // over int limit
            if (ans > 0 && ans > (Integer.MAX_VALUE - (x % 10)) / 10
                || ans < 0 && ans < (Integer.MIN_VALUE - (x % 10)) / 10) {
                return 0;
            }
            ans = ans * 10 + x % 10;
            x = x / 10;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // over int limit case
        // int num = 1563847412; // reverse to 2147483651
        // int num = 1463847412;
        int num = -2147483648;
        System.out.println("test: "+ sol.reverse(num));
    }
}