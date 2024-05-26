import java.util.PriorityQueue;

class Solution {
    public int kthLargestValue(int[][] matrix, int k) {
        int n = matrix.length;
        int m = matrix[0].length;
        PriorityQueue<Integer> minPq = new PriorityQueue<>();
        int[] xorSum = new int[m];
        for (int i = 0; i < n; i++) {
            int[] temp = new int[m];
            int rowXor = 0;
            for (int j = 0; j < m; j++) {
                rowXor ^= matrix[i][j];
                temp[j] = rowXor ^ xorSum[j];

                minPq.offer(temp[j]);
                if (minPq.size() > k) {
                    minPq.poll();
                }
            }
            xorSum = temp;
        }
        return minPq.poll();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] matrix = new int[][] {{10,9,5},{2,0,4},{1,0,9},{3,4,8}};
        int k = 10;
        System.out.println("test: " + sol.kthLargestValue(matrix, k));
    }
}