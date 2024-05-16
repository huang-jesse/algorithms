class Solution {
    public long numberOfWeeks(int[] milestones) {
        int max = 0;
        long sum = 0L;
        for (int milestone : milestones) {
            max = Math.max(max, milestone);
            sum += milestone;
        }
        long theLargestMilestoneConsume = Math.min(sum - max + 1, max);
        long theOthersConsume = sum - max;
        return theLargestMilestoneConsume + theOthersConsume;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[] milestones = {5,2,1};
        // int[] milestones = {5,2,1};
        int[] milestones = {4,3,3,2};
        System.out.println("test: " + sol.numberOfWeeks(milestones));
    }
}