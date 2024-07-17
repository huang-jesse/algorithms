class SolutionAdjacentDifference {
    public boolean checkArray(int[] nums, int k) {
        int n = nums.length;
        // Adjacent difference
        int[] differenceArr = new int[n + 1];
        long preSumDifference = 0L;
        for (int i = 0; i < n; i++) {
            // Accumulate prefix sum
            preSumDifference += differenceArr[i];

            int currentNum = nums[i];
            currentNum += preSumDifference;
            if (currentNum == 0) {
                continue;
            }
            if (currentNum < 0 || i + k > n) {
                // Cannot do anything else
                return false;
            }
            // Constructe adjacent difference
            differenceArr[i] -= currentNum;
            differenceArr[i + k] += currentNum;
            // Accumulate prefix sum
            preSumDifference -= currentNum;
        }
        return true;
    }

    public static void main(String[] args) {
        SolutionAdjacentDifference sol = new SolutionAdjacentDifference();
        // int[] nums = {2,3,3,2,1,1,0};
        // int k = 3;
        int[] nums = {2,2,3,1,1,0};
        int k = 3;
        System.out.println("test: " + sol.checkArray(nums, k));
    }
}