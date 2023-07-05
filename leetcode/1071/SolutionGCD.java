class SolutionGCD {
    public String gcdOfStrings(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int gcdLen = gcd(n, m);
        String gcdStr = str1.substring(0, gcdLen);
        // Check
        if (check(gcdStr, str1) && check(gcdStr, str2)) {
            return gcdStr;
        }
        return "";
    }

    private int gcdRecursion(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
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

    private boolean check(String subStr, String str) {
        int times = str.length() / subStr.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            sb.append(subStr);
        }
        return sb.toString().equals(str);
    }

    public static void main(String[] args) {
        SolutionGCD sol = new SolutionGCD();
        String str1 = "ABCABC";
        String str2 = "ABC";
        System.out.println("test: " + sol.gcdOfStrings(str1, str2));
    }
}