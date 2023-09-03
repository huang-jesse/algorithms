class Solution {
    public int eliminateMaximum(int[] dist, int[] speed) {
        int n = dist.length;
        int max = 0;
        for (int i = 0; i < n; i++) {
            int arriveTime = (int)Math.ceil((double)dist[i] / speed[i]);
            max = Math.max(max, arriveTime);
        }
        int[] arriveTimeCounter = new int[max + 1];
        for (int i = 0; i < n; i++) {
            int arriveTime = (int)Math.ceil((double)dist[i] / speed[i]);
            arriveTimeCounter[arriveTime]++;
        }
        int ans = 0;
        int time = 0;
        for (int arriveTime = 1; arriveTime <= max; arriveTime++) {
            int count = arriveTimeCounter[arriveTime];
            int eliminateNum = Math.min(count, arriveTime - time);
            ans += eliminateNum;
            time += eliminateNum;
            if (eliminateNum < count) {
                // 说明未完全消灭时，怪兽到达
                break;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[] dist = {3};
        // int[] speed = {5};
        int[] dist = {1,1,2,3};
        int[] speed = {1,1,1,1};
        // int[] dist = {1,3,4};
        // int[] speed = {1,1,1};
        System.out.println("test: " + sol.eliminateMaximum(dist, speed));
    }
}