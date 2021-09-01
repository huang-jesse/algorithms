class Solution {
    public int compareVersion(String version1, String version2) {
        String[] ver1Arr = version1.split("\\.");
        String[] ver2Arr = version2.split("\\.");
        for (int i = 0; i < ver1Arr.length || i < ver2Arr.length; ++i) {
            int x = 0, y = 0;
            if (i < ver1Arr.length) {
                x = Integer.parseInt(ver1Arr[i]);
            }
            if (i < ver2Arr.length) {
                y = Integer.parseInt(ver2Arr[i]);
            }
            if (x > y) {
                return 1;
            }
            if (x < y) {
                return -1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String version1 = "1.0.1";
        String version2 = "1";
        System.out.println("test: " + sol.compareVersion(version1, version2));
    }
}