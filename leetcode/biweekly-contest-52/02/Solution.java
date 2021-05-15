class Solution {
    public int[] memLeak(int memory1, int memory2) {
        int[] ans = new int[3];
        int i = 1;
        while (i <= memory1 || i <= memory2) {
            if (memory1 == memory2) {
                memory1 -= i;
            } else if (memory1 > memory2) {
                memory1 -= i;
            } else {
                memory2 -= i;
            }
            i++;
        }
        ans[0] = i;
        ans[1] = memory1;
        ans[2] = memory2;
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int memory1 = 0;
        int memory2 = 0;
        System.out.println("test: " + sol.memLeak(memory1, memory2));
    }
}