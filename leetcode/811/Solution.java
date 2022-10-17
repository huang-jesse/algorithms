import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> subdomainCounter = new HashMap<>();
        for (String cpdomain : cpdomains) {
            String[] arr = cpdomain.split(" ");
            int count = Integer.valueOf(arr[0]);
            String domain = arr[1];
            int subLen = domain.length();
            for (int i = subLen - 1; i >= 0; i--) {
                if (domain.charAt(i) != '.') {
                    continue;
                }
                String subdomain = domain.substring(i + 1);
                subdomainCounter.put(subdomain, subdomainCounter.getOrDefault(subdomain, 0) + count);
            }
            subdomainCounter.put(domain, subdomainCounter.getOrDefault(domain, 0) + count);

        }
        List<String> ans = new ArrayList<>();
        for (String subdomain : subdomainCounter.keySet()) {
            ans.add(subdomainCounter.get(subdomain) + " " + subdomain);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] cpdomains = new String[]{"9001 discuss.leetcode.com"};
        System.out.println("test: " + sol.subdomainVisits(cpdomains));
    }
}