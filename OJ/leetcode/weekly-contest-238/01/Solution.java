class Solution {
    public int sumBase(int n, int k) {
        int sum = 0;
        String bases = Integer.toString(n, k);
        System.out.println("bases: " + bases);
        for (int i = 0; i < bases.length(); i++) {
            sum += Integer.valueOf(String.valueOf(bases.charAt(i)));
        }
        return sum;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println("sumBase: " + sol.sumBase(34, 6));
    }
}