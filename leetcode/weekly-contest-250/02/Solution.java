class Solution {
    public int addRungs(int[] rungs, int dist) {
        int n = rungs.length;
        int pre = 0;
        int index = 0;
        int ans = 0;
        while (index < n) {
            int next = rungs[index];
            if ((long)pre + dist < next) {
                int diff = next - pre - dist;
                pre += diff;
                ans += (int)Math.ceil((double)diff / dist);
            } else {
                pre = next;
                index++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] rungs = {5};
        int dist = 10;
        System.out.println("test: " + sol.addRungs(rungs, dist));
    }
}