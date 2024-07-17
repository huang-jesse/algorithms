class SolutionBinarySearch {
    public int hIndex(int[] citations) {
        int low = 0;
        int high = citations.length;
        while (low < high) {
            int mid = low + ((high - low + 1) >> 1);
            if (check(citations, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    private boolean check(int[] citations, int h) {
        int count = 0;
        for (int citation : citations) {
            if (citation >= h) {
                count++;
            }
        }
        return count >= h;
    }

    public static void main(String[] args) {
        SolutionBinarySearch sol = new SolutionBinarySearch();
        int[] citations = {1,3,1};
        System.out.println("test: " + sol.hIndex(citations));
    }
}