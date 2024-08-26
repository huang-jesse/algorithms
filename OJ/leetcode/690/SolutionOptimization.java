import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
// Definition for Employee.
class Employee {
    public int id;
    public int importance;
    public List<Integer> subordinates;
};
*/

class SolutionOptimization {
    public int getImportance(List<Employee> employees, int id) {
        Employee[] employeeArr = new Employee[2001];
        for (Employee e : employees) {
            employeeArr[e.id] = e;
        }
        int ans = 0;
        Queue<Employee> queue = new LinkedList<>();
        queue.offer(employeeArr[id]);
        while (!queue.isEmpty()) {
            Employee e = queue.poll();
            ans += e.importance;
            for (int subId : e.subordinates) {
                queue.offer(employeeArr[subId]);
            }
        }
        return ans;
    }

    static class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;

        public Employee(int id, int importance, List<Integer> subordinates) {
            this.id = id;
            this.importance = importance;
            this.subordinates = subordinates;
        }
    }

    public static void main(String[] args) {
        SolutionOptimization sol = new SolutionOptimization();
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, 5, Arrays.asList(2,3)));
        employees.add(new Employee(2, 3, Collections.emptyList()));
        employees.add(new Employee(3, 3, Collections.emptyList()));
        int id = 1;
        System.out.println("getImportance: "+ sol.getImportance(employees, id));
    }
}