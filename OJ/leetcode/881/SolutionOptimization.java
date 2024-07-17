import java.util.Arrays;

class SolutionOptimization {
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int n = people.length;
        int l = 0;
        int r = n - 1;
        int count = 0;
        while (l <= r) {
            if (people[l] + people[r] <= limit) {
                l++;
                r--;
                count++;
            } else {
                r--;
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        SolutionOptimization sol = new SolutionOptimization();
        int[] people = {3,2,2,1};
        int limit = 3;
        System.out.println("test: " + sol.numRescueBoats(people, limit));
    }
}