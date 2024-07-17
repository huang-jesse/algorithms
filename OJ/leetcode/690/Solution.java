import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
// Definition for Employee.
class Employee {
    public int id;
    public int importance;
    public List<Integer> subordinates;
};
*/

class Solution {
    public static class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;

        public Employee(int id, int importance, List<Integer> subordinates) {
            this.id = id;
            this.importance = importance;
            this.subordinates = subordinates;
        }
    };

    private Map<Integer, Employee> empsMap;

    public int getImportance(List<Employee> employees, int id) {
        if (employees == null) return 0;
        empsMap = employees.stream().collect(Collectors.toMap(emp -> emp.id, emp -> emp));
        return recursiveEmployee(empsMap.get(id));
    }

    private int recursiveEmployee(Employee employee) {
        int importance = employee.importance;
        if (employee.subordinates != null) {
            for (Integer subId : employee.subordinates) {
                Employee subEmp = empsMap.get(subId);
                importance += recursiveEmployee(subEmp);
            }
        }
        return importance;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, 5, Arrays.asList(2,3)));
        employees.add(new Employee(2, 3, Collections.emptyList()));
        employees.add(new Employee(3, 3, Collections.emptyList()));
        int id = 1;
        System.out.println("getImportance: "+ sol.getImportance(employees, id));
    }
}