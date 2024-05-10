class Solution {
    public int minSwapsCouples(int[] row) {
        int n = row.length;
        if (n <= 2) {
            return 0;
        }
        int[] indexes = new int[n];
        for (int i = 0; i < n; i++) {
            indexes[row[i]] = i;
        }
        // greedy
        int ans = 0;
        for (int i = 0; i < n - 1; i += 2) {
            int id1 = row[i];
            int id2 = -1;
            if (id1 % 2 == 0) {
                // even
                id2 = id1 + 1;
            } else {
                // odd
                id2 = id1 - 1;
            }
            if (indexes[id2] == i + 1) {
                continue;
            } else {
                // need swap
                swap(row, indexes, i + 1, indexes[id2]);
                ans++;
            }
        }
        return ans;
    }

    private void swap(int[] row, int[] indexes, int i, int j) {
        int temp = row[i];
        row[i] = row[j];
        row[j] = temp;
        indexes[row[i]] = i;
        indexes[row[j]] = j;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] row = {0,4,5,2,3,1};
        System.out.println("test: " + sol.minSwapsCouples(row));
    }
}