import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class SolutionSort {
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        int n = arr.length;
        if (n == 2)
            return new int[] {1, arr[1]};
        int total = n*(n-1) / 2;
        int[][] fractionArr = new int[total][2];
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                fractionArr[index++] = new int[] {arr[i], arr[j]};
            }
        }
        Comparator<int[]> fractionCompare = (o1, o2) -> o1[0]*o2[1] - o2[0]*o1[1];
        Arrays.sort(fractionArr, fractionCompare);
        return fractionArr[k-1];
    }

    public static void main(String[] args) {
        SolutionSort sol = new SolutionSort();
        int[] arr = {1,2,3,5};
        int k = 3;
        List<Integer> fraction = Arrays.stream(sol.kthSmallestPrimeFraction(arr, k)).boxed().collect(Collectors.toList());
        System.out.println("test: " + fraction);
    }
}