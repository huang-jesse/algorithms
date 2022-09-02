class Solution {
    public int garbageCollection(String[] garbage, int[] travel) {
        int ans = 0;
        // M
        ans += getGarbageTime(garbage, travel, 'M');
        // P
        ans += getGarbageTime(garbage, travel, 'P');
        // G
        ans += getGarbageTime(garbage, travel, 'G');
        return ans;
    }

    private int getGarbageTime(String[] garbage, int[] travel, Character type) {
        int n = garbage.length;
        int res = 0;
        boolean isTravel = false;
        for (int i = n - 1; i > 0; i--) {
            String curGarbage = garbage[i];
            int count = countGarbage(curGarbage, type);
            if (count > 0) {
                isTravel = true;
                res += count;
            }
            if (isTravel) {
                res += travel[i - 1];
            }
        }
        res += countGarbage(garbage[0], type);
        return res;
    }

    private int countGarbage(String s, Character type) {
        int count = 0;
        for (char cur : s.toCharArray()) {
            if (cur == type) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] garbage = {"MMM","PGM","GP"};
        int[] travel = {3, 10};
        System.out.println("test: " + sol.garbageCollection(garbage, travel));
    }
}