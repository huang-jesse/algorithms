class Solution {
    public int minSwaps(String s) {
        char[] chars = s.toCharArray();
        int count = 0;
        int min = 0;
        for (char cur : chars) {
            if (cur == '[') {
                count++;
            } else {
                count--;
                min = Math.min(min, count);
            }
        }
        return (-min + 1) >> 1;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "][][";
        System.out.println("test: " + sol.minSwaps(s));
    }
}