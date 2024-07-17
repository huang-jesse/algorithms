class SolutionOptimization {
    public int distributeCandies(int n, int limit) {
        return c2(n + 2) - (3 * c2(n - (limit + 1) + 2) - 3 * c2(n - 2 * (limit + 1) + 2) + c2(n - 3 * (limit + 1) + 2));
    }

    private static int c2(int n) {
        return n < 1 ? 0 : (n * (n - 1)) / 2;
    }

    public static void main(String[] args) {
        SolutionOptimization sol = new SolutionOptimization();
        int n = 5;
        int limit = 2;
        System.out.println("test: " + sol.distributeCandies(n, limit));
    }
}