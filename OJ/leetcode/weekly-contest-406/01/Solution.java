class Solution {
    public String getSmallestString(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();
        for (int i = 0; i < n - 1; i++) {
            int cur = arr[i] - '0';
            int next = arr[i + 1] - '0';
            if (cur % 2 == next % 2 && cur > next) {
                char temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
                break;
            }
        }
        return new String(arr);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "45320";
        System.out.println("test: " + sol.getSmallestString(s));
    }
}