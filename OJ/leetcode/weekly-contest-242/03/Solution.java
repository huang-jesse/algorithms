class Solution {
    public boolean canReach(String s, int minJump, int maxJump) {
        char[] sArr = s.toCharArray();
        int n = sArr.length;
        int[] partialPreSum = new int[n+1];
        int ans = 1; // when ans == 1, current index is reachable
        partialPreSum[1] = 1;
        for (int i = 1; i < n; i++) {
            char cur = sArr[i];
            ans = 0;
            if (cur == '0') {
                // when i is reachable, then one of [i - maxJump, i - minJump] must be reachable
                int left = Math.max(0, i - maxJump);
                int right = i - minJump;
                if (right >= 0 && left <= right) {
                    // value of [i - maxJump, i - minJump]
                    boolean reachable = partialPreSum[right+1] - partialPreSum[left] > 0;
                    if (reachable) {
                        ans = 1;
                    }
                }
            }
            partialPreSum[i+1] = partialPreSum[i] + ans;
        }
        return ans == 1;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "00111010";
        int minJump = 3;
        int maxJump = 5;
        System.out.println("test: " + sol.canReach(s, minJump, maxJump));
    }
}