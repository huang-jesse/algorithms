import java.util.HashSet;
import java.util.Set;

class Solution {
    public int numDifferentIntegers(String word) {

        if (word == null || word.length() == 0) return 0;

        Set<String> uniqueInt = new HashSet();
        String intStr = "";
        for (int i = 0; i < word.length(); i++) {
            int charCode = word.charAt(i);
            char charStr = (char)charCode;
            // not int(48-57) and intStr is not empty
            if (charCode > 57 && intStr != "") {
                uniqueInt.add(intStr);
                intStr = "";
                continue;
            }
            // not int
            if (charCode > 57) {
                continue;
            }
            // if empty add first '0'
            if (intStr == "") {
                intStr += "0";
            }
            // the first char is '0'
            if (intStr.length() == 1 && charCode == 48) {
                continue;
            }
            intStr += charStr;
        }
        // add last one
        if (intStr != "") {
            uniqueInt.add(intStr);
        }

        return uniqueInt.size();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // Set parameters
        String str = "a1b01c001";
        // Do test
        int test = sol.numDifferentIntegers(str);
        System.out.println(test);
    }
}