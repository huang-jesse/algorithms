class Solution {
    public String solveEquation(String equation) {
        String leftEquation = equation.split("=")[0];
        String rightEquation = equation.split("=")[1];

        int[] leftEquationNums = getEquationNums(leftEquation);
        int leftXCoefficient = leftEquationNums[0];
        int leftNum = leftEquationNums[1];

        int[] rightEquationNums = getEquationNums(rightEquation);
        int rightXCoefficient = rightEquationNums[0];
        int rightNum = rightEquationNums[1];

        leftXCoefficient = leftXCoefficient - rightXCoefficient;
        rightXCoefficient = 0;
        rightNum = rightNum - leftNum;
        leftNum = 0;

        if (leftXCoefficient != 0) {
            rightNum = (int)(rightNum / leftXCoefficient);
            leftXCoefficient = 1;
            return "x=" + String.valueOf(rightNum);
        } else {
            if (rightNum == 0) {
                return "Infinite solutions";
            } else {
                return "No solution";
            }
        }
    }

    private static int[] getEquationNums(String equation) {
        int numCoefficient = 0;
        int xCoefficient = 0;

        int operation = 1;
        int curNum = 0;
        for (int i = 0; i < equation.length(); i++) {
            char cur = equation.charAt(i);
            if (cur == '+' || cur == '-') {
                curNum *= operation;
                numCoefficient += curNum;
                curNum = 0;
                operation = cur == '+' ? 1 : -1;
            } else if (cur == 'x') {
                if (i == 0 || !Character.isDigit(equation.charAt(i-1))) {
                    curNum = 1;
                }
                curNum *= operation;
                xCoefficient += curNum;
                curNum = 0;
            } else {
                curNum = curNum * 10 + Character.getNumericValue(cur);
            }
        }
        curNum *= operation;
        numCoefficient += curNum;
        return new int[] {xCoefficient, numCoefficient};
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String equation = "x+5-3+x=6+x-2";
        // String equation = "1=2";
        System.out.println("test: " + sol.solveEquation(equation));
    }
}