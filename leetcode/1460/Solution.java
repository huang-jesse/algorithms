class Solution {
    public boolean canBeEqual(int[] target, int[] arr) {
        int[] counter = new int[1001];
        int n = target.length;
        for (int i = 0; i < n; i++) {
            counter[target[i]]++;
            counter[arr[i]]--;
        }
        for (int count : counter) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] target = {1,2,3,4};
        int[] arr = {2,4,1,3};
        System.out.println("test: " + sol.canBeEqual(target, arr));
    }
}