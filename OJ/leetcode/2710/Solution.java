class Solution {
    public String removeTrailingZeros(String num) {
        int n = num.length();
        int right = n - 1;
        while (right >= 0 && num.charAt(right) == '0') {
            right--;
        }
        return num.substring(0, right + 1);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String num = "51230100";
        // String num = "123";
        System.out.println("test: " + sol.removeTrailingZeros(num));
    }
}