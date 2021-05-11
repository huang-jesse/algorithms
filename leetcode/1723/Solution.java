import java.util.Arrays;

class Solution {
    public int minimumTimeRequired(int[] jobs, int k) {
        Arrays.sort(jobs);
        reverseOrder(jobs);
        
        int left = jobs[0];
        int right = Arrays.stream(jobs).sum();
        while (left < right) {
            int mid = left + ((right - left) / 2);
            if (checkIfCanloadLimit(jobs, k, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    private boolean checkIfCanloadLimit(int[] jobs, int k, int limit) {
        int[] workloads = new int[k];
        workloads[0] = jobs[0];
        return backtrack(jobs, workloads, limit, 1);
    }

    private boolean backtrack(int[] jobs, int[] workloads, int limit, int index) {
        if (index >= jobs.length)
            return true;
        int cur = jobs[index];
        for (int i = 0; i < workloads.length; i++) {
            boolean isThisWorkerCanLoad = workloads[i] + cur <= limit;
            if (isThisWorkerCanLoad) {
                workloads[i] = workloads[i] + cur;
                if (backtrack(jobs, workloads, limit, index+1))
                    return true;
                workloads[i] = workloads[i] - cur;
            }
            boolean isWithoutWorkRemain = workloads[i] == 0;
            boolean isLoadFailure = workloads[i] + cur == limit;
            if (isWithoutWorkRemain || isLoadFailure)
                return false;
        }
        return false;
    }

    private static void reverseOrder(int[] arr) {
        int low = 0;
        int high = arr.length-1;
        while (low < high) {
            int temp = arr[low];
            arr[low] = arr[high];
            arr[high] = temp;
            low++;
            high--;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[] jobs = new int[] {1,2,4,7,8};
        int[] jobs = new int[] {9};
        int k = 1;
        System.out.println("minimumTimeRequired: " + sol.minimumTimeRequired(jobs, k));
    }
}