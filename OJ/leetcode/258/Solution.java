class Solution {
    public int addDigits(int num) {
        int res = num;
        while (true) {
            int temp = res;
            res = 0;
            while (temp > 0) {
                int digit = temp % 10;
                res += digit;
                temp = temp / 10;
            }
            if (res < 10) {
                break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int num = 38;
        System.out.println("test: " + sol.addDigits(num));
    }
}