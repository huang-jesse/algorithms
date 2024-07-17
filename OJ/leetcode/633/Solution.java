class Solution {
    public boolean judgeSquareSum(int c) {
        if (c == 0) return true;
        int left = 0;
        int right = (int)Math.sqrt(c);

        while (left <= right) {
            int aPow = (int)Math.pow(left, 2);
            int bPow = (int)Math.pow(right, 2);
            int temp = c - aPow;
            if (temp == bPow) {
                return true;
            }
            if (temp < bPow) {
                right--;
            } else {
                left++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int c = Integer.MAX_VALUE - 1;
        // int c = 109;
        System.out.println("judgeSquareSum: "+ sol.judgeSquareSum(c));
    }
}