class Solution {
    public boolean checkZeroOnes(String s) {
        char[] sArr = s.toCharArray();
        int count0 = 0;
        int count1 = 0;
        int temp0 = 0;
        int temp1 = 0;
        for (int i = 0; i < sArr.length; i++) {
            char cur = sArr[i];
            if (cur == '1') {
                temp1++;
            } else {
                count1 = Math.max(count1, temp1);
                temp1 = 0;
            }

            if (cur == '0') {
                temp0++;
            } else {
                count0 = Math.max(count0, temp0);
                temp0 = 0;
            }
        }
        count1 = Math.max(count1, temp1);
        count0 = Math.max(count0, temp0);

        return count1 > count0;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "111000";
        System.out.println("test: " + sol.checkZeroOnes(s));
    }
}