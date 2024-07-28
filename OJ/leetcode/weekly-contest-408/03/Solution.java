class Solution {
    public int numberOfSubstrings(String s) {
        int n = s.length();
        int zeroCount = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') zeroCount++;
        }
        int[] zeroIndexes = new int[zeroCount];
        int zeroIndex = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') zeroIndexes[zeroIndex++] = i;
        }

        for (int i = 0; i < zeroCount; i++) {
            for (int j = i + 1; j < zeroCount; j++) {
                int zeros = j - i + 1;
                if (zeros * zeros > (n - zeros)) break;


            }
        }


        return 0;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "101101"; // 16
        System.out.println("test: " + sol.numberOfSubstrings(s));
    }
}