import java.util.ArrayList;
import java.util.List;

class Solution {
    public String minimumString(String a, String b, String c) {
        List<String> list = new ArrayList<>();
        list.add(concatenate(a, b, c));
        list.add(concatenate(a, c, b));
        list.add(concatenate(b, a, c));
        list.add(concatenate(b, c, a));
        list.add(concatenate(c, a, b));
        list.add(concatenate(c, b, a));

        int minLen = 400;
        for (String str : list) {
            minLen = Math.min(minLen, str.length());
        }
        List<String> res = new ArrayList<>();
        for (String str : list) {
            if (str.length() == minLen) {
                res.add(str);
            }
        }
        res.sort((o1, o2) -> o1.compareTo(o2));
        return res.get(0);
    }

    private String concatenate(String str1, String str2, String str3) {
        int m = str2.length();
        int k = str3.length();
        StringBuilder sb = new StringBuilder(str1);
        int l = sb.length();
        int prefix = -1;
        for (int i = 0; i < l; i++) {
            int rightBoundary = Math.min(m, (l - i));
            for (int j = 0; j < rightBoundary; j++) {
                if (sb.charAt(i+j) == str2.charAt(j)) {
                    prefix = j + 1;
                    continue;
                } else {
                    prefix = 0;
                    break;
                }
            }
            if (prefix == rightBoundary) {
                break;
            }
        }
        for (int i = prefix; i < m; i++) {
            sb.append(str2.charAt(i));
        }

        l = sb.length();
        prefix = -1;
        for (int i = 0; i < l; i++) {
            int rightBoundary = Math.min(k, (l - i));
            for (int j = 0; j < rightBoundary; j++) {
                if (sb.charAt(i+j) == str3.charAt(j)) {
                    prefix = j + 1;
                    continue;
                } else {
                    prefix = 0;
                    break;
                }
            }
            if (prefix == rightBoundary) {
                break;
            }
        }
        for (int i = prefix; i < k; i++) {
            sb.append(str3.charAt(i));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String a = "abc";
        String b = "bca";
        String c = "aaa";// aaabca
        System.out.println("test: " + sol.minimumString(a, b, c));
    }
}