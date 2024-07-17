class Solution {
    public int sumOfTheDigitsOfHarshadNumber(int x) {
        int sumOfDigits = 0;
        int num = x;
        while (num > 0) {
            sumOfDigits += num % 10;
            num /= 10;
        }
        return x % sumOfDigits == 0 ? sumOfDigits : -1;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int num = 18;
        System.out.println("test: " + sol.sumOfTheDigitsOfHarshadNumber(num));
    }
}