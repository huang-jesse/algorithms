class Solution {
    public boolean escapeGhosts(int[][] ghosts, int[] target) {
        int distance = getDistance(new int[]{0, 0}, target);
        for (int i = 0; i < ghosts.length; i++) {
            int[] ghost = ghosts[i];
            int shortestDsOfGhost = getDistance(ghost, target);
            if (shortestDsOfGhost <= distance) {
                return false;
            }
        }
        return true;
    }

    private static int getDistance(int[] source, int[] target) {
        return Math.abs(source[0] - target[0]) + Math.abs(source[1] - target[1]);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] ghosts = {{5,0},{-10,-2},{0,-5},{-2,-2},{-7,1}};
        int[] target = {7,7};
        System.out.println("test: " + sol.escapeGhosts(ghosts, target));
    }
}