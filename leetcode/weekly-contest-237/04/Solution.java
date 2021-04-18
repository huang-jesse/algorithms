class Solution {
    public int getXORSum(int[] arr1, int[] arr2) {
        int xorSum = 0;
        int n = 30; // 10**9 < 2**30
        for (int i = 0; i < n; i++) {
            int[] aux1 = new int[2];
            int[] aux2 = new int[2];

            for (int x : arr1) {
                aux1[getBit(x, i)]++;
            }
            for (int x : arr2) {
                aux2[getBit(x, i)]++;
            }

            // if total bit 1(arr1 * arr2) is odd number then xorSum's bit of i is 1
            if ((long)aux1[1] * aux2[1] % 2 == 1) {
                // set i's bit to 1
                xorSum = setBit(xorSum, i);
            }
        }

        return xorSum;
    }

    /**
     * Get bit of position i
     * @param x
     * @param i
     * @return
     */
    private int getBit(int x, int i) {
        return (x >> i) & 1;
    }

    /**
     * Set bit 1 to position i
     * @param x
     * @param i
     */
    private int setBit(int x, int i) {
        return x | (1 << i);
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{12};
        int[] arr2 = new int[]{4};
        Solution sol = new Solution();
        System.out.println("XORSUM: "+ sol.getXORSum(arr1, arr2));
    }
}