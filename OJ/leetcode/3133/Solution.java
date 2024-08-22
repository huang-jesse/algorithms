class Solution {
    public long minEnd(int n, int x) {
        n = n - 1;
        int bitLimit = Integer.SIZE - Integer.numberOfLeadingZeros(n);
        long ans = 0L | x;
        for (int i = 0, j = 0; i <= bitLimit; i++) {
            // find 0-bit position in ans
            while (((ans >> j) & 1) == 1) j++;
            // set ith-bit of n to ans in j position
            long setBit = (n >> i) & 1;
            ans |= (setBit << j);
            j++;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int n = 3;
        // int x = 4; // res: 6
        int n = 6715154;
        int x = 7193485; // res: 6
        System.out.println("test: " + sol.minEnd(n, x));
    }
}