class Solution {
    public int wateringPlants(int[] plants, int capacity) {
        int n = plants.length;
        int ans = 0;
        int currentCap = capacity;
        for (int i = 0; i < n; i++) {
            if (currentCap < plants[i]) {
                currentCap = capacity;
                // refill water can and walk to (i - 1)
                ans += i * 2;
            }
            currentCap -= plants[i];
            ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] plants = {1,1,1,4,2,3};
        int capacity = 4; // ans = 30
        System.out.println("test: " + sol.wateringPlants(plants, capacity));
    }
}