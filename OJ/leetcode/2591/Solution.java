class Solution {
    public int distMoney(int money, int children) {
        if (money < children) {
            return -1;
        }
        // distribute 1 dollar
        int remainingMoney = money - children;
        // distribute 7 dollar
        int distTimes = remainingMoney / 7;
        remainingMoney = remainingMoney % 7;
        if (distTimes > children || (distTimes == children && remainingMoney > 0)) {
            // last one distribute more than 8 dollar
            return children - 1;
        }
        // distTimes <= children
        if (distTimes == children && remainingMoney == 0) {
            return children;
        }
        // forbidden to distribute 4 dollar
        if (distTimes == children - 1 && remainingMoney == 3) {
            // last one cannot distribute 4 dollar, so there will be two child share 5 dollar
            return children - 2;
        }
        return distTimes;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int money = 20;
        int children = 3;
        System.out.println("test: " + sol.distMoney(money, children));
    }
}