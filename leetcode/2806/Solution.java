class Solution {
    public int accountBalanceAfterPurchase(int purchaseAmount) {
        purchaseAmount = (purchaseAmount + 5) / 10 * 10;
        return 100 - purchaseAmount;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int purchaseAmount = 9;
        System.out.println("test: " + sol.accountBalanceAfterPurchase(purchaseAmount));
    }
}