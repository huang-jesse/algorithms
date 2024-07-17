import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
    final static String TABLE = "Table";
    public List<List<String>> displayTable(List<List<String>> orders) {
        Map<String, Map<String, Integer>> tableMap = new HashMap<>();
        Set<String> foods = new HashSet<>();
        for (List<String> order : orders) {
            String tableNumber = order.get(1);
            String foodItem = order.get(2);
            foods.add(foodItem);
            Map<String, Integer> table = tableMap.getOrDefault(tableNumber, new HashMap<>());
            table.put(foodItem, table.getOrDefault(foodItem, 0) + 1);
            tableMap.put(tableNumber, table);
        }
        List<String> header = foods.stream().sorted().collect(Collectors.toList());
        header.add(0, TABLE);

        List<List<String>> ans = new ArrayList<>();
        ans.add(header);
        
        Comparator<String> compareIntStr = (o1, o2) -> Integer.valueOf(o1).compareTo(Integer.valueOf(o2));
        List<String> tableNumbers = tableMap.keySet().stream().sorted(compareIntStr).collect(Collectors.toList());
        
        for (String table : tableNumbers) {
            List<String> row = new ArrayList<>();
            for (String item : header) {
                if (item.equals(TABLE)) {
                    row.add(table);
                } else {
                    Integer countOfFood = tableMap.get(table).getOrDefault(item, 0);
                    row.add(String.valueOf(countOfFood));
                }
            }
            ans.add(row);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String[][] ordersString = {{"David","3","Ceviche"},{"Corina","10","Beef Burrito"},{"David","3","Fried Chicken"},{"Carla","5","Water"},{"Carla","5","Ceviche"},{"Rous","3","Ceviche"}};
        List<List<String>> orders = Arrays.stream(ordersString).map(order -> {
            return Arrays.stream(order).collect(Collectors.toList());
        })
        .collect(Collectors.toList());
        System.out.println("test: " + sol.displayTable(orders));
    }
}