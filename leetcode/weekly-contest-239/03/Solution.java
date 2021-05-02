class Solution {

    public int getMinSwaps(String num, int k) {
        int[] arr = new int[num.length()];
        for (int i = 0; i < num.length(); i++) {
            arr[i] = num.charAt(i) - '0';
        }
        
        return 1;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String num = "123";
        int k = 1;
        System.out.println("test: " + sol.getMinSwaps(num, k));
    }
}