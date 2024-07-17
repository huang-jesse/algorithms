import java.util.Arrays;
import java.util.stream.Collectors;

class SolutionHash {
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
        HashHelper hashHelper = new HashHelper(arr);
        int firstValidRangeHash = hashHelper.getHash(first, first + validPartLen - 1);
        int secondValidRangeHash = hashHelper.getHash(second, second + validPartLen - 1);
        int thirdValidRangeHash = hashHelper.getHash(third, n - 1);
        if (firstValidRangeHash == secondValidRangeHash && secondValidRangeHash == thirdValidRangeHash) {
            // find the answer
            return new int[]{first + validPartLen - 1, second + validPartLen};
        } else {
            // cannot divide
            return new int[]{-1, -1};
        }
    }

    class HashHelper {
        private static final int B = 3;
        private static final int M = (int)1e9 + 7;
        private int[] hashes;
        private int[] bPow;
        public HashHelper(int[] arr) {
            int n = arr.length;
            hashes = new int[n + 1];
            bPow = new int[n + 1];
            bPow[0] = 1;
            for (int i = 1; i <= n; i++) {
                hashes[i] = (int)(((long)hashes[i - 1] * B + arr[i - 1]) % M);
                bPow[i] = (int)((long)bPow[i - 1] * B % M);
            }
        }
        private int getHash(int l, int r) {
            return (hashes[r + 1] + M - (int)((long)hashes[l] * bPow[r - l + 1] % M)) % M;
        }
    }

    public static void main(String[] args) {
        SolutionHash sol = new SolutionHash();
        int[] arr = {0,1,0,1,0,1,0,1,1,0,1};
        // int[] arr = {1,0,0};
        System.out.println("test: " + Arrays.stream(sol.threeEqualParts(arr)).boxed().collect(Collectors.toList()));
    }
}