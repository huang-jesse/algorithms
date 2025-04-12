class Solution {
    public int countSymmetricIntegers(int low, int high) {
        int ans = 0;
        for (int num = low; num <= high; num++) {
            char[] digits = Integer.toString(num).toCharArray();
            if (digits.length % 2 == 1) continue;
            int half = digits.length / 2;
            int diff = 0;
            for (int i = 0; i < half; i++) {
                diff += digits[i];
            }
            for (int i = half; i < digits.length; i++) {
                diff -= digits[i];
            }
            if (diff == 0) ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int low = 1200;
        int high = 1230;
        System.out.println("test: " + sol.countSymmetricIntegers(low, high));
    }
}