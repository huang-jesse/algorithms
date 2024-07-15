import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        // {email, indexList}
        Map<String, List<Integer>> emailsMap = new HashMap<>();
        int n = accounts.size();
        for (int i = 0; i < n; i++) {
            List<String> emails = accounts.get(i);
            for (int j = 1; j < emails.size(); j++) {
                emailsMap.computeIfAbsent(emails.get(j), o -> new ArrayList<>());
                emailsMap.get(emails.get(j)).add(i);
            }
        }
        // {nodeIndex, [nodeIndex]}
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (List<Integer> connectedIndexes : emailsMap.values()) {
            if (connectedIndexes.size() <= 1) continue;
            int k = connectedIndexes.size();
            for (int i = 0; i < k; i++) {
                int indexi = connectedIndexes.get(i);
                graph.computeIfAbsent(indexi, o -> new ArrayList<>());
                for (int j = i + 1; j < k; j++) {
                    int indexj = connectedIndexes.get(j);
                    graph.computeIfAbsent(indexj, o -> new ArrayList<>());
                    graph.get(indexi).add(indexj);
                    graph.get(indexj).add(indexi);
                }
            }
        }
        List<List<String>> ans = new ArrayList<>();
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            if (!graph.containsKey(i)) {
                // not connected with another account
                List<String> accountInfo = accounts.get(i);
                accountInfo.subList(1, accountInfo.size()).sort(String::compareTo);
                ans.add(accountInfo);
            } else {
                // find and merge all connected account
                List<Integer> mergeList = new ArrayList<>();
                // BFS
                Deque<Integer> queue = new ArrayDeque<>();
                visited[i] = true;
                queue.offer(i);
                while (!queue.isEmpty()) {
                    int node = queue.poll();
                    mergeList.add(node);
                    List<Integer> nextNodes = graph.get(node);
                    for (int nextNode : nextNodes) {
                        if (visited[nextNode]) continue;
                        visited[nextNode] = true;
                        queue.offer(nextNode);
                    }
                }
                Set<String> emails = new HashSet<>();
                for (int index : mergeList) {
                    List<String> account = accounts.get(index);
                    for (int j = 1; j < account.size(); j++) {
                        emails.add(account.get(j));
                    }
                }
                List<String> mergeAccountInfo = new ArrayList<>();
                mergeAccountInfo.add(accounts.get(i).get(0));
                List<String> sortedEmails = emails.stream().sorted().collect(Collectors.toList());
                mergeAccountInfo.addAll(sortedEmails);
                ans.add(mergeAccountInfo);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        List<List<String>> accounts = Arrays.asList(
            Arrays.asList("John","johnsmith@mail.com","john_newyork@mail.com")
            ,Arrays.asList("John","johnsmith@mail.com","john00@mail.com")
            ,Arrays.asList("Mary","mary@mail.com")
            ,Arrays.asList("John","johnnybravo@mail.com"));
        System.out.println("test: " + sol.accountsMerge(accounts));
    }
}