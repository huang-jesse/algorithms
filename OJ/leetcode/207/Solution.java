import java.util.ArrayList;
import java.util.List;

class Solution {
    private Boolean[] memo;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        this.memo = new Boolean[numCourses];
        List<Integer>[] prerequisitesArr = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            prerequisitesArr[i] = new ArrayList<>();
        }
        for (int[] prerequisite : prerequisites) {
            prerequisitesArr[prerequisite[0]].add(prerequisite[1]);
        }
        boolean[] visisted = new boolean[numCourses];
        boolean isFinish = true;
        for (int i = 0; i < numCourses; i++) {
            visisted[i] = true;
            isFinish &= backtrack(i, prerequisitesArr, visisted);
            visisted[i] = false;
        }
        return isFinish;
    }

    private boolean backtrack(int course, List<Integer>[] prerequisitesArr, boolean[] visisted) {
        if (prerequisitesArr[course].isEmpty()) {
            return true;
        }
        if (memo[course] != null) {
            return memo[course];
        }
        List<Integer> prerequisiteList = prerequisitesArr[course];
        boolean isFinish = true;
        for (int preCourse : prerequisiteList) {
            if (visisted[preCourse]) {
                return false;
            }
            visisted[preCourse] = true;
            isFinish &= backtrack(preCourse, prerequisitesArr, visisted);
            visisted[preCourse] = false;
            if (!isFinish) {
                break;
            }
        }
        memo[course] = isFinish;
        return isFinish;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int numCourses = 2;
        // int[][] prerequisites = {{1,0},{0,1}};
        int[][] prerequisites = {{1,0}};
        System.out.println("test: " + sol.canFinish(numCourses, prerequisites));
    }
}