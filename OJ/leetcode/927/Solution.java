import java.util.Arrays;
import java.util.stream.Collectors;

class Solution {
    public int[] threeEqualParts(int[] arr) {
        int n = arr.length;
        int allOneCount = Arrays.stream(arr).sum();
        if (allOneCount % 3 != 0) {
            // cannot divide
            return new int[]{-1, -1};
        }
        if (allOneCount == 0) {
            return new int[]{0, 2};
        }
        int partial = allOneCount / 3;
        int first = 0;
        int second = 0;
        int third = 0;
        int oneCount = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] == 1) {
                if (oneCount == 0) {
                    first = i;
                } else if (oneCount == partial) {
                    second = i;
                } else if (oneCount == 2 * partial) {
                    third = i;
                }
                oneCount++;
            }
            if (first > 0 && second > 0 && third > 0) {
                break;
            }
        }

        // find three parts
        int validPartLen = (n - 1) - third + 1;
        if (first + validPartLen - 1 >= second || second + validPartLen - 1 >= third) {
            // cannot divide
            return new int[]{-1, -1};
        }
        // check each parts
        for (int i = 1; i < validPartLen; i++) {
            if (arr[first + i] != arr[second + i] || arr[second + i] != arr[third + i]) {
                // cannot divide
                return new int[]{-1, -1};
            }
        }
        // find the answer
        return new int[]{first + validPartLen - 1, second + validPartLen};
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] arr = {0,1,0,1,0,1,0,1,1,0,1};
        // int[] arr = {1,0,0};
        System.out.println("test: " + Arrays.stream(sol.threeEqualParts(arr)).boxed().collect(Collectors.toList()));
    }
}