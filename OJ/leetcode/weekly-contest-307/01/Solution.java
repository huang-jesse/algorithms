import java.util.Arrays;

class Solution {
    public int minNumberOfHours(int initialEnergy, int initialExperience, int[] energy, int[] experience) {
        int ans = 0;
        int n = energy.length;
        int needEnergy = Arrays.stream(energy).sum();
        if (needEnergy + 1 > initialEnergy) {
            ans += needEnergy + 1 - initialEnergy;
        }

        for (int i = 0; i < n; i++) {
            int cur = experience[i];
            if (initialExperience <= cur) {
                int needEx = cur - initialExperience + 1;
                initialExperience += needEx;
                ans += needEx;
            }
            initialExperience += cur;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int initialEnergy = 5;
        int initialExperience = 3;
        int[] energy = {1,4,3,2};
        int[] experience = {2,6,3,1};
        System.out.println("test: " + sol.minNumberOfHours(initialEnergy, initialExperience, energy, experience));
    }
}