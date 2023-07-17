class Solution {
    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int n = num1.length();
        int m = num2.length();
        int i = n - 1;
        int j = m - 1;
        int carryDigit = 0;
        while (i >= 0 || j >= 0 || carryDigit != 0) {
            int currentDigit = carryDigit;
            if (i >= 0) {
                currentDigit += (num1.charAt(i) - '0');
                i--;
            }
            if (j >= 0) {
                currentDigit += (num2.charAt(j) - '0');
                j--;
            }
            carryDigit = currentDigit / 10;
            sb.append(currentDigit % 10);
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String num1 = "456";
        String num2 = "77";
        System.out.println("test: " + sol.addStrings(num1, num2));
    }
}