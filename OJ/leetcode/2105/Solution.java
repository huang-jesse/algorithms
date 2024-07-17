class Solution {
    public int minimumRefill(int[] plants, int capacityA, int capacityB) {
        int n = plants.length;
        int numOfRefill = 0;
        int l = 0;
        int r = n - 1;
        int curA = capacityA;
        int curB = capacityB;
        while (l < r) {
            if (curA < plants[l]) {
                curA = capacityA;
                numOfRefill++;
            }
            curA -= plants[l];
            if (curB < plants[r]) {
                curB = capacityB;
                numOfRefill++;
            }
            curB -= plants[r];
            l++;
            r--;
        }
        if (l == r) {
            // they reach then same plant
            if (Math.max(curA, curB) < plants[l]) {
                // need refill one more time
                numOfRefill++;
            }
        }
        return numOfRefill;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] plants = {2,2,3,3};
        int capacityA = 3;
        int capacityB = 4;
        System.out.println("test: " + sol.minimumRefill(plants, capacityA, capacityB));
    }
}