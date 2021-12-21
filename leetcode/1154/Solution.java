class Solution {
    private static final int[] monthDays = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    public int dayOfYear(String date) {
        String[] dateStr = date.split("-");
        int year = Integer.valueOf(dateStr[0]);
        int month = Integer.valueOf(dateStr[1]);
        int day = Integer.valueOf(dateStr[2]);
        if (isLeapYear(year)) {
            monthDays[2]++;
        }

        int ans = day;
        for (int i = 1; i <= month; i++) {
            ans += monthDays[i-1];
        }
        return ans;
    }

    /**
     * 公历闰年判定遵循的规律为：四年一闰、百年不闰、400年再闰
     * @param year
     * @return
     */
    private static boolean isLeapYear(int year) {
        if (year % 4 == 0 && year % 100 != 0) {
            return true;
        } else if (year % 400 == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // String date = "2019-01-09";
        // String date = "2004-03-01";
        String date = "2012-01-02";
        System.out.println("test: " + sol.dayOfYear(date));
    }
}