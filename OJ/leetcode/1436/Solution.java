import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public String destCity(List<List<String>> paths) {
        Set<String> origins = new HashSet<>();
        for (List<String> path : paths) {
            origins.add(path.get(0));
        }
        for (List<String> path : paths) {
            if (!origins.contains(path.get(1))) {
                return path.get(1);
            }
        }
        return "";
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        List<List<String>> paths = new ArrayList<>();
        paths.add(Arrays.asList("London","New York"));
        paths.add(Arrays.asList("New York","Lima"));
        paths.add(Arrays.asList("Lima","Sao Paulo"));
        System.out.println("test: " + sol.destCity(paths));
    }
}