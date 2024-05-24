import java.util.Arrays;
import java.util.List;

public class SolutionTest {

    private Solution solution;

    public SolutionTest() {
        solution = new Solution();
    }

    public void testLongestEqualSubarray_NormalCase() {
        List<Integer> nums = Arrays.asList(1, 2, 2, 3, 3, 3);
        int k = 1;
        int expected = 3;
        int result = solution.longestEqualSubarray(nums, k);
        assertResult(expected, result);
    }

    public void testLongestEqualSubarray_SingleElement() {
        List<Integer> nums = Arrays.asList(1);
        int k = 0;
        int expected = 1;
        int result = solution.longestEqualSubarray(nums, k);
        assertResult(expected, result);
    }

    public void testLongestEqualSubarray_NoMatchingElements() {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);
        int k = 0;
        int expected = 1;
        int result = solution.longestEqualSubarray(nums, k);
        assertResult(expected, result);
    }

    public void testLongestEqualSubarray_MaxK() {
        List<Integer> nums = Arrays.asList(1, 1, 2, 2, 2, 1, 1);
        int k = 3;
        int expected = 4; // Updated expected value to 4
        int result = solution.longestEqualSubarray(nums, k);
        assertResult(expected, result);
    }

    public void testLongestEqualSubarray_KEqualsZero() {
        List<Integer> nums = Arrays.asList(1, 1, 2, 2, 2, 1, 1);
        int k = 0;
        int expected = 3;
        int result = solution.longestEqualSubarray(nums, k);
        assertResult(expected, result);
    }

    public void testLongestEqualSubarray_AllSameElements() {
        List<Integer> nums = Arrays.asList(1, 1, 1, 1, 1);
        int k = 2;
        int expected = 5;
        int result = solution.longestEqualSubarray(nums, k);
        assertResult(expected, result);
    }

    public void testLongestEqualSubarray_LargeK() {
        List<Integer> nums = Arrays.asList(1, 2, 2, 3, 3, 3, 1, 1);
        int k = 5;
        int expected = 3;
        int result = solution.longestEqualSubarray(nums, k);
        assertResult(expected, result);
    }

    public void testLongestEqualSubarray_EmptyList() {
        List<Integer> nums = Arrays.asList();
        int k = 1;
        int expected = 1;
        int result = solution.longestEqualSubarray(nums, k);
        assertResult(expected, result);
    }

    public void testLongestEqualSubarray_LargeInput() {
        List<Integer> nums = Arrays.asList(1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5);
        int k = 3;
        int expected = 3;
        int result = solution.longestEqualSubarray(nums, k);
        assertResult(expected, result);
    }

    private void assertResult(int expected, int result) {
        if (expected != result) {
            throw new AssertionError("Expected " + expected + ", but got " + result);
        }
    }

    public static void main(String[] args) {
        SolutionTest test = new SolutionTest();
        test.testLongestEqualSubarray_NormalCase();
        test.testLongestEqualSubarray_SingleElement();
        test.testLongestEqualSubarray_NoMatchingElements();
        test.testLongestEqualSubarray_MaxK();
        test.testLongestEqualSubarray_KEqualsZero();
        test.testLongestEqualSubarray_AllSameElements();
        test.testLongestEqualSubarray_LargeK();
        test.testLongestEqualSubarray_EmptyList();
        test.testLongestEqualSubarray_LargeInput();
    }
}
