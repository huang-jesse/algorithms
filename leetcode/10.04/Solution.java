class Solution {
    public int findString(String[] words, String s) {
        if (s.isEmpty()) return -1;

        // binary search
        return binarySearch(words, s, 0, words.length-1);
    }

    private static int binarySearch(String[] words, String s, int min, int max) {
        if (min > max) return -1;
        int mid = (max+min) / 2;
        // find
        if (s.equals(words[mid])) return mid;

        // search both side when mid is empty string
        if (words[mid].isEmpty()) {
            // search left
            int index = binarySearch(words, s, min, mid-1);
            if (index > -1) return index;
            // search right when not found in left side
            return binarySearch(words, s, mid+1, max);
        }
        if (s.compareTo(words[mid]) < 0) {
            // search left
            return binarySearch(words, s, min, mid-1);
        } else {
            // search right
            return binarySearch(words, s, mid+1, max);
        }
    }

    public static void main(String[] args) {
        String[] words = new String[]{"at", "", "", "", "ball", "", "", "car", "", "","dad", "", ""};
        String s = "dad";

        Solution sol = new Solution();
        int index = sol.findString(words, s);
        System.out.println("Find index: " + index);
    }
}