class Solution {
    public boolean mergeTriplets(int[][] triplets, int[] target) {
        boolean temp = false;
        for (int[] arr : triplets) {
            if (arr[0] == target[0] && arr[1] <= target[1] && arr[2] <= target[2]) {
                temp = true;
            }
        }
        if (!temp) return false;

        temp = false;
        for (int[] arr : triplets) {
            if (arr[1] == target[1] && arr[0] <= target[0] && arr[2] <= target[2]) {
                temp = true;
            }
        }
        if (!temp) return false;

        temp = false;
        for (int[] arr : triplets) {
            if (arr[2] == target[2] && arr[0] <= target[0] && arr[1] <= target[1]) {
                temp = true;
            }
        }
        if (!temp) return false;

        return true;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[][] triplets = {{2,5,3},{1,8,4},{1,7,5}};
        // int[] target = {2,7,5};
        
        int[][] triplets = {{2,3,4},{4,4,5},{3,5,5}};
        int[] target = {2,3,5};
        System.out.println("test: " + sol.mergeTriplets(triplets, target));
    }
}