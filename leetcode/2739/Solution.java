class Solution {
    public int distanceTraveled(int mainTank, int additionalTank) {
        int consumeFuel = mainTank + Math.min((mainTank - 1) / 4, additionalTank);
        return consumeFuel * 10;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int mainTank = 5;
        int addtionalTank = 10;
        System.out.println("test: " + sol.distanceTraveled(mainTank, addtionalTank));
    }
}