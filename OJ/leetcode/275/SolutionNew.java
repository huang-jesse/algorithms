class Solution {
    public int hIndex(int[] citations) {
        int n = citations.length;
        if (citations[n - 1] == 0) {
            return 0;
        }
        int left = 0;
        int right = n - 1;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (citations[mid] >= (n - mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return n - left;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] citations = {0,1,3,5,5,6,6};
        System.out.println("test: " + sol.hIndex(citations));
    }
}