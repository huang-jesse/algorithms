class Solution {
    public void merge(int[] A, int m, int[] B, int n) {
        int a = n+m-1; // merged A index
        int b = n-1; // B index
        int oa = m-1; // original A index
        while (b >= 0) {
            if (oa < 0) {
                A[a--] = B[b--];
            } else if (B[b] >= A[oa]) {
                A[a--] = B[b--];
            } else {
                A[a--] = A[oa--];
            }
        }
    }
}