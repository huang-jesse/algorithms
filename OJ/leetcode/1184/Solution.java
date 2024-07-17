class Solution {
    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        if (start > destination) {
            int temp = start;
            start = destination;
            destination = temp;
        }
        int sum1 = 0, sum2 = 0;
        for (int i = 0; i < distance.length; i++) {
            if (i >= start && i < destination) {
                sum1 += distance[i];
            } else {
                sum2 += distance[i];
            }
        }
        return Math.min(sum1, sum2);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] distance = {1,2,3,4};
        int start = 0;
        int destination = 2;
        System.out.println("test: " + sol.distanceBetweenBusStops(distance, start, destination));
    }
}