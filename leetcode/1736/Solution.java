class Solution {
    static final char WILD = '?';
    public String maximumTime(String time) {
        String[] times = time.split(":");
        char[] hour = times[0].toCharArray();
        if (hour[0] == WILD) {
            if (hour[1] == WILD || Character.getNumericValue(hour[1]) < 4) {
                hour[0] = '2';
            } else {
                hour[0] = '1';
            }
        }
        if (hour[1] == WILD) {
            if (hour[0] == '2') {
                hour[1] = '3';
            } else {
                hour[1] = '9';
            }
        }

        char[] minute = times[1].toCharArray();
        if (minute[0] == WILD) {
            minute[0] = '5';
        }
        if (minute[1] == WILD) {
            minute[1] = '9';
        }
        StringBuilder sb = new StringBuilder();
        sb.append(hour)
          .append(':')
          .append(minute);

        return sb.toString();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String time = "?4:03";
        System.out.println("test: " + sol.maximumTime(time));
    }
}