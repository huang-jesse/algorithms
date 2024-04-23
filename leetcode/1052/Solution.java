class Solution {
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int n = customers.length;
        int[] unStatisfiedCustomers = new int[n];
        int satisfiedCount = 0;
        for (int i = 0; i < n; i++) {
            if (grumpy[i] == 1) {
                unStatisfiedCustomers[i] = customers[i];
            } else {
                satisfiedCount += customers[i];
            }
        }
        int maxSum = 0;
        int currentSum = 0;
        for (int i = 0; i < n; i++) {
            currentSum += unStatisfiedCustomers[i];
            if (i >= minutes) {
                currentSum -= unStatisfiedCustomers[i - minutes];
            }
            maxSum = Math.max(maxSum, currentSum);
        }
        return satisfiedCount + maxSum;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] customers = {1,0,1,2,1,1,7,5};
        int[] grumpy = {0,1,0,1,0,1,0,1};
        int minutes = 3; // ans = 16
        System.out.println("test: " + sol.maxSatisfied(customers, grumpy, minutes));
    }
}