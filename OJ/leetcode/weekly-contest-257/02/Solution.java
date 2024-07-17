import java.util.Arrays;

class Solution {
    public int numberOfWeakCharacters(int[][] properties) {
        int n = properties.length;
        Arrays.sort(properties, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0]);
        int ans = 0;
        int maxDefense = 0;
        for (int i = 0; i < n; i++) {
            int curDefense = properties[i][1];
            if (maxDefense > curDefense) {
                ans++;
            }
            maxDefense = Math.max(maxDefense, curDefense);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] properties = {{7,9},{10,7},{6,9},{10,4},{7,5},{7,10}};
        System.out.println("test: " + sol.numberOfWeakCharacters(properties));
    }
}