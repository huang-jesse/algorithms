class Solution {
    public int maximumLength(String s) {
        int n = s.length();
        int[][] subLenCounter = new int[26][3];
        int l = 0;
        int r = l;
        while (r < n) {
            while (r < n && s.charAt(r) == s.charAt(l)) {
                r++;
            }
            int subLen = r - l;
            int index = s.charAt(l) - 'a';
            if (subLen > subLenCounter[index][0]) {
                subLenCounter[index][2] = subLenCounter[index][1];
                subLenCounter[index][1] = subLenCounter[index][0];
                subLenCounter[index][0] = subLen;
            } else if (subLen > subLenCounter[index][1]) {
                subLenCounter[index][2] = subLenCounter[index][1];
                subLenCounter[index][1] = subLen;
            } else if (subLen > subLenCounter[index][2]){
                subLenCounter[index][2] = subLen;
            }
            l = r;
        }
        int res = 0;
        for (int[] subLenArr : subLenCounter) {
            res = Math.max(res, subLenArr[0] - 2);
            res = Math.max(res, Math.min(subLenArr[0] - 1, subLenArr[1]));
            res = Math.max(res, subLenArr[2]);
        }
        return res == 0 ? -1 : res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "abcaba";
        System.out.println("test: " + sol.maximumLength(s));
    }
}