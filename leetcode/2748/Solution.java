class Solution {
    public int countBeautifulPairs(int[] nums) {
        int n = nums.length;
        boolean[][] coprimes = new boolean[10][10];
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                coprimes[i][j] = gcd(i, j) == 1;
            }
        }

        int[] digitsCounter = new int[10];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            int lastDigit = num % 10;
            for (int k = 1; k < 10; k++) {
                if (coprimes[lastDigit][k]) {
                    ans += digitsCounter[k];
                }
            }
            while (num >= 10) {
                num /= 10;
            }
            int firstDigit = num;
            digitsCounter[firstDigit]++;
        }
        return ans;
    }

    private int gcd(int a, int b) {
        int remainder = a % b;
        while (remainder != 0) {
            a = b;
            b = remainder;
            remainder = a % b;
        }
        return b;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {2,5,1,4};
        System.out.println("test: " + sol.countBeautifulPairs(nums));
    }
}