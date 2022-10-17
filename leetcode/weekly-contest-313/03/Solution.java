class Solution {
    public int minimizeXor(int num1, int num2) {
        int bitCount = Integer.bitCount(num2);
        int ans = 0;
        for (int i = 30; i >= 0; i--) {
            if (bitCount <= 0) {
                break;
            }
            int mask = 1 << i;
            boolean isOneBit = (num1 & mask) == mask;
            // one bit
            if (isOneBit) {
                // clear one bit
                num1 = num1 ^ mask;
                // add one bit
                ans = ans | mask;
                bitCount--;
            }
        }
        for (int i = 0; i <= 30; i++) {
            if (bitCount <= 0) {
                break;
            }
            int mask = 1 << i;
            boolean isNum1OneBit = (num1 & mask) == mask;
            boolean isAnsOneBit = (ans & mask) == mask;
            if (isNum1OneBit || isAnsOneBit) {
                continue;
            }
            ans = ans | mask;
            bitCount--;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int num1 = 3;
        // int num2 = 5;// ans = 3
        int num1 = 1;
        int num2 = 12;// ans = 3
        // int num1 = 25;
        // int num2 = 72;// ans = 24
        System.out.println("test: " + sol.minimizeXor(num1, num2));
    }
}