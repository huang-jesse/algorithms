class Solution {
    public String complexNumberMultiply(String num1, String num2) {
        int[] num1Arr = strToArr(num1);
        int[] num2Arr = strToArr(num2);
        int[] ansArr = new int[2];
        // real
        ansArr[0] += num1Arr[0] * num2Arr[0];
        ansArr[0] += -(num1Arr[1] * num2Arr[1]);
        // imaginary
        ansArr[1] += num1Arr[0] * num2Arr[1];
        ansArr[1] += num1Arr[1] * num2Arr[0];
        return arrToStr(ansArr);
    }

    private static int[] strToArr(String num) {
        String[] nums = num.split("\\+");
        String imaginaryVal = nums[1].split("i")[0];
        return new int[]{Integer.valueOf(nums[0]), Integer.valueOf(imaginaryVal)};
    }

    private static String arrToStr(int[] arr) {
        return  String.format("%d+%di", arr[0], arr[1]);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String num1 = "1+-1i";
        String num2 = "1+-1i";
        System.out.println("test: " + sol.complexNumberMultiply(num1, num2));
    }
}