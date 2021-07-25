import java.util.Arrays;

class Solution {
    public int hIndex(int[] citations) {
        int n = citations.length;
        if (n == 0)
            return 0;
        if (n == 1) {
            return Math.min(1, citations[0]);
        }
        Arrays.sort(citations);
        int left = -1;
        int right = n-1;
        while (left < right) {
            int mid = left + ((right - left + 1) >> 1);
            int citation = citations[mid];
            int numOfPaper = n - mid;
            if (numOfPaper > citation) {
                left = mid;
            } else {
                // numOfPaper <= citation
                right = mid - 1;
            }
        }
        return n - left - 1;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] citations = {1,3,1};
        System.out.println("test: " + sol.hIndex(citations));
    }
}