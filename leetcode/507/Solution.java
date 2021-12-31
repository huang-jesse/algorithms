class Solution {
    public boolean checkPerfectNumber(int num) {
        if (num == 1) {
            return false;
        }
        int sum = 1;
        for (int d = 2; d*d < num; d++) {
            if (num % d == 0) {
                sum += d;
                if (d*d < num) {
                    // exclusive duplicate count case when d*d == num
                    sum += (num / d);
                }
            }
        }
        return sum == num;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int num = 7;
        System.out.println("test: " + sol.checkPerfectNumber(num));
    }
}