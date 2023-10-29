class SolutionRadixSort {
    public int hIndex(int[] citations) {
        int[] counter = new int[1001];
        for (int citation : citations) {
            counter[citation]++;
        }
        int paperCount = 0;
        for (int i = 1000; i >= 0; i--) {
            paperCount += counter[i];
            if (paperCount >= i) {
                return i;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        SolutionRadixSort sol = new SolutionRadixSort();
        int[] citations = {1,3,1};
        System.out.println("test: " + sol.hIndex(citations));
    }
}