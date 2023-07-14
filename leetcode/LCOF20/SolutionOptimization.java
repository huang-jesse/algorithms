class SolutionOptimization {
    private int currentIndex;
    public boolean isNumber(String s) {
        s = s.trim();
        int n = s.length();
        if (n == 0) {
            return false;
        }
        currentIndex = 0;
        boolean numeric = scanInteger(s);
        // 如果出现'.'，接下来是数字的小数部分
        if (currentIndex < n && s.charAt(currentIndex) == '.') {
            currentIndex++;
            // 下面一行代码用||的原因：
            // 1. 小数可以没有整数部分，例如.123等于0.123；
            // 2. 小数点后面可以没有数字，例如233.等于233.0；
            // 3. 当然小数点前面和后面可以有数字，例如233.666
            numeric = scanUsignedInteger(s) || numeric;
        }

        // 如果出现'e'或者'E'，接下来跟着的是数字的指数部分
        if (currentIndex < n && (s.charAt(currentIndex) == 'e' || s.charAt(currentIndex) == 'E')) {
            currentIndex++;
            // 下面一行代码用&&的原因：
            // 1. 当e或E前面没有数字时，整个字符串不能表示数字，例如.e1、e1；
            // 2. 当e或E后面没有整数时，整个字符串不能表示数字，例如12e、12e+5.4
            numeric = scanInteger(s) && numeric;
        }
        return currentIndex == n && numeric;
    }

    /**
     * 整数的格式可以用[+|-]B表示, 其中B为无符号整数
     * @param s
     * @return
     */
    private boolean scanInteger(String s) {
        int n = s.length();
        if (currentIndex < n && (s.charAt(currentIndex) == '+' || s.charAt(currentIndex) == '-')) {
            currentIndex++;
        }
        return scanUsignedInteger(s);
    }

    private boolean scanUsignedInteger(String s) {
        int n = s.length();
        int beforeIndex = currentIndex;
        while (currentIndex < n && '0' <= s.charAt(currentIndex) && s.charAt(currentIndex) <= '9') {
            currentIndex++;
        }
        // 当str中存在若干0-9的数字时，返回true
        return currentIndex > beforeIndex;
    }

    public static void main(String[] args) {
        SolutionOptimization sol = new SolutionOptimization();
        String s = "12e+5";
        // String s = "12e+5.4";
        // String s = "-1E-16";
        // String s = "0e";
        // String s = "+-.";
        // String s = " -.";
        System.out.println("test: " + sol.isNumber(s));
    }
}