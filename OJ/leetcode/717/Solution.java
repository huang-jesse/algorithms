class Solution {
    public boolean isOneBitCharacter(int[] bits) {
        int n = bits.length;
        int i = 0;
        boolean isOneBit = false;
        while (i < n) {
            int cur = bits[i];
            if (cur == 0) {
                isOneBit = true;
                i++;
            } else {
                isOneBit = false;
                i += 2;
            }
        }
        return isOneBit;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] bits = {1, 1, 1, 0};
        System.out.println("test: " + sol.isOneBitCharacter(bits));
    }
}