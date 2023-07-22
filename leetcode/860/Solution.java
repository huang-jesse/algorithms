class Solution {
    public boolean lemonadeChange(int[] bills) {
        int fiveCounter = 0;
        int tenCounter = 0;
        for (int bill : bills) {
            if (bill == 5) {
                fiveCounter++;
            } else if (bill == 10) {
                fiveCounter--;
                tenCounter++;
            } else {
                // bill == 20
                if (tenCounter > 0) {
                    fiveCounter--;
                    tenCounter--;
                } else {
                    fiveCounter -= 3;
                }
            }
            if (fiveCounter < 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] bills = {5,5,10,10,20};
        // int[] bills = {5,5,5,10,20};
        System.out.println("test: " + sol.lemonadeChange(bills));
    }
}