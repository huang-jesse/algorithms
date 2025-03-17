class Solution {
    public int largestVariance(String s) {
        char[] chars = s.toCharArray();
        int ans = 0;
        for (char a = 'a'; a <= 'z'; a++) {
            for (char b = 'a'; b <= 'z'; b++) {
                if (a == b) continue;
                int f0 = 0;
                int f1 = Integer.MIN_VALUE;
                for (char cur : chars) {
                    // f0
                    if (cur == a || cur == b) {
                        f0 = Math.max(f0, 0) + (cur == a ? 1 : -1);
                    } else {
                        f0 = Math.max(f0, 0) + 0;
                    }
                    // f1
                    if (cur == a) {
                        f1 = f1 + 1;
                    } else if (cur == b) {
                        f1 = f0;
                    }
                    // else cur == other, f1 = pref1
                    ans = Math.max(ans, f1);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "aababbb"; // ans = 3
        System.out.println("test: " + sol.largestVariance(s));
    }
}